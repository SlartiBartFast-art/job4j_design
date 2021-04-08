package ru.job4j.tree;

import java.util.*;

/**
 * Создать элементарную структуру дерева [#471689]
 * По заданию в дереве не могут храниться дубликаты.
 *
 * @param <E>
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Должен находить узел по значению parent и добавлять в него дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще нет в дереве а parent есть.
     * Если child есть, то метод должен вернуть false.
     * @param parent искомый ключ
     * @param child  ключ для добавления
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        var node = findBy(parent); // получили узер по значение Родитель (обернут Опционал)
        // через метод перестраховывемся и указываем что вывести в случае если Опционал пуст
        var etr = node.orElse(null);
        if (etr == null) { // получить false т.к. объект Опционал пуст
            return rsl;
        }
        //Если child есть, то метод должен вернуть false
        for (Node n : etr.children) {
            if (n.value.equals(child)) {
                return rsl;
            }
        }

        // добавить в него узел со значением child
        etr.children.add(new Node<E>(child));
        rsl = true;
        return rsl;
    }

    /**
     * Метод алгоритм обхода в ширину SimpleTree
     * @param value искомое значение
     * @return объект типа Optional в котором содержиться экземпляр значения Node или null
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty(); // создаем пустой опционал объект
        Queue<Node<E>> data = new LinkedList<>();
        // Метод offer (E e) интерфейса  Queue (очереди) вставляет указанный элемент в эту очередь,
        // если это можно сделать немедленно, не нарушая ограничения по емкости.
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                //Метод of () используется для получения экземпляра этого дополнительного класса
                // с указанным значением указанного типа.создания дополнительного экземпляра с этим значением.
                //возвращает экземпляр этого дополнительного класса с указанным значением указанного типа.
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SimpleTree.class.getSimpleName() + "[", "]")
                .add("root=" + root)
                .toString();
    }

    public static void main(String[] args) {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        System.out.println(tree);
        boolean tir = tree.add(7, 5);
        System.out.println(tir);
    }
}
