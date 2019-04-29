package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            // File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(new File("G:/file.txt"));
            InputStream inputStream = new FileInputStream(new File("G:/file.txt"));

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут


            User user1 = new User();
            User user2 = new User();

            user1.setFirstName("Vasya");
            user1.setLastName("Pupkin");

            GregorianCalendar calendar = new GregorianCalendar();
            Date d1 = calendar.getTime();

            user1.setBirthDate(d1);

            user1.setMale(true);
            user1.setCountry(User.Country.RUSSIA);

            user2.setFirstName("masha");
            user2.setLastName("popova");

            GregorianCalendar calendar2 = new GregorianCalendar();
            Date d2 = calendar2.getTime();

            user2.setBirthDate(d2);

            user2.setMale(true);
            user2.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user1);
            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            /////
     //       javaRush.load(inputStream);
            ////

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {

            PrintWriter writer = new PrintWriter(outputStream);

            for(User in: users){
                writer.println(in.getFirstName());
                writer.println(in.getLastName());
                writer.println(in.getBirthDate().toString());
                writer.println(Boolean.toString(in.isMale()));
                writer.println(in.getCountry().toString());
            }
            writer.flush();

            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                int x=users.size();
                while (reader.ready()){
                    users.add(x, new User());
                    users.get(x).setFirstName(reader.readLine());
                    users.get(x).setLastName(reader.readLine());

                    DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    String data = reader.readLine();
                    Date get = df.parse(data);
                    users.get(x).setBirthDate(get);

                    users.get(x).setMale(Boolean.parseBoolean(reader.readLine()));

                    String country = reader.readLine();

                    if(country.equals("UKRAINE"))users.get(x).setCountry(User.Country.UKRAINE);
                    if(country.equals("RUSSIA"))users.get(x).setCountry(User.Country.RUSSIA);
                    if(country.equals("OTHER"))users.get(x).setCountry(User.Country.OTHER);
                    x++;
                }

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
