package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
//поле ForwardLinked. Это связанный список из предыдущего задания.

    /**
     * Метод push(T value) - помещает значение в коллекцию.
     */
    public void push(T value) {
        linked.add(value);

    }

    /**
     * Метод pop() - должен возвращать значение и удалять его из коллекции.
     */
    public T pop() {
        return linked.deleteLast();
    }
}
