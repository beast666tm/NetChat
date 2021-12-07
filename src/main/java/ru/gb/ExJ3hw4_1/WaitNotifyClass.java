package ru.gb.ExJ3hw4_1;

/**
 * Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 **/
public class WaitNotifyClass {
    static final Object monitor = new Object();
    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {

        /* A */
        final Thread threadA = new Thread(() -> {  // * второй метод
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (currentLetter != 'A')
                            monitor.wait();
                        System.out.print((i + 1) + " "); // украшательства
                        System.out.print(currentLetter);
                        currentLetter = 'B';
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /* B */
        final Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (currentLetter != 'B')
                            monitor.wait();
                        System.out.print(currentLetter);
                        currentLetter = 'C';
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /* C */
        final Thread threadC = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (currentLetter != 'C')
                            monitor.wait();
                        System.out.print(currentLetter);
                        currentLetter = 'A';
                        monitor.notifyAll();
                        System.out.println(); //  Добавил чтобы вывести столбиком (Лучше считать)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}