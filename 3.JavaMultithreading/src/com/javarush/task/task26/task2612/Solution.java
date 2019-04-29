package com.javarush.task.task26.task2612;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
Весь мир играет комедию
*/
public class Solution {
    private Lock lock = new ReentrantLock();

    public void someMethod() {
        boolean status=true;
        //implement logic here, use the lock field
        try {
             status= lock.tryLock();
            if (!status) {
                ifLockIsBusy();
            }
            else ifLockIsFree();
        }
        finally {
           if(status) lock.unlock();

        }
    }

    public void ifLockIsFree() {
    }

    public void ifLockIsBusy() {
    }
}
