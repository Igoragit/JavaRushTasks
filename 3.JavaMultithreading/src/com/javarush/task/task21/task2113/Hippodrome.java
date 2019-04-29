package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gor_I on 04.04.2017.
 */
public class Hippodrome {

    static  Hippodrome game;


    public static void main(String[] args) throws InterruptedException {

        game = new Hippodrome(new ArrayList<>());

        game.getHorses().add(new Horse("Gor",3,0));
        game.getHorses().add(new Horse("Seruxa",3,0));
        game.getHorses().add(new Horse("Dima",3,0));
        game.getHorses().add(new Horse("Zonov",3,0));
        game.getHorses().add(new Horse("Tolya",3,0));
        game.getHorses().add(new Horse("Sergey",3,0));

        game.run();
        game.printWinner();
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }


    public void run() throws InterruptedException {

        for(int x = 0; x < 100; x ++){
            move();
            print();
            Thread.sleep(100);
        }
    }

    public void move(){
        for(Horse in: horses){
            in.move();
        }
    }

    public void print(){
        for (Horse in: horses){
            in.print();
        }

        for(int x=0; x<10; x++){
            System.out.println();
        }
    }


    public Horse getWinner(){
        double dis=0;

        for(Horse in: horses){

            if(in.getDistance()>dis) {
                dis=in.getDistance();
            }
        }

        for(Horse in: horses){
            if(in.getDistance()==dis) {
               return in;
            }
        }
        return null;
    }

    public void printWinner(){

        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
