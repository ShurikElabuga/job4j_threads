package ru.job4j.io;

import java.io.*;

public class WriteFile {

    private final File file;

    public WriteFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream output = new BufferedOutputStream(new FileOutputStream(file))) {
            for (char ch : content.toCharArray()) {
                output.write(ch);
            }
        }
    }
}
