package ru.job4j.generics;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void add() {
        SimpleArray<Integer> array = new SimpleArray<>(new Integer[]{1, 2, null, null});
        array.add(7);
        array.add(2);
        SimpleArray<Integer> exp = new SimpleArray<>(new Integer[]{1, 2, 7, 2});
        assertThat(exp.toString(), is("SimpleArray{"
                + "array=" + "[" + "1," + " " + "2," + " " + "7," + " " + "2" + "]"
                + '}'));
    }

    @Test
    public void get() {
        SimpleArray<Integer> array = new SimpleArray<>(new Integer[]{1, 2, 3, 4});
        int rsl = array.get(2);
        assertThat(rsl, is (3));
    }

    @Test
    public void set() {
    }

    @Test
    public void remove() {
        SimpleArray<Integer> array = new SimpleArray<>(new Integer[]{1, 2, 3, 4});
        array.remove(1);
        SimpleArray<Integer> rsl = array;
        SimpleArray<Integer> exp = new SimpleArray<>(new Integer[]{1, 3, 4, null});
        assertThat(rsl.toString(), is ("SimpleArray{"
                + "array=" + "[" + "1," + " " + "3," + " " + "4," + " " + "null" + "]"
                + '}'));
    }
}