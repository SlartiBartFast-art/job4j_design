package ru.job4j.collection;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    /**
     * Метод push(T value) - помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
        size++;
    }

    /**
     * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
     */
    public T poll() {
        T t = null;
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        t = in.pop();
        out.push(t);
      /*  if (out.isEmpty()) {
            while (!in.isEmpty()) {
                t = in.pop();
                out.push(t);

            }
        }*/
        return t;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleQueue.class.getSimpleName() + "[", "]")
                .add("in=" + in)
                .add("out=" + out)
                .toString();
    }

    public static void main(String[] args) {
        SimpleQueue<String> fifo = new SimpleQueue<>();
        fifo.push("один");
        fifo.push("два");
        fifo.push("три");
        fifo.poll();
        System.out.println(fifo);
        fifo.push("четыри");
        System.out.println(fifo);
        fifo.poll();
        System.out.println(fifo);

    }

}
