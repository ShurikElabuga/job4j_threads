package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {

    public record Sums(int rowSum, int colSum) {
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] result = new Sums[n];
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum+=matrix[i][j];
                colSum+=matrix[j][i];
            }
            result[i] = new Sums(rowSum, colSum);
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] result = new Sums[n];
        CompletableFuture<Sums>[] futures = new CompletableFuture[n];
        for (int i=0; i<n; i++) {
            final int index = i;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                int rowSum = 0;
                int colSum = 0;
                for (int j = 0; j < n; j++) {
                    rowSum+=matrix[index][j];
                    colSum+=matrix[j][index];
                }
                return new Sums(rowSum, colSum);
            });
        }
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures);
        allFutures.get();
        for (int i = 0; i<n; i++) {
            result[i] = futures[i].get();
        }
        return result;
    }
}
