package ru.job4j.generics;

import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
public class SimpleArrayTest {

   @Test
    public void add() {
        SimpleArray<Integer> simpleArray = new SimpleArray(3);
        simpleArray.add(7);
        simpleArray.add(2);
        SimpleArray<Integer> rsl = simpleArray;
        assertThat(rsl.toString(), is("SimpleArray{"
                + "array=" + "[" + "7," + " " + "2," + " " + "null" + "]"
                + '}'));
    }

    @Test
    public void get() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(7);
        simpleArray.add(8);
        simpleArray.add(15);
        int rsl = simpleArray.get(2);
        assertThat(rsl, is (15));
    }

    @Test
    public void set() {
       SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
       simpleArray.add(1);
       simpleArray.add(2);
       simpleArray.add(3);
       simpleArray.set(0, 7);
       assertThat(simpleArray.get(0), is(7));
    }

    @Test
    public void remove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        SimpleArray<Integer> rsl = simpleArray;
        //SimpleArray<Integer> exp = new SimpleArray<Integer>(new int[]{1, 0, 3, 0, 0});
        assertThat(rsl.toString(), is ("SimpleArray{"
                + "array=" + "[" + "1," + " " + "3," + " " + "null," + " " + "null" + "]"
                + '}'));
    }

}