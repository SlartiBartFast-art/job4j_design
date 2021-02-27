package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedListContainerTest {

    @Test
    public void whenAddThenGet() {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        String rsl = list.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenGetVarTwo() {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        list.add("two");
        list.add("three");
        list.add("four");
        String rsl = list.get(2);
        assertThat(rsl, is("three"));
    }

    @Test
    public void whenAddThenIterator() {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        String rsl = list.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenSize() {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        list.add("two");
        int rsl = list.size();
        assertThat(rsl, is(2));
    }
}