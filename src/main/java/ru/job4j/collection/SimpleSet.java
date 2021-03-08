package ru.job4j.collection;

import java.util.*;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> simpleArray;

    public SimpleSet() {
        this.simpleArray = new SimpleArray<>();
        this.simpleArray = new SimpleArray<T>();
    }

    public boolean add(T t) { // element
        boolean rsl = false;
        if (!contains(t)) {
            simpleArray.add(t);
            rsl = true;
        }
        return rsl;
    }

    public boolean contains(T t) {
        boolean rsl = false;
        for (T f : simpleArray) {
            if (Objects.equals(f, t)) {
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleSet.class.getSimpleName() + "[", "]")
                .add("simpleArray=" + simpleArray)
                .toString();
    }

    public static void main(String[] args) {
        SimpleSet<String> strings = new SimpleSet<>();
        strings.add("ROcked");
        strings.add("ROLL");
        strings.add("ROcked");
         Iterator inter = strings.iterator();

        System.out.println(strings);
        System.out.println(inter.next());
        System.out.println(inter.next());
        System.out.println(inter.next());

        // Iterator<String> it = strings.iterator();
      // strings.add(null);
     //   it.next();

    }
}


