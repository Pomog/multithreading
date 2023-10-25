package org.multithreading;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        var bracket = new Brackets();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 10; i++) {
                    bracket.generate();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Time taken for Thread 1: " + (endTime - startTime) + "ms");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < 10; i++) {
                    bracket.generate();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("Time taken for Thread 2:  " + (endTime - startTime) + "ms");
            }
        }).start();
    }



}