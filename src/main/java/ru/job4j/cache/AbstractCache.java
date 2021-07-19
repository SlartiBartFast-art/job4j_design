package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Реализация кеша на SoftReference [#1592]
 * Категория : 2.4. Garbage Collection
 * Топик : 2.4.4. Типы
 * Часть 1
 * Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, что бы можно было задать ключ получения объекта кеша и в случае
 * если его нет в памяти, задать поведение загрузки этого объекта в кеш. Для это выделим класс:
 * @param <K>
 * @param <V>
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {

    }

    public V get(K key) {
        return null;
    }

    /**
     * В случае если его нет в памяти, задать поведение загрузки этого объекта в кеш(Мап)
     * @param key
     * @return
     */
    protected abstract V load(K key);
}
