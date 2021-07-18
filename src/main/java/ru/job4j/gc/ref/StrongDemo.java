package ru.job4j.gc.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 0. Виды ссылок [#6854]
 * Категория : 2.4. Garbage CollectionТопик : 2.4.4. Типы ссылок и коллекции
 * Strong Reference
 *Проблема данного типа ссылок является то, что если в программе есть
 * неиспользуемые ссылки на созданные объекты, то они не будут удалены,
 * что приведет к утечке памяти, что в свою очередь может привести к
 * ошибке OutOfMemoryException - ситуации когда программе не хватает выделенной памяти.
 */
public class StrongDemo {

    public static void main(String[] args) throws InterruptedException {
        //example1();
        //example2();
        example3(); // метод переполнения памяти
    }

    /**
     * В методе, мы создаем объект и далее их за'null'яем.
     * Вызываем сборщик мусора и ждем некоторое время.
     * Объекты удаляются, т.к. ссылки на них null
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
//finalized()
// метод высвобождает ресурсы используемые объектом
// Он подчиняется только Java-машине в том смысле, что только она его может вызывать.
// Причем аккурат перед тем, как объект будет уничтожен.
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * В методе, мы создаем объекты вместе с вложенными.
     * Удаляя внешние объекты как в примере выше удаляются и вложенные,
     * не смотря на то что они не null.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object!");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Код этого метода привет к ошибке переполнения памяти,
     * для более быстрого отображения выставить в VM опции аргументы
     * на ограничение выделенной памяти
     * -Xmx4m -Xms4m
     */
    private static void example3() {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}
