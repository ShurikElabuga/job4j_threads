package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T item;
    private final int from;
    private final int to;
    private static final int THRESHOLD = 10;

    public ParallelSearch(T[] array, T item, int from, int to) {
        this.array = array;
        this.item = item;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= THRESHOLD) {
            return linerSearch(array, item, from, to);
        }

        int middle = (from + to) / 2;
        ParallelSearch<T> left = new ParallelSearch<>(array, item, from, middle);
        ParallelSearch<T> right = new ParallelSearch<>(array, item, middle, to);
        left.fork();
        right.fork();
        int leftResult = left.join();
        int rightResult = right.join();

        return leftResult != -1 ? leftResult : rightResult;
    }

    private static <T> int linerSearch(T[] array, T object, int from, int to) {
        for (int i = from; i < to; i++) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int search(T[] array, T item) {
        if (array.length <= THRESHOLD) {
            return linerSearch(array, item, 0, array.length);
        }
        return ForkJoinPool.commonPool().invoke(new ParallelSearch<>(array, item, 0, array.length));
    }
}
