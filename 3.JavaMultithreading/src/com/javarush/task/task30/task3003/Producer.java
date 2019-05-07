package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for(int x =1; x<10; x++){
            try {
            System.out.format(String.format("Элемент 'ShareItem-%d' добавлен\n",x));
            queue.offer(new ShareItem(String.format("ShareItem-%d",x)));

                Thread.sleep(100);
                if(queue.hasWaitingConsumer()){
                    System.out.format("Consumer в ожидании!\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
