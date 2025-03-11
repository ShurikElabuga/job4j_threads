package ru.job4j.concurrent;

public class Wget {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> { }
        );
        thread.start();
        for (int i = 0; i <= 100; i++) {
            System.out.print("\rLoading :" + i + "%");
            Thread.sleep(1000);
        }
    }
}
