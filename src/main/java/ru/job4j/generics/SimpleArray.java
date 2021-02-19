package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SimpleArray<T> implements Iterator<T> {
    private final T[] array;
    private int size = 0;
    private int point = 0;

    public SimpleArray(T[] array) {
        this.array = array;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null || array[i] != (Integer) 0 || array[i] != (Boolean) false) {
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
        return array[index];
    }

    /**
     * заменяет указанным элементом (model), элемент
     * находящийся по индексу index;
     */
    public void set(int index, T model) { // заменить на элемент
        Objects.checkIndex(index, size);
        array[index] = model;
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
    public T next() {
        return array[point++];
    }

    @Override
    public boolean hasNext() {
        return point < size;
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                + "array=" + Arrays.toString(array)
                + '}';
    }
}
