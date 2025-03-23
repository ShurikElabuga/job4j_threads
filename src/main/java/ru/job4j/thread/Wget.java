package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        var file = new File("tmp.xml");
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {
            var dataBuffer = new byte[1024];
            int bytesRead;
            long totalBytes = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = input.read(dataBuffer)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                totalBytes += bytesRead;
                long downloadTime = System.currentTimeMillis() - startTime;
                long expectedTime = totalBytes / speed;

                if (expectedTime > downloadTime) {
                    long pause = expectedTime - downloadTime;
                    Thread.sleep(pause);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.out.println("Проверьте ввод двух аргументов: <url> и <speed>");
            return;
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
