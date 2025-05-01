package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import java.util.concurrent.ExecutionException;

class RowColSumTest {

    @Test
    void whenSquareMatrixThenSequentialSumCorrect() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        RowColSum.Sums[] expected = {
                new RowColSum.Sums(6, 12),
                new RowColSum.Sums(15, 15),
                new RowColSum.Sums(24, 18)
        };

        RowColSum.Sums[] result = RowColSum.sum(matrix);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenSquareMatrixThenAsyncSumCorrect() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        RowColSum.Sums[] expected = {
                new RowColSum.Sums(6, 12),
                new RowColSum.Sums(15, 15),
                new RowColSum.Sums(24, 18)
        };

        RowColSum.Sums[] result = RowColSum.asyncSum(matrix);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenEmptyMatrixThenEmptyResult() {
        int[][] matrix = {};

        RowColSum.Sums[] result = RowColSum.sum(matrix);
        assertThat(result).isEmpty();
    }

    @Test
    void whenSingleElementMatrixThenCorrectSums() {
        int[][] matrix = {{5}};

        RowColSum.Sums[] expected = {new RowColSum.Sums(5, 5)};

        RowColSum.Sums[] result = RowColSum.sum(matrix);
        assertThat(result).isEqualTo(expected);
    }
}