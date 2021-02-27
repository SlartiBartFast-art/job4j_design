package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListContainer<E> implements Iterable<E> {
    private int size = 0; // количество добавленных элементов в список /длина
    private Node<E> head; //первый узел в списке
    private Node<E> last; //последний узел

    private int modCount = 0; // сколь раз изменен с момента создания

    public LinkedListContainer() {

    }

    public int size() {
        return size;
    }

    public void add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(E e) {
        E t = null;
        for (int i = 0; i < size; i++) {
            if (head.getItem() == e) {
                t = head.getItem();
            }
        }
        return t;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            private int expectModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E e = current.getItem();
                current = current.next;
                return e;
            }
        };
    }

    private static class Node<E> {

        private E item;
        private Node<E> prev;
        private Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

    }

    public static void main(String[] args) {
        LinkedListContainer<String> linkedListContainer = new LinkedListContainer<>();
        linkedListContainer.add("Apple");
        linkedListContainer.add("cherry");

        System.out.println(linkedListContainer.size());
    }
}

