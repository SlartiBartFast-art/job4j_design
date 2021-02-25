package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] container; // elementData
    private int size = 0; // хранит текущий размер нашего листа
    private int modCount = 0; // сколько раз коллекция была изменена с момента ее создания

    public SimpleArray(int length) {
        this.container = (T[]) new Object[length];
    }

    public SimpleArray() {
        this.container = (T[]) new Object[DEFAULT_SIZE];
    }

    public T get(int index) {
        System.out.println("get --- index" + index + "/" + "size___" + size);
        Objects.checkIndex(index, size);
        return container[index];
    }

    public boolean add(T t) { // element
        if (size == container.length) {
            container = grow();
        }
        container[size] = t;
        size = size + 1;
        System.out.println(size);
        System.out.println("DAA sixe____" + size);
        modCount++;
        return true;
    }

    private T[] grow() {
        int oldCapasity = container.length;
        int s = size * 2;
        if (oldCapasity == 0) {
            return Arrays.copyOf(container, DEFAULT_SIZE);
        }
        return Arrays.copyOf(container, s);
    }

    public boolean remove(int index) {
        boolean rsl = false;
        int newSize = size - 1;
        Objects.checkIndex(index, size);
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
            container[newSize] = null;
            rsl = true;
            size--;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int count = 0;
            private int expectModCount = modCount;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[count++];
            }
        };
    }
}
