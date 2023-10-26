package org.multithreading;

public class Main {

    volatile public static int flag = 0;

    public static void main(String[] args) {

        new Thread (() -> {
            while (true){
                if (Main.flag == 0) {
                    System.out.println("Runing");
                } else {
                    System.out.println("Not runing");
                    break;
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                Main.flag = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

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



}