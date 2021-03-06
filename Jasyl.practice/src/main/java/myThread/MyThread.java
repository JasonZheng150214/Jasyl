package myThread;

import java.util.concurrent.TimeUnit;

/**
 * Created by jason on 16/5/31.
 */
public class MyThread implements Runnable {

    public void run() {
        System.out.println("run");
    }

    public static void main(String[] args) {

        new Thread();
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();


    }

    static class TimeWaiting implements Runnable {

        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Waiting implements Runnable {

        public void run() {
            while (true) {
                synchronized (Waiting.class) {

                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }


    static class Blocked implements Runnable {

        public void run() {
            while (true) {
                synchronized (Blocked.class) {

                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }
}
