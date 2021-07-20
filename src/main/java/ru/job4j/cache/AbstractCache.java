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
    //where will be placed file name(K), file date(V)
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * метод записывает в Map<K, V>
     * where Key -file name,Value - file data
     * @param key String имя объекта(файла)
     * @param value (содержимое файла)
     */
    public void put(K key, V value) {
    }

    /**
     * Метод проверяет есть ли в Мап данный файл
     * Возвращает содержимое по ключу файла
     * @param key file name
     * @return V (содержимое/content Value - file date)
     */
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
