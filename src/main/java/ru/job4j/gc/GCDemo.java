package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.*; 
// для получения метода sizeOf(int number) sizeOf(Object object)

/**
 * 0. Понятие сборки мусора [#6851 #178314]
 * Структура памяти,пример работы с принудительной сборкой мусора
 * получеение размера объекта через привязку dependency(groupId - com.carrotsearch) in to pom.xml
 * и метода sizeOf(int number)
 * sizeOf(Object object)
 */
public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10; i++) {
            System.out.println(sizeOf(new Person(i, "N" + i)) + " " + i);
        }       
        System.gc(); // принудетельная чистка garbage
        info();
    }
}
