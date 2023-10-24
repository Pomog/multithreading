package org.multithreading;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        new Synchronized().counter();
//        new Brackets().generate();

        var bracket = new Brackets();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    bracket.generate();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    bracket.generate();
                }
            }
        }).start();
    }



}