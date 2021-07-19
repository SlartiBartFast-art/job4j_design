package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
public class Emulator extends DirFileCache {
    List<Path> pathList = new ArrayList<>();

    public Emulator(String cachingDir) {
        DirFileCache dirFileCache = new DirFileCache(cachingDir);
        reader(dirFileCache.getCachingDir());
    }

    /**
     * Метод проводить запись всех путей файлов в указанной директории в List
     *
     * @param cachingDir String name files directory
     * @return List<String> files in directory of start parameter
     */
    private List<Path> reader(String cachingDir) {
        //Path.of(cachingDir); // получили объект Path директории

        Path path = Paths.get(cachingDir);
        try (DirectoryStream<Path> directoryStream =
                     Files.newDirectoryStream(Paths.get(cachingDir))) { // получили стрим всех путей/файлов из каталога
            for (Path path1 : directoryStream) {
                Path nameFile = path1.getFileName();//имя файла
                String nameStr = nameFile.toString();
//теперь надо записать что в файле
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nameStr))) {
                    String value = bufferedReader.readLine(); // записали в значение то что в файле
                    if (load(nameStr) != null) {
                        put(nameStr, value); // записавли в Мапу из АбстрактКеш
                    }
                    pathList.add(path1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathList;
    }

    /**
     * Метод проводит поиск в существующей Мап объекта по key (String file name)
     *
     * @param key имя файла для загрузки в Мап
     * @return имя файла в качестве занчения key для Map or null if String key received from a Map
     */
    @Override
    protected String load(String key) {
        String string = null;
        if (cache.get(key) == null) {
            string = key;
            return key;
        }
        return string;
    }

    /**
     * метод записывает в Map<K, V>
     *
     * @param key   String имя объекта(файла)
     * @param value (содержимое файла)
     */
    @Override
    public void put(String key, String value) {
        SoftReference softReference = new SoftReference(value);
        cache.put(key, softReference);
    }

    /**
     * Метод проверяет есть ли в Мап данный файл
     * Возвращает содержимое по ключу файла
     *
     * @param key
     * @return String содержимое Value  - Map(-, Value)
     */
    @Override
    public String get(String key) {
        var softReference = cache.get(key);
        return softReference.get();
    }
}
