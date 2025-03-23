package ru.job4j.io;

import java.util.function.Predicate;

public final class FilterContent {

    public static Predicate<Character> getContent() {
        return ch -> true;
    }

    public static Predicate<Character> getContentWithoutUnicode() {
        return ch -> ch < 0x80;
    }
}
