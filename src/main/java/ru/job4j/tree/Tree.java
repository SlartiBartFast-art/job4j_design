package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Создать элементарную структуру дерева [#471689]
 * @param <E>
 */
public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    /**
     * Класс Node описывает узел дерева.
     * Узел содержит хранимое значение и ссылки на дочерние узлы.
     * @param <E>
     */
    class Node<E> { // описывает узел дерева
        final E value; // хранимое занчение
        final List<Node<E>> children = new ArrayList<>(); // ссылка на дочерние узлы

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("children=" + children)
                    .toString();
        }
    }
}
