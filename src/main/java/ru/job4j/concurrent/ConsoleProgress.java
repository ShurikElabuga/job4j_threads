package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    char[] process = new char[] {'-', '\\', '|', '/'};

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            for (char ch : process) {
                System.out.print("\rload: " + ch);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
