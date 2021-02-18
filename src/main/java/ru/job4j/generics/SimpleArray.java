package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int size = 0;

    public SimpleArray(T[] array) {
        this.array = array;
        for (int i = 0; i < array.length; i++) {
             if (array[i] != null) {
                 size++;
             }
        }
    }

    public int getSize() {
        return size;
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     */
    public void add(T model) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = model;
                size++;
                break;
            }
        }
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     */
    public T get(int index) {
        System.out.println(size + "razmer method get");
        System.out.println(Objects.checkIndex(index, size) + "result");
        Objects.checkIndex(index, size);
        for (T t : array) {
            if (t == array[index]) {
                return t;

            }
        }
        return null;
    }

    /**
     * заменяет указанным элементом (model), элемент
     * находящийся по индексу index;
     */
    public void set(int index, T model) { // заменить на элемент
        // if () // в рамках добавленных элементов
        Objects.checkIndex(index, size);
        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                array[i] = model;
            }
        }
    }

    /**
     * удаляет элемент по указанному индексу, все находящиеся справа элементы
     * при этом необходимо сдвинуть на единицу влево
     * (в середине массива не должно быть пустых ячеек);
     */
    public void remove(int index) { // то что в ячейке
        Objects.checkIndex(index, size);
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        array[size - 1] = null;
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return List.of(this.array).iterator();
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                + "array=" + Arrays.toString(array)
                + '}';
    }
}
