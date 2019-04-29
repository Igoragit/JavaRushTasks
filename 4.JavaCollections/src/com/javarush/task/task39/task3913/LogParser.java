package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private Path logDir;
    private List<Log> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        this.logDir = logDir;
        generateLogMap();
    }

    public void generateLogMap() {

        //чтение файла в лист строк
        List<String> logLines = new ArrayList<>();
        try {
            File folder = logDir.toFile();
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (!file.isDirectory()) {
                    if (file.getAbsolutePath().endsWith(".log")) {
                        logLines.addAll(Files.readAllLines(file.toPath()));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //чтение листа строк в лист массивов стрингов по содержимому строки
        List<String[]> stringsOfEachLogLine = new ArrayList<>();
        for (String in : logLines) {
            stringsOfEachLogLine.add(in.split("\t"));
        }

        // создание объекта Log с заполненными полями параметров

        for (String[] in : stringsOfEachLogLine) {
            Log log = new Log();
            log.setIp(in[0]);
            log.setUser(in[1]);
            log.setDate(stringToDate(in[2]));


            String[] strings = in[3].split("\\s+");
            if (strings.length > 1) {
                if (strings[1] != null) {
                    log.setTaskNum(Integer.parseInt(strings[1]));
                }
            }

            if (strings[0].contains(Event.DONE_TASK.toString())) {
                log.setEvent(Event.DONE_TASK);
            }
            if (strings[0].contains(Event.LOGIN.toString())) {
                log.setEvent(Event.LOGIN);
            }
            if (strings[0].contains(Event.DOWNLOAD_PLUGIN.toString())) {
                log.setEvent(Event.DOWNLOAD_PLUGIN);
            }
            if (strings[0].contains(Event.WRITE_MESSAGE.toString())) {
                log.setEvent(Event.WRITE_MESSAGE);
            }
            if (strings[0].contains(Event.SOLVE_TASK.toString())) {
                log.setEvent(Event.SOLVE_TASK);
            }
            //
            if (in[4].contains(Status.OK.toString())) {
                log.setStatus(Status.OK);
            }
            if (in[4].contains(Status.FAILED.toString())) {
                log.setStatus(Status.FAILED);
            }
            if (in[4].contains(Status.ERROR.toString())) {
                log.setStatus(Status.ERROR);
            }
            logs.add(log);
        }
    }

    ///QLQuery

    enum ExecuteType {
        GET_IP,
        GET_USER,
        GET_DATE,
        GET_EVENT,
        GET_STATUS,
        NULL
    }

    enum ExecuteFor {
        F_USER,
        F_DATE,
        F_EVENT,
        F_STATUS,
        F_IP,
        NULL
    }

    @Override
    public Set<Object> execute(String query) {

        ExecuteType executeType = ExecuteType.NULL;
        ExecuteFor executeFor = ExecuteFor.NULL;

        if (query.contains("get ip")) executeType = ExecuteType.GET_IP;
        if (query.contains("get user")) executeType = ExecuteType.GET_USER;
        if (query.contains("get date")) executeType = ExecuteType.GET_DATE;
        if (query.contains("get event")) executeType = ExecuteType.GET_EVENT;
        if (query.contains("get status")) executeType = ExecuteType.GET_STATUS;


        if (query.contains("for user")) executeFor = ExecuteFor.F_USER;
        if (query.contains("for date")) executeFor = ExecuteFor.F_DATE;
        if (query.contains("for event")) executeFor = ExecuteFor.F_EVENT;
        if (query.contains("for status")) executeFor = ExecuteFor.F_STATUS;
        if (query.contains("for ip")) executeFor = ExecuteFor.F_IP;

        String[] strings = query.split("\"");

        Event event = null;
        Status status = null;
        Object value = null;
        Date date1 = null;
        Date date2 = null;


        if (executeFor == ExecuteFor.NULL) {
            return executeByParam(executeType, executeFor, null, null, null, null, null);
        }
        else if (executeFor == ExecuteFor.F_STATUS || executeFor == ExecuteFor.F_EVENT) {
            switch (strings[1]) {
                case "LOGIN":
                    event = Event.LOGIN;
                    break;
                case "DOWNLOAD_PLUGIN":
                    event = Event.DOWNLOAD_PLUGIN;
                    break;
                case "WRITE_MESSAGE":
                    event = Event.WRITE_MESSAGE;
                    break;
                case "SOLVE_TASK":
                    event = Event.SOLVE_TASK;
                    break;
                case "DONE_TASK":
                    event = Event.DONE_TASK;
                    break;
                ///status
                case "OK":
                    status = Status.OK;
                    break;
                case "FAILED":
                    status = Status.FAILED;
                    break;
                case "ERROR":
                    status = Status.ERROR;
                    break;
            }
        }
        if (executeFor == ExecuteFor.F_DATE) value = stringToDate(strings[1]);
        else if (event == null && status == null) value = strings[1];

        if (query.contains("between")) {
            date1 = stringToDate(strings[3]);
            date2 = stringToDate(strings[5]);
            return executeByParam(executeType, executeFor, event, status, value, date1, date2);
        } else {
            return executeByParam(executeType, executeFor, event, status, value, null, null);
        }
    }


    private Date stringToDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Set<Object> executeByParam(ExecuteType executeType, ExecuteFor executeFor, Event event, Status status, Object value, Date after, Date before) {

        switch (executeType) {
            case GET_IP: {
                switch (executeFor) {
                    case F_USER: {
                        return new HashSet<>(getIPsForUser(String.valueOf(value), after, before));
                    }
                    case F_EVENT: {
                        return new HashSet<>(getIPsForEvent(event, after, before));
                    }
                    case F_STATUS: {
                        return new HashSet<>(getIPsForStatus(status, after, before));
                    }
                    case F_DATE: {
                        if (isDateInRange((Date) value, after, before)) {
                            Set<String> ips = new HashSet<>();
                            for (Log in : logs) {
                                if (in.getDate().getTime() == ((Date) value).getTime()) {
                                    ips.add(in.getIp());
                                }
                            }
                            return new HashSet<Object>(ips);
                        }
                        break;
                    }
                    case NULL: {
                        return new HashSet<>(getUniqueIPs(after, before));
                    }
                }
                break;
            }
            case GET_USER: {
                switch (executeFor) {
                    case F_IP: {
                        return new HashSet<>(getUsersForIP(String.valueOf(value), after, before));
                    }
                    case F_DATE: {
                        Set<Object> usersF_date = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getDate().equals(value)) usersF_date.add(in.getUser());
                        }
                        return usersF_date;
                        //
                    }
                    case F_EVENT: {
                        switch (event) {
                            case LOGIN: {
                                return new HashSet<>(getLoggedUsers(after, before));
                            }
                            case DONE_TASK: {
                                return new HashSet<>(getDoneTaskUsers(after, before));
                            }
                            case SOLVE_TASK: {
                                return new HashSet<>(getSolvedTaskUsers(after, before));
                            }
                            case WRITE_MESSAGE: {
                                return new HashSet<>(getWroteMessageUsers(after, before));
                            }
                            case DOWNLOAD_PLUGIN: {
                                return new HashSet<>(getDownloadedPluginUsers(after, before));
                            }
                        }

                    }
                    case F_STATUS: {
                        switch (status) {
                            case OK: {
                                Set<Object> users = new HashSet<>();
                                for (Log in : logs) {
                                    if (in.getStatus().equals(Status.OK)) users.add(in.getUser());
                                }
                                ///
                                return users;
                            }
                            case ERROR: {
                                Set<Object> users = new HashSet<>();
                                for (Log in : logs) {
                                    if (in.getStatus().equals(Status.ERROR)) users.add(in.getUser());
                                }
                                return users;
                            }
                            case FAILED: {
                                Set<Object> users = new HashSet<>();
                                for (Log in : logs) {
                                    if (in.getStatus().equals(Status.FAILED)) users.add(in.getUser());
                                }
                                return users;
                            }
                        }
                    }
                    case NULL: {
                        Set<Object> users = new HashSet<>();
                        for (Log in : logs) {
                            users.add(in.getUser());
                        }
                        return users;
                    }
                }
                break;
            }
            case GET_DATE: {
                switch (executeFor) {
                    case F_IP: {
                        Set<Object> dates = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getIp().contains(String.valueOf(value))) {
                                dates.add(in.getDate());
                            }
                        }
                        return dates;
                    }
                    case F_USER: {
                        Set<Object> dates = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getUser().equals(String.valueOf(value))) {
                                dates.add(in.getDate());
                            }
                        }
                        return dates;
                    }
                    case F_EVENT: {
                        Set<Object> dates = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getEvent().equals(event)) {
                                dates.add(in.getDate());
                            }
                        }
                        return dates;
                    }
                    case F_STATUS: {
                        Set<Object> dates = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getStatus().equals(status)) {
                                dates.add(in.getDate());
                            }
                        }
                        return dates;
                    }

                    case NULL: {
                        Set<Object> dates = new HashSet<>();
                        for (Log in : logs) {
                            dates.add(in.getDate());
                        }
                        return dates;
                    }
                }
                break;
            }

            case GET_EVENT: {
                switch (executeFor) {
                    case F_IP: {
                        return new HashSet<>(getEventsForIP(String.valueOf(value), after, before));
                    }
                    case F_USER: {
                        return new HashSet<>(getEventsForUser(String.valueOf(value), after, before));
                    }
                    case F_DATE: {
                        // if(!isDateInRange((Date)value,after,before)) return null;
                        Set<Object> events = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getDate().equals(value)) {
                                events.add(in.getEvent());
                            }
                        }
                        return events;
                    }
                    case F_STATUS: {
                        Set<Object> events = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getStatus().equals(status)) {
                                events.add(in.getEvent());
                            }
                        }
                        return events;
                    }
                    case NULL: {
                        return new HashSet<>(getAllEvents(after, before));
                    }
                }


            }

            case GET_STATUS: {
                switch (executeFor) {
                    case F_IP: {
                        Set<Object> gstatus = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getIp().contains(String.valueOf(value))) {
                                gstatus.add(in.getStatus());
                            }
                        }
                        return gstatus;
                    }
                    case F_USER: {
                        Set<Object> gstatus = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getUser().contains(String.valueOf(value))) {
                                gstatus.add(in.getStatus());
                            }
                        }
                        return gstatus;
                    }
                    case F_DATE: {
                        Set<Object> gstatus = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getDate().equals(value)) {
                                gstatus.add(in.getStatus());
                            }
                        }
                        return gstatus;
                    }
                    case F_EVENT: {
                        // if(!isDateInRange((Date)value,after,before)) return null;
                        Set<Object> gstatus = new HashSet<>();
                        for (Log in : logs) {
                            if (in.getEvent().equals(event)) {
                                gstatus.add(in.getStatus());
                            }
                        }
                        return gstatus;
                    }
                    case NULL: {
                        Set<Object> gstatus = new HashSet<>();
                        for (Log in : logs) {
                            gstatus.add(in.getStatus());
                        }
                        return gstatus;
                    }
                }
            }
        }
        return null;
    }

    ///
    /// EventQuery

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {

        Set<Event> events = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent() != null) {
                    events.add(in.getEvent());
                }
            }
        }
        return events.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();

        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                events.add(in.getEvent());
            }
        }

        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {

        Set<Event> events = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before) && in.getIp().contains(ip)) {
                events.add(in.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before) && in.getUser().contains(user)) {
                events.add(in.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)
                    && in.getStatus().equals(Status.FAILED)) {
                events.add(in.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)
                    && in.getStatus().equals(Status.ERROR)) {
                events.add(in.getEvent());
            }
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int countToSolve = 0;
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)
                    && in.getEvent().equals(Event.SOLVE_TASK)
                    && in.getTaskNum() == task) {
                countToSolve++;
            }
        }
        return countToSolve;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int countToSolve = 0;
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)
                    && in.getEvent().equals(Event.DONE_TASK)
                    && in.getTaskNum() == task) {
                countToSolve++;
            }
        }
        return countToSolve;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {

        Map<Integer, Integer> taskNum_countOfAttempt = new HashMap<>();
        Set<Integer> numbersOfTask = new HashSet<>();
        for (Log in : logs) {
            if (in.getTaskNum() > 0) {
                numbersOfTask.add(in.getTaskNum());
            }
        }

        for (Integer in : numbersOfTask) {
            int value = getNumberOfAttemptToSolveTask(in, after, before);
            if (value > 0) {
                taskNum_countOfAttempt.put(in, value);
            }
        }

        return taskNum_countOfAttempt;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> taskNum_countOfAttempt = new HashMap<>();
        Set<Integer> numbersOfTask = new HashSet<>();
        for (Log in : logs) {
            if (in.getTaskNum() > 0) {
                numbersOfTask.add(in.getTaskNum());
            }
        }

        for (Integer in : numbersOfTask) {
            int value = getNumberOfSuccessfulAttemptToSolveTask(in, after, before);
            if (value > 0) {
                taskNum_countOfAttempt.put(in, value);
            }
        }

        return taskNum_countOfAttempt;
    }

    ///

    /// DateQuery

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log in : logs) {
            if (in.getUser().equals(user)) {
                if (in.getEvent().equals(event)) {
                    if (isDateInRange(in.getDate(), after, before)) {
                        dates.add(in.getDate());
                    }
                }
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log in : logs) {
            if (in.getStatus().equals(Status.FAILED)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    dates.add(in.getDate());
                }
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (Log in : logs) {
            if (in.getStatus().equals(Status.ERROR)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    dates.add(in.getDate());
                }
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {

        ArrayList<Date> dates = new ArrayList<>();
        for (Log in : logs) {
            if (in.getUser().contains(user)) {
                if (in.getEvent().equals(Event.LOGIN)) {
                    if (isDateInRange(in.getDate(), after, before)) {
                        dates.add(in.getDate());
                    }
                }
            }
        }

        dates.sort(Comparator.naturalOrder());

        try {
            return dates.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        ArrayList<Date> dates = new ArrayList<>();
        if (user == null || user.equals("")) return null;

        for (Log in : logs) {
            if (in.getUser().contains(user)) {
                if (in.getEvent().equals(Event.SOLVE_TASK)) {
                    if (in.getTaskNum() == task) {
                        if (isDateInRange(in.getDate(), after, before)) {
                            dates.add(in.getDate());
                        }
                    }
                }
            }
        }

        Collections.sort(dates);

        try {
            return dates.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        ArrayList<Date> dates = new ArrayList<>();
        if (user == null || user.equals("")) return null;

        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getUser().contains(user)) {
                    if (in.getEvent().equals(Event.DONE_TASK)) {
                        if (in.getTaskNum() == task) {
                            // if(in.getStatus().equals(Status.OK)){
                            dates.add(in.getDate());
                            // }
                        }
                    }
                }
            }
        }

        Collections.sort(dates);

        try {
            return dates.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {

        Set<Date> userWroteMessage = new HashSet<>();
        if (user == null || Objects.equals(user, "")) return userWroteMessage;

        for (Log in : logs) {
            if (in.getUser().contains(user)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    if (in.getEvent().equals(Event.WRITE_MESSAGE)) {
                        if (in.getStatus().equals(Status.OK)) {
                            userWroteMessage.add(in.getDate());
                        }
                    }
                }
            }
        }

        return userWroteMessage;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> userDownloadedPlugin = new HashSet<>();
        if (user == null || Objects.equals(user, "")) return userDownloadedPlugin;
        for (Log in : logs) {
            if (in.getUser().contains(user)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    if (in.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                        userDownloadedPlugin.add(in.getDate());
                    }
                }
            }
        }

        return userDownloadedPlugin;
    }

    ///UserQuery
    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();

        for (Log in : logs) {
            users.add(in.getUser());
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                users.add(in.getUser());
            }
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> events = new HashSet<>();
        for (Log in : logs) {
            if (in.getUser().equals(user)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    if (in.getEvent() != null) {
                        events.add(in.getEvent());
                    }
                }
            }
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> usersForIp = new HashSet<>();
        for (Log in : logs) {
            if (in.getIp().contains(ip)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    usersForIp.add(in.getUser());
                }
            }
        }
        return usersForIp;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {

        Set<String> loggedUsers = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.LOGIN)) {
                    //  if (in.getStatus().equals(Status.OK)) {
                    loggedUsers.add(in.getUser());
                    //  }
                }
            }
        }
        return loggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> usersDownloadedPlugin = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    if (in.getStatus().equals(Status.OK)) {
                        usersDownloadedPlugin.add(in.getUser());
                    }
                }
            }
        }
        return usersDownloadedPlugin;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> usersSentMessage = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.WRITE_MESSAGE)) {
                    if (in.getStatus().equals(Status.OK)) {
                        usersSentMessage.add(in.getUser());
                    }
                }
            }
        }
        return usersSentMessage;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> usersSolvedTask = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.SOLVE_TASK)) {
                    usersSolvedTask.add(in.getUser());
                }
            }
        }
        return usersSolvedTask;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> usersSolvedCorrentTask = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.SOLVE_TASK)) {
                    if (in.getTaskNum() == task) {
                        usersSolvedCorrentTask.add(in.getUser());
                    }
                }
            }
        }
        return usersSolvedCorrentTask;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> usersDoneTask = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.DONE_TASK)) {
                    //  if (in.getStatus().equals(Status.OK)) {
                    usersDoneTask.add(in.getUser());
                    //   }
                }
            }
        }
        return usersDoneTask;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> usersDONECorrentTask = new HashSet<>();
        for (Log in : logs) {
            if (isDateInRange(in.getDate(), after, before)) {
                if (in.getEvent().equals(Event.DONE_TASK)) {
                    if (in.getTaskNum() == task) {
                        usersDONECorrentTask.add(in.getUser());
                    }
                }
            }
        }
        return usersDONECorrentTask;
    }
    ///

    public class Log {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private Status status;
        private Integer taskNum;

        public Log() {
            this.taskNum = 0;
        }

        public int getTaskNum() {
            return taskNum;
        }

        public void setTaskNum(int taskNum) {
            this.taskNum = taskNum;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }

    ///IPQuery


    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {

        ArrayList<String> allIPs = new ArrayList<>();

        for (Log in : logs) {
            allIPs.add(in.getIp());
        }

      /*  ArrayList<String> allIPsCopy = new ArrayList<>(allIPs);

        for (int x = 0; x < allIPs.size() - 1; x++) {
            for (int i = x + 1; i < allIPs.size(); i++) {
                if (allIPs.get(x).equals(allIPs.get(i))) {
                    allIPsCopy.set(x, "");
                    allIPsCopy.set(i, "");
                }
            }
        }
        Set<String> ips = new HashSet<>(allIPsCopy);
        ips.remove("");*/

        Set<String> ips = new LinkedHashSet<>(allIPs);
        Set<String> ips_inTime = new HashSet<>();

        for (Log in : logs) {
            for (String into : ips) {
                if (in.getIp().equals(into)) {
                    if (isDateInRange(in.getDate(), after, before)) {
                        ips_inTime.add(into);
                    }
                }
            }
        }
        return ips_inTime;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {

        Set<String> ips = new HashSet<>();
        for (Log in : logs) {
            if (in.getUser().equals(user)) {
                if (isDateInRange(in.getDate(), after, before)) {
                    ips.add(in.getIp());
                }
            }
        }
        return ips;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (Log in : logs) {
            if (in.getEvent() == event) {
                if (isDateInRange(in.getDate(), after, before)) {
                    ips.add(in.getIp());
                }
            }
        }
        return ips;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (Log in : logs) {
            if (in.getStatus() == status) {
                if (isDateInRange(in.date, after, before)) {
                    ips.add(in.getIp());
                }
            }
        }
        return ips;
    }

    private boolean isDateInRange(Date check, Date after, Date before) {
        /*boolean fits = before == null || check.before(before) ;
        return fits && (after == null || check.after(after));
*/
      /*  boolean fits = before == null || check.before(before) || check.equals(before);
        return fits && (after == null || check.after(after) || check.equals(after));
*/
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            return check.getTime() < before.getTime();
        } else if (before == null) {
            return check.getTime() > after.getTime();
        } else {
            return check.getTime() > after.getTime() && check.getTime() < before.getTime();
        }
    }
    ///
}