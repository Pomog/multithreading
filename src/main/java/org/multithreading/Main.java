package org.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    volatile public static int flag = 0;
    public static int balance = 0;
    public int counter = 0;
    static int counter2 = 0;
    static int counter3 = 0;
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread reentrantLock1 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10000; i++) {
                    Main.counter3++;
                }
            } finally {
                lock.unlock();
            }

        });

        Thread reentrantLock2 = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 10000; i++) {
                Main.counter3++;
            }
            lock.unlock();
        });

        reentrantLock1.start();
        reentrantLock2.start();

        try {
            reentrantLock1.join();
            reentrantLock2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Counter3 = " + Main.counter3);
        System.out.println("reentrantLock ENDS");



        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Thread produserThread = new Thread(producer);
        produserThread.start();

        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        //wait for produserThread ends
        try {
            produserThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ExecutorService executorService = Executors.newFixedThreadPool(2);

        var latch = new CountDownLatch(10);

        var thread1 = new ThreadForPool("Thread 1", latch);
        var thread2 = new ThreadForPool("Thread 2", latch);
        var thread3 = new ThreadForPool("Thread 3", latch);
        var thread4 = new ThreadForPool("Thread 4", latch);
        var thread5 = new ThreadForPool("Thread 5", latch);
        var thread6 = new ThreadForPool("Thread 6", latch);
        var thread7 = new ThreadForPool("Thread 7", latch);
        var thread8 = new ThreadForPool("Thread 8", latch);
        var thread9 = new ThreadForPool("Thread 9", latch);
        var thread10 = new ThreadForPool("Thread 10", latch);


        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.execute(thread4);
        executorService.execute(thread5);
        executorService.execute(thread6);
        executorService.execute(thread7);
        executorService.execute(thread8);
        executorService.execute(thread9);
        executorService.execute(thread10);


        executorService.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        var app = new Main();

        var ThreadForJoin = getThread(app);
        try {
            ThreadForJoin.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter in the Main Thread = " + app.counter);

        var threadWithDraw = new Thread(() -> {
            app.withdraw(100);
        });
        threadWithDraw.start();

       if (balance <= 0) {
           try {
               Thread.sleep(4000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           threadWithDraw.interrupt();
       }

       new Thread(() -> {
           try {
               Thread.sleep(5000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           app.deposit(200);
        }).start();

//
//        new Thread (() -> {
//            while (true){
//                if (Main.flag == 0) {
//                    System.out.println("Runing");
//                } else {
//                    System.out.println("Not runing");
//                    break;
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//                Main.flag = 1;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                Brackets.generate("Thread 1");
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken for Thread 1: " + (endTime - startTime) + "ms");
        }).start();

        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                Brackets.generate("Thread 2");
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken for Thread 2:  " + (endTime - startTime) + "ms");
        }).start();
    }

    private static Thread getThread(Main app) {
        var ThreadForJoin = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is now running");
            System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            for (int i = 0; i <= 1000; i++) {
                app.counter++;
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " is now finished");
            System.out.println("Counter = " + app.counter);
        });
        ThreadForJoin.start();
        return ThreadForJoin;
    }

    public void withdraw (int amount){
       synchronized (this) {
           if (balance <= 0) {
               try {
                   System.out.println("Waiting for deposit...");
                   wait();
               } catch (InterruptedException e) {
                   System.out.println("Thread interrupted");
                   return;
               }
           }
       }
        balance -= amount;
        System.out.println("Current balance: " + balance);
        System.out.println("Withdraw: " + amount + " successfully");
    }

    public void deposit (int amount){
        System.out.println("Deposit: " + amount);
        balance += amount;
        synchronized (this) {
            notifyAll();
        }
    }
}

