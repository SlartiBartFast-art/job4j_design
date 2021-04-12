package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * Создать элементарную структуру дерева [#471689]
 * По заданию в дереве не могут храниться дубликаты.
 * 2. Добавить метод boolean isBinary() [#471690]
 * метод должен циклически пройти по всем элементам дерева, аналогично методу findBy
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
     *
     * @param parent искомый ключ
     * @param child  ключ для добавления
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        var node = findBy(parent); // получили узер по значение Родитель (обернут Опционал)
        //Отсутствие child нужно проверить во всем дереве. Для этого также нужно использовать findBy()
        var childRes = findBy(child);
        if (childRes.isEmpty()) {
            if (node.isPresent()) {
                var etr = node.get();
                etr.children.add(new Node<>(child));
                rsl = true;
                return rsl;
            }
        }
        return rsl;
    }

    /**
     * Метод алгоритм обхода в ширину SimpleTree
     *
     * @param value искомое значение
     * @return объект типа Optional в котором содержиться экземпляр значения Node или null
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        E e = value;
        Optional<Node<E>> rsl = findByPredicate(el -> el.value.equals(e));

        return rsl;
    }

  /*  @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty(); // создаем пустой экземпляр Optional объект
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
    }*/

    /**
     * Метод должен проверять количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное
     *
     * @return
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(el -> el.children.size() > 2).isEmpty();
    }

    //TODO методы isBinary() и findBy() идентичны.
    // Ваша задача отрефакторить код, создав вспомогательный метод.
    // Это метод уже использовать в методах isBinary() и findBy()

    /**
     * @param condition
     * @return
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> nodeOptional = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                nodeOptional = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return nodeOptional;
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
