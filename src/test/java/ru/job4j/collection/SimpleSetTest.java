package ru.job4j.collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSetTest {

    @Test
    public void whenAddThenIt() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("first");
        String rsl = simpleSet.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.iterator().next();
    }

    @Test
    public void whenAdd() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("Dod");
        simpleSet.add("Dog");
        Iterator<String> it = simpleSet.iterator();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenAddTwoThenNull() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Cat");
        set.add("Dog");
        set.add(null);
        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        assertNull(it.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddIterThenAdd() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("Ракета");
        Iterator<String> it = simpleSet.iterator();
        simpleSet.add(null);
        it.next();
    }
}