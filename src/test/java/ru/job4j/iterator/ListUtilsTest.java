package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;;

public class ListUtilsTest {



    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 4, 6, 8));
        ListUtils.removeIf(input, integer -> integer > 3);
        assertThat(Arrays.asList(2), Is.is(input));
    }

     @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        ListUtils.replaceIf(input, t -> t > 5, 0);
        assertThat(Arrays.asList(1, 3, 5, 0, 0), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> removing = Arrays.asList(4, 5);
        ListUtils.removeAll(input, removing);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }
}