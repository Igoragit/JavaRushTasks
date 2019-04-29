package com.javarush.task.task29.task2909.human;



import java.util.ArrayList;
import java.util.List;

public class University  {


     private List<Student> students;
     private String name;
     private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University(String name, int age) {

        this.students = new ArrayList<>();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        int max = 0;
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() > students.get(max).getAverageGrade())
                max = i;
        }
        return students.size() == 0 ? null : students.get(max);
    }

    public Student getStudentWithMinAverageGrade() {
        int min = 0;
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getAverageGrade() < students.get(min).getAverageGrade())
                min = i;
        }
        return students.size() == 0 ? null : students.get(min);
    }

    public void expel(Student student){
        students.remove(student);
    }
}