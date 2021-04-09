package ru.job4j.collection;

import java.util.Iterator;

public interface Map<K, V> extends Iterable<K> {
    boolean insert(K key, V value);

    boolean delete(K key);

    V get(K key);

    int size();

    @Override
    Iterator<K> iterator();
}
