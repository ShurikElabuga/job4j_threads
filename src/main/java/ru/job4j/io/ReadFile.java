package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ReadFile {
    private final File file;

    public ReadFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                char ch = (char) data;
                if (filter.test(ch)) {
                    output.append(ch);
                }
            }
        }
        return output.toString();
    }
}
