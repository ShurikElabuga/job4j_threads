package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelSearchTest {

    @Test
    void whenArrayLargeThenIndexIsFound() {
        String[] array = new String[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = "item - " + i;
        }
          assertThat(ParallelSearch.search(array, "item - 11")).isEqualTo(11);
        assertThat(ParallelSearch.search(array, "item - 100")).isEqualTo(-1);
    }

    @Test
    void whenArraySmallThenIndexIsFound() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertThat(ParallelSearch.search(array, 5)).isEqualTo(4);
        assertThat(ParallelSearch.search(array, 11)).isEqualTo(-1);
    }

    @Test
    void whenDoubleArraySmallThenIndexIsFound() {
        Double[] array = new Double[9];
        for (int i = 0; i < array.length; i++) {
            array[i] = 0.5 + i;
        }
        assertThat(ParallelSearch.search(array, 8.5)).isEqualTo(8);
        assertThat(ParallelSearch.search(array, 9.5)).isEqualTo(-1);
    }
}