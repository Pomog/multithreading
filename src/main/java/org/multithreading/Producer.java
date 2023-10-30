package org.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable{
    private ArrayBlockingQueue<Integer> queue;

    public Producer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
                queue.put(Main.counter2++);
                System.out.println("Value added in the queue: " + Main.counter2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
