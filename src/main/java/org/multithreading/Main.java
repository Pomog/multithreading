package org.multithreading;

public class Main {

    volatile public static int flag = 0;

    public static int balance = 0;

    public int counter = 0;

    public static void main(String[] args) {

        var app = new Main();

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

