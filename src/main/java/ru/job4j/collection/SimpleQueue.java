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
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) { // проверяем пуст ли второй стек
            while (!in.isEmpty()) { // делаем пока не закончаться элемнты в первом стеке
                t = in.pop(); // получаем то значение которое удаляем первым(с головы) у стек 1
                out.push(t); //заносим его в первым в стек два
            }
        }
        return out.pop(); // удаляем первый элемент из стек два и выводим
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
        System.out.println(fifo.poll());
        System.out.println(fifo);
        fifo.push("четыри");
        System.out.println(fifo);
        System.out.println(fifo.poll());
        System.out.println(fifo);

    }

}
