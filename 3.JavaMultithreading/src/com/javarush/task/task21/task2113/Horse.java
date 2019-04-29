package com.javarush.task.task21.task2113;

/**
 * Created by Gor_I on 04.04.2017.
 */
public class Horse {

    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    ///
    public void move(){
        this.distance +=getSpeed()*Math.random();
    }

    public void print(){

        String repeatedStar = new String(new char[(int)Math.floor(getDistance())]).replace('\0', '.');

        System.out.println(repeatedStar + getName());
    }
}
