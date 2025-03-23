package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File fileForRead = new File("exampleRead.txt");
        File fileForWrite1 = new File("exampleWrite1.txt");
        File fileForWrite2 = new File("exampleWrite2.txt");
        try {
            ReadFile readFile = new ReadFile(fileForRead);
            WriteFile writeFile1 = new WriteFile(fileForWrite1);
            WriteFile writeFile2 = new WriteFile(fileForWrite2);

            String allContent = readFile.content(FilterContent.getContent());
            writeFile1.saveContent(allContent);

            String contentWithoutUnicode = readFile.content(FilterContent.getContentWithoutUnicode());
            writeFile2.saveContent(contentWithoutUnicode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
