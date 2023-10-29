package org.multithreading;

import java.util.concurrent.CountDownLatch;

public class ThreadForPool extends Thread{
    private String threadCode;
    private CountDownLatch latch;

    public ThreadForPool(String threadCode, CountDownLatch latch) {
        this.threadCode = threadCode;
        this.latch = latch;

    }

    public String getThreadCode() {
        return threadCode;
    }

    @Override
    public void run() {
        System.out.println("Thread " + getThreadCode() + " is now running");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + getThreadCode() + " is now finished");
        latch.countDown();
    }
}
