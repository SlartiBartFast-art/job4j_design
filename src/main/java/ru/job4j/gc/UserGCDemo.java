package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.*; //получим метод чтобы узнать размер объекат в памяти

/**
 * 1. Демонстрация работы GC [#1589]
 * Создать объект User c полями и перекрытым методом finalize
 * Создать несколько объектов User и руками рассчитать сколько он будет занимать памяти.
 * Нужно найти информацию. Сколько памяти занимает пустой объект без полей.
 * Добиться состояния, когда виртуальная машины вызывает сборщик мусора самостоятельно. За счет ключей xmx.
 * Объяснить поведение программы в текстовом файле.
 */
public class UserGCDemo {
    private static final long KB = 1000;
    //private static final long MB = KB * KB; лишнне
    //минимальная (без компилятора и других средств разработки) реализация виртуальной машины,
    // необходимая для исполнения Java-приложений.
    // Состоит из виртуальной машины Java Virtual Machine и библиотеки Java-классов.
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / KB);
        System.out.printf("Total: %d%n", totalMemory / KB);
        System.out.printf("Max: %d%n", maxMemory / KB);
    }

    public static void main(String[] args) {
        info(); // данные о размере памяти до начала заполенения
        //System.gc(); // принудительная очиста памяти
        for (int i = 0; i < 200; i++) {
            new User(i, "N" + i);
            System.out.println(sizeOf(new User(i, "N" + i)) + " " + i);

        }
        System.out.println(sizeOf(new User()) + "размер пустого");
        System.out.println("Перед запуском метода сборки мусора : ");
        info(); // данные о размере памяти после переполнения
    }
}
