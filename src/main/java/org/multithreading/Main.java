package org.multithreading;

public class Main {

    public static void main(String[] args) {

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