package ru.job4j.collection;

import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 8. Реализовать собственную структуру данных - HashMap [#471707]
 * Ассоциативный массив на базе хэш-таблицы должен быть унифицирован через генерики и иметь методы:
 * boolean insert(K key, V value);
 * V get(K key);
 * boolean delete(K key);
 *
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V> implements Map<K, V> {
    private int capaCity = 16; // по идеи это размер массива
    private Node<K, V>[] hashtable = new Node[capaCity];
    private int size = 0; // Количество элементов HashMap-а;
    private int modCount = 0; // сколько раз коллекция была изменена с момента ее создания
    private float loadFactor = 0.75f; // коэффициент загрузки / в данном заднии не используется

    /**
     * Метод проводит вставку пары ключ-значение
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean insert(K key, V value) {
        boolean rsl = false;
        int keyHash = hash(key);

        if (size == this.capaCity) {
            arrayDouble();
        }
        if (!checkHashTable(key)) {
            rsl = add(keyHash, value, indexKey(key));
        } else {
            rsl = add(keyHash, value, freeIndex());
        }
        return rsl;
    }

    /**
     * Метод проводит удаление новой пары ключ в хеш-таблице
     *
     * @param key парамерт по которому проходит удание
     * @return true если уделание прошло удачно , иначе false
     */
    @Override
    public boolean delete(K key) {
        boolean rsl = false;
        if (checkHashTable(key)) {
            hashtable[indexKey(key)] = null;
            size--;
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метода проводит получение знаяения по ключу
     *
     * @param key ключ
     * @return значение соотвествующее ключу если оно найдено, иначе значение null;
     */
    @Override
    public V get(K key) {
        V v = null;
        int index = indexKey(key);
        if (checkHashTable(key)) {
            if (compareHash(index, key)) {
                v = (V) hashtable[index].getValue();
            } else {
                v = getFromTable(key);
            }
        }
        return v;
    }

    /**
     * Определяет позицию в массиве куда будет помещен элемент
     * окончательный индекс(бакет)
     *
     * @param key ключ
     * @return int число бакета
     */
    private int indexKey(K key) {
        return hash(key) & (capaCity - 1);
    }

    /**
     * Метод проводит удваивание массива путем перекопировани
     * и увеличения при достижении порогового значения
     */
    private void arrayDouble() {
        hashtable = Arrays.copyOf(hashtable, capaCity * 2);
    }

    /**
     * Метод возращает колличество элементов hashtable
     *
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Метод проводит создание и встаку Node
     *
     * @param hash  результат работы метода hash()
     * @param value значение
     * @param index итоговый индекс в бакете
     * @return
     */
    private boolean add(int hash, V value, int index) {
        hashtable[index] = new Node<>(hash, value);
        this.size++;
        return true;
    }

    /**
     * Метод прроверяет на присутствие key в hashtable
     *
     * @param key - Ключ
     * @return Обнаружен key с таким значениме - true, нет - false.
     */
    private boolean checkHashTable(K key) {
        int index = hash(key);
        boolean rsl = false;
        if (size > 0) {
            for (Node node : hashtable) {
                if (node != null && node.getHash() == index) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Метод помогает избегать лишних вложености в методе: V get(key)
     * Осуществляет поиск по всему массиву, в случаии колизии.
     *
     * @return V value or null
     */
    private V getFromTable(K key) {
        V v = null;
        for (int i = 0; i < hashtable.length; i++) {
            if (compareHash(i, key)) {
                v = (V) hashtable[i].getValue();
                break;
            }
        }

        return v;
    }

    /**
     * Метод проверяет на равенство ключей по хешам
     * Если ключи не равны, то возвращать false.
     *
     * @param index бакет
     * @param key   ключ
     * @return true or false
     */
    private boolean compareHash(int index, K key) {
        return hashtable[index].getHash() == hash(key);
    }

    /**
     * получение хеша
     * предварительного индекса для бакета
     *
     * @param key
     * @return индекс
     */
    private int hash(K key) {
        int rsl = 31;
        rsl = rsl * key.hashCode();
        return rsl % (hashtable.length - 1);
    }

    private int freeIndex() {
        int rsl = -1;
        for (int i = 0; i < this.capaCity; i++) {
            if (hashtable[i] == null) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    /**
     * Нода, вложенная в каждый бакет
     *
     * @param <K> является основанием для получения хеш
     * @param <V> значение храниемое по ключу
     */
    private static class Node<K, V> {
        private int hash;
        private V value;

        public Node(int hash, V value) {
            this.hash = hash;
            this.value = value;
        }

        private int getHash() {
            return hash;
        }

        private V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("hash=" + hash)
                    .add("value=" + value)
                    .toString();
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                var result = false;
                for (int i = position; i < hashtable.length; i++) {
                    if (hashtable[i] != null) {
                        position = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (V) hashtable[position++].getValue();
            }
        };
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleHashMap.class.getSimpleName() + "[", "]")
                .add("capaCity=" + capaCity)
                .add("hashtable=" + Arrays.toString(hashtable))
                .add("size=" + size)
                .add("modCount=" + modCount)
                .add("loadFactor=" + loadFactor)
                .toString();
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer, Integer> siMap = new SimpleHashMap<>();
        siMap.insert(1, 1);
        siMap.insert(122, 11);
        siMap.insert(11235, 14);
        siMap.insert(17, 17);
        Iterator<Integer> iter = siMap.iterator();
        while (iter.hasNext()) {

            System.out.println("Значение ключа равно: " + iter.next());

        }
        System.out.println(siMap.size());
    }
}
