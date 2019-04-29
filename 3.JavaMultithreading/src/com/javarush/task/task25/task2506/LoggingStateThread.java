package com.javarush.task.task25.task2506;


import static java.lang.Thread.State.TERMINATED;

  class   LoggingStateThread extends Thread {

    private Thread thread;
    private Thread.State state;
    private boolean ch=true;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
        state=thread.getState();
        System.out.println(state);
    }

    @Override
    public void run() {
        super.run();

        while (ch){


            if(this.state!=this.thread.getState()){
                this.state=this.thread.getState();
                System.out.println(state);
            }

            if(this.state == TERMINATED){
                this.interrupt();
                ch=false;
            }
        }
    }
}

