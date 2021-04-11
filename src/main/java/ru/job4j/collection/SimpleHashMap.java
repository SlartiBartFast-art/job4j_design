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
    private int capaCity = 16; // это размер массива
    private Node<K, V>[] hashtable = new Node[capaCity];
    private int size = 0; // Количество элементов HashMap-а;
    private int modCount = 0; // сколько раз коллекция была изменена с момента ее создания
    private final float loadFactor = 0.75f; // коэффициент загрузки / в данном заднии не используется

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

        if (size > loadFactor * this.capaCity) {
            resize();
        }
        int index = indexKey(key);
        System.out.println("index: =" + index);
        if (hashtable[index] != null && !hashtable[index].getKey().equals(key)) {
            System.out.println("false--: зашел в If");
            return rsl;
        }
        System.out.println("dobavlenie: " + index);
        add(index, key, value);
        size++;
        modCount++;
        rsl = true;
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

        int index = indexKey(key);
        if (hashtable[index] == null || !compareNode(index, key)) {
            return false;
        }
        hashtable[index] = null;
        modCount++;
        size--;
        return true;
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
        if (hashtable[index] == null
                || !hashtable[index].getKey().equals(key)) {
            return null;
        }
        return hashtable[index].getValue();
    }

    /**
     * Метод проводит удваивание массива путем перекопировани
     * и увеличения при достижении порогового значения
     */
    private Node<K, V>[] resize() {
        capaCity = capaCity * 2;
        Node<K, V>[] newhashtable = new Node[capaCity];
        for (Node<K, V> node : hashtable) {
            if (node != null) {
                int index = indexKey(node.getKey());
                newhashtable[index] = node;
            }
        }
        return newhashtable;
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
     * @param value значение
     * @param index итоговый индекс в бакете
     * @return
     */
    private boolean add(int index, K key, V value) {
        hashtable[index] = new Node<>(key, value);
        return true;
    }

    /**
     * Метод проверяет на равенство ключей по хешам
     * Если ключи не равны, то возвращать false.
     *
     * @param index бакет
     * @param key   ключ
     * @return true or false
     */
    private boolean compareNode(int index, K key) {
        K key1 = hashtable[index].getKey();
        return (key1.hashCode() == key.hashCode() && key1.equals(key));
    }

    /**
     * Определяет позицию в массиве куда будет помещен элемент
     * окончательный индекс(бакет)
     *
     * @param key ключ
     * @return int число бакета
     */
    private int indexKey(K key) {
        int etr = hash(key) & (capaCity - 1);
        System.out.println("LastIndex=" + etr);
        return etr;
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
        int ty = rsl % (hashtable.length - 1);
        System.out.println("Hash=" + ty);
        return ty;
    }

    /**
     * Нода, вложенная в каждый бакет
     *
     * @param <K> является основанием для получения хеш
     * @param <V> значение храниемое по ключу
     */
    private static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("key=" + key)
                    .add("value=" + value)
                    .toString();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int modi = modCount;
            private int count = 0;
            private int lastpos = 0;

            @Override
            public boolean hasNext() {
                if (modi != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                for (int ind = lastpos; ind < capaCity; ind++) {
                    System.out.println("Текущий индекс в ФОре: " + ind);
                    if (hashtable[ind] != null) {
                        lastpos = 1 + ind;
                        count++;
                        return hashtable[ind].getKey();
                    }
                }
                return null;
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
      /*  siMap.delete(17);
        System.out.println(siMap.size());
        System.out.println(siMap);
        siMap.delete(122);*/
        System.out.println(siMap);
    }

}
