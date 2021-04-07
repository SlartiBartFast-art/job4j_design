package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    private SimpleHashMap<String, SimpleUser> map = new SimpleHashMap<String, SimpleUser>();
    private SimpleUser one = new SimpleUser("Petr", 2);
    private SimpleUser two = new SimpleUser("Ivan", 3);
    private SimpleUser three = new SimpleUser("Fedor", 5);

    @Before
    public void setUp() {
        map.insert("Первый", one);
        map.insert("Второй", two);
        map.insert("Третий", three);
    }

    @Test
    public void insert() {
        assertThat(map.size(), is(3));
    }

    @Test
    public void get() {
        assertThat(map.get("Первый"), is(one));
    }

    @Test
    public void delete() {
        assertThat(map.get("Первый"), is(one));
        map.delete("Первый");
        assertThat(map.get("Первый"), is(nullValue()));
        assertThat(map.size(), is(2));
    }

    @Test
    public void numberInKey() {
        SimpleHashMap<Integer, String> map1 = new SimpleHashMap<>();
        map1.insert(10, "one");
        map1.insert(35, "two");
        map1.insert(315, "three");
        map1.insert(317, "five");


        assertThat(map1.get(35), is("two"));
        assertThat(map1.get(315), is("three"));
    }

    @Test
    public void iterator() {
        SimpleHashMap<String, String> map1 = new SimpleHashMap<>();
        map1.insert("Первый", "one");
        map1.insert("Второй", "two");
        map1.insert("Третий", "three");
        Iterator<String> iter = map1.iterator();

        System.out.println((iter.hasNext()));
        assertThat(iter.hasNext(), is(true));
        System.out.println((iter.next()));
        assertThat(iter.hasNext(), is(true));
        assertNotNull(iter.next());
        assertThat(iter.hasNext(), is(true));
        assertNotNull(iter.next());
        assertThat(iter.hasNext(), is(false));
    }
}