package org.multithreading;

public class Brackets {

    public static synchronized void generate(String threadCode) {
              for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (i < 5) {
                    System.out.print("[");
                } else {
                    System.out.print("]");
                }
            }

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Generated with code: " + threadCode);
    }
}
