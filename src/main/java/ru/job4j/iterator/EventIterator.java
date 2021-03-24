package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращает только четные числа
 */
public class EventIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int count = 0;

    public EventIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        for (int i = count; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                break;
            }
            count++;
        }
        return numbers.length > count;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        System.out.println("count--->" + count);

        return numbers[count++];

    }
}

