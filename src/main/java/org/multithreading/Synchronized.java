package org.multithreading;

public class Synchronized {

    public static int counter = 0;

    public void counter() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " is now running");
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
                for (int i = 0; i <= 1000; i++) {
                    Synchronized.counter++;
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " is now finished");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " is now running");
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
                for (int i = 0; i <= 1000; i++) {
                    Synchronized.counter++;
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " is now finished");
            }
        });

        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        System.out.println("Main.counter = " + Synchronized.counter);
    }
}
