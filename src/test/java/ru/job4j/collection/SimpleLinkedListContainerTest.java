package ru.job4j.collection;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleLinkedListContainerTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        String rsl = list.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenGetVarTwo() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.add("two");
        list.add("three");
        list.add("four");
        String rsl = list.get(2);
        assertThat(rsl, is("three"));
    }

    @Test
    public void whenAddThenIterator() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        String rsl = list.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenSize() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.add("two");
        int rsl = list.size();
        assertThat(rsl, is(2));
    }
}