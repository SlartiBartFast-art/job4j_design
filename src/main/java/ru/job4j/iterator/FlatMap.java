package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

/**
 * Iterator<Integer> - это метод который пробегается по элементам коллекции содержащей Integer
 * Iterator<Iterator<Integer>> - это метод который пробегает по итераторам Iterator<Integer>
 *
 * @param <T>
 */
public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> currentIterator = Collections.emptyIterator();
    // метод используется для получения итератора не имеющего элементов

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (!currentIterator.hasNext() && data.hasNext()) {
            //  data.hasNext() - здесь нужен т.к. в методе происходит вызов next(), перед ним
            // Обязательно! должен быть вызов hasNext()
            currentIterator = data.next();
        }
        return currentIterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentIterator.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        flat.hasNext();
        Integer init =  flat.next();
        System.out.println(init);
        Integer ini1t =  flat.next();
        System.out.println(ini1t);

       /* while (flat.hasNext()) {
            System.out.println(flat.next());
        }*/
        Iterator<Iterator<?>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap fla = new FlatMap(it);
        boolean te = fla.hasNext();
        System.out.println("flat.hasNext--->" + te);
    }
}
