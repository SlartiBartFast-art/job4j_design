package ru.job4j.collection;

import java.util.StringJoiner;

/**
 * Используя контейнер на базе связанного списка создать
 * контейнер Stack [#471670]
 * @param <T>
 */
public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    //поле ForwardLinked. Это связанный список из предыдущего задания.
    private int size = 0;

    /**
     * Метод push(T value) - помещает значение в коллекцию.
     */
    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    /**
     * Метод pop() - должен возвращать значение и удалять его из коллекции.
     */
    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleStack.class.getSimpleName() + "[", "]")
                .add("linked=" + linked)
                .toString();
    }
}
