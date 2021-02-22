package ru.job4j.generics;

import java.util.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int point = 0;

    public SimpleArray(int length) {
        // Objects.checkIndex(index, point);
        this.array = (T[]) new Object[length];
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     */
    public void add(T model) {
        Objects.checkIndex(point, array.length);
            this.array[point++] = model;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     */
    public T get(int index) {
        Objects.checkIndex(index, point);
        return array[index];
    }

    /**
     * заменяет указанным элементом (model), элемент
     * находящийся по индексу index;
     */
    public void set(int index, T model) { // заменить на элемент
        Objects.checkIndex(index, point);
        array[index] = model;
    }

    /**
     * удаляет элемент по указанному индексу, все находящиеся справа элементы
     * при этом необходимо сдвинуть на единицу влево
     * (в середине массива не должно быть пустых ячеек);
     */
    public void remove(int index) { // то что в ячейке
        Objects.checkIndex(index, point);
        System.arraycopy(array, index + 1, array, index, point - (index + 1));
        array[point - 1] = null;
        point--;
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                + "array=" + Arrays.toString(array)
                + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < point;
            }

            @Override
            public T next() {
                return array[count++];
            }
        };
    }

}


