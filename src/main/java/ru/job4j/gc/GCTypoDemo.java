package ru.job4j.gc;

import java.util.Random;

/**
 * 0. Виды сборщиков мусора [#6852 #178905]
 * Топик : 2.4.2. Виды сборщиков мусоров
 * Запуск приложения с различными сборщиками мусора
 * Для начала стоит отметить флаг -Xlog:gc* (до JDK 9 нужно использовать -XX:-PrintGCDetails)
 * если мы его зададим то, сможем увидеть лог сборщика в консоли
 * Ключи для запуска:
 * - Serial => -XX:+UseSerialGC
 * - Parallel => -XX:+UseParallelGC
 * - CMS => -XX:+UseConcMarkSweepGC (допуступен до JDK 14)
 * - G1 => -XX:+UseG1GC
 * - ZGC => -XX:+UseZGC
 */
public class GCTypoDemo {
    /**
     * Будем запускать такую программу. Она составляет строку
     * из продублированного несколько раз символа, при этом перезаписывая очейки массивы.
     *
     * @param args массива аргументов
     */
    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}