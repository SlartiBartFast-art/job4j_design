package ru.job4j.cache;

/**
 * 1. Реализация кеша на SoftReference [#1592]
 * Категория : 2.4. Garbage Collection
 * Топик : 2.4.4. Типы
 * Часть 3
 * Создать класс Emulator для работы с пользователем. Предоставить пользователю возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 */
public class Emulator {
    DirFileCache dirFileCache;

    public Emulator(String cashStringDir) {
        this.dirFileCache = new DirFileCache(cashStringDir);
    }

    /**
     * Метод записывает в Map<K, V>
     * where Key - file name,Value - file data
     *
     * @param key   tring имя объекта(файла)
     * @param value (содержимое файла)
     */
    public void put(String key, String value) {
        dirFileCache.put(key, value);
    }

    /**
     * Метод проверяет есть ли в Мап данный файл
     * Возвращает содержимое по ключу файла
     *
     * @param key file name
     * @return Sting (содержимое/content Value - file date) or null if file date is empty
     */
    public String get(String key) {
        String v = null;
        if (dirFileCache.get(key) != null) {
            var softReference = dirFileCache.get(key);
            System.out.println("method get softReference: " + softReference);
            return softReference;
        }
        return v;
    }

    public static void main(String[] args) {
        String opisanie = "Столица автоматически переходит в Васюки. " +
                "Сюда приезжает правительство. " +
                "Васюки переименовываются в Нью-Москву, Москва — в Старые Васюки.";
        //директория где находятся файлы
        //String placeDir = "C:/projects/job4j_design/src/main/java/ru/job4j/cache";
        String string = "C:/projects/Temp";
        Emulator emulator = new Emulator(string); // указать кешируемую директорию
//получение по ключу если нет еще такого элемента
        System.out.println("defore - get : " + emulator.get("NewVasyki.txt"));
// вставка ключ/значение
        emulator.put("NewVasyki.txt", opisanie);
// получение значение из Мап по ключу
        System.out.println("after get (vasyki) : " + emulator.get("NewVasyki.txt"));

    }
}
