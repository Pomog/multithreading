package org.multithreading;

public class Brackets {
    synchronized public void generate() {
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                System.out.print("[");
            } else {
                System.out.print("]");
            }
        }
        System.out.println();
    }
}
