package ru.job4j.collection;

import java.util.*;

public class LinkedList<T> implements Iterable<T> { //контейнер
    private int size = 0; // количество добавленных элементов в список
    private Node<T> nodeFirst; //первый узел в списке
    private Node<T> nodeLast; //последний узел

    private int modCount = 0; // сколь раз изменен с момента создания

    public LinkedList() {
        nodeLast = new Node<T>(null, nodeFirst, null);
        nodeFirst = new Node<T>(null, null, nodeLast);
    }

    /**
     * Метод производит добавление элемента в контейнер
     */
    public void add(T value) { //добавляет в конец
        Node<T> prev = nodeLast;
        prev.setElement(value);
        nodeLast = new Node<T>(null, prev, null);
        prev.setNextElementNode(nodeLast);
        size++;
        modCount++;
    }

    /**
     * Метод позволяет олучить значение Node элемента контейнера по индексу
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> rsl = nodeFirst.getNextElementNode();
        for (int i = 0; i < index; i++) {
            rsl = getNextEletment(rsl);
        }
        return rsl.getElement();
    }

    private Node<T> getNextEletment(Node<T> current) {
        return current.getNextElementNode();
    }

    /**
     * Метод позволяет получить размер списка,
     * в соответсвии с колличеством добавленных элементов в список
     */
    public int size() {
        return size;
    }

    public void addFirst(T t) {
        Node<T> next = nodeFirst;
        next.setElement(t);
        nodeFirst = new Node<>(null, null, next);
        next.setPrev(nodeFirst);
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;
            private int expectModCount = modCount;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(count++);
            }
        };
    }

    private class Node<T> {
        private T element;
        private Node<T> nextElement;
        private Node<T> prev;

        private Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.nextElement = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Node<T> getNextElementNode() {
            return nextElement;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setNextElementNode(Node<T> next) {
            this.nextElement = next;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

    }

}

