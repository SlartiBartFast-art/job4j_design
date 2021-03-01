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
        if (head == null) {
            throw new NoSuchElementException();
        }
        //if (head != null) { // проверяем что указатель не пустой(там есть дальше нода)
        T t = head.value; // прописали зачение которое будет удалено
        head = head.next; // прописали в Хед что первод будет след нода2
        return t; // возвращаем удаляемое значение
    }

    public T get(int index) {
        //Node<T> t = null;
        T t = null;
        int i = 0;
        Node<T> currentNode = head; // первая нода
        while (currentNode != null) {
//
            if (i == index) {
                // t = currentNode;
                t = currentNode.getValue();
                return t;
            }

            currentNode = currentNode.getNext(); // four ->> three ->> two ->> one ->> null
            i++;
        }
        return t;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T t = null;
        Node<T> currentNode = head;
        Node<T> currentNodeNext = head.getNext();

        if (currentNodeNext == null) {
            t = currentNode.getValue();
            currentNode = null;
            return t;
        }

        while (currentNode != null) {

            if (currentNodeNext.next == null) {
                currentNode.next = null;
                t = currentNodeNext.getValue();
                return t;
            }
            currentNode = currentNode.getNext();
            currentNodeNext = currentNode.getNext();

        }
        return t;
    }

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

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ForwardLinked<String> forwardLinked = new ForwardLinked<>();
        forwardLinked.add("First");
        //forwardLinked.add("Second");
        //forwardLinked.add("tree");
        //forwardLinked.add("Second11");
        //forwardLinked.add("tree11");
        // forwardLinked.deleteFirst();
        //forwardLinked.deleteLast();
        //System.out.println(forwardLinked.deleteFirst());
        //System.out.println(forwardLinked.deleteFirst());
        System.out.println(forwardLinked.deleteLast());
        System.out.println(forwardLinked.get(1));
        System.out.println(forwardLinked.get(2));
        System.out.println(forwardLinked.get(3));
        System.out.println(forwardLinked.get(4));
        System.out.println(forwardLinked.get(5));

    }
}
