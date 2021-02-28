package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private int modCount = 0;
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;

        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        modCount++;
        size++;
    }

    public T deleteFirst() {
        T t = null;
        if (head != null) { // проверяем что указатель не пустой(там есть дальше нода)
            Node<T> tmp = head; // получили ту ноду на которую указывает Хед /нода1
            Node<T> node = tmp.next; // взяли указатель первой ноды на след ноду2

            head = node; // прописали в Хед что первод будет след нода2
            t = tmp.value; // получили значение из нода1 которую удаляем
            tmp.next = null; // удаляем указатель на ноду2 из ноды1
        }
        if (t == null) {
            throw new NoSuchElementException();
        }

        return t; // возвращаем удаляемое значение
    }

  /* public T get(T t) {
        T th  = null;
        if (head != null) {
            //Node<T> node = head.next;
            for (int i = 0; i < size; i++) {
                if (head.value == t) {
                th = head.value;
                }
            }
        }
        return th;
    }*/

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
