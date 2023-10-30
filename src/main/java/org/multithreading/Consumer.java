package org.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable{
    private ArrayBlockingQueue<Integer> queue;

    public Consumer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
                queue.take();
                Main.counter2--;
                System.out.println("Value removed in the queue: " + Main.counter2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
