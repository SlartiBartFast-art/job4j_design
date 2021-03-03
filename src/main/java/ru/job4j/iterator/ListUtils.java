package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    /**
     * addBefore() вставляет до индекса;
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * addAfter() вставляет после индекса;
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * removeIf() удаляет все элементы, которые удовлетворяют предикату.
     * Запрещено использовать метод List.removeIf;
     */
    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
            }
        }

        return list;
    }

    /**
     * replaceIf() заменяет все элементы, которые удовлетворяют предикату;
     */
    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        if (list == null) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < list.size(); i++) {
            if (filter.test(list.get(i))) {
                list.set(i, value);
            }
        }
        return list;
    }

    /**
     * removeAll() удаляет из списка те элементы, которые есть в elements.
     * Запрещено использовать метод List.removeAll().
     */
    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        if (list == null) {
            throw new NoSuchElementException();
        }
        for (T value : elements) {
            if (list.contains(value)) {
                list.remove(value);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListIterator<Integer> i = list.listIterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
