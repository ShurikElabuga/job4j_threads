package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count;

    public CASCount(int initValue) {
        this.count = new AtomicInteger(initValue);
    }

    public void increment() {
        int oldCount;
        int newCount;
        do {
            oldCount = count.get();
            newCount = oldCount + 1;
        } while (!count.compareAndSet(oldCount, newCount));
    }

    public int get() {
        return count.get();
    }
}
