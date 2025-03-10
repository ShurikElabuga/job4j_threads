package ru.job4j.concurrent;

public class ConcurrentOutput {

    public static void main(String[] args) {
        Thread another1 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread another2 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another1.start();
        another2.start();
        System.out.println(Thread.currentThread().getName());
    }
}
