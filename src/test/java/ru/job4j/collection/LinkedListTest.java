package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;


public class LinkedListTest {
    @Test
    public void whenAddThenGet() {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        String rsl = list.get(0);
        assertThat(rsl, is("first"));
    }
    @Test
    public void whenAddThenIterator() {
        LinkedList<String> list = new LinkedList<>();
        list.add("first");
        String rsl = list.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("first");
        linkedList.get(1);

    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
       LinkedList<String> linkedList = new LinkedList<>();
        linkedList.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("first");
        Iterator<String> it = linkedList.iterator();
        linkedList.add("second");
        it.next();
    }

}