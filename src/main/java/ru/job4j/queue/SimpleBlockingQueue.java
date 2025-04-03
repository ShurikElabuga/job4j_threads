package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
   final private Queue<T> queue = new LinkedList<>();
   final private int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() > size) {
                wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
                wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }
}
