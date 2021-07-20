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
 * Часть 2
 * Создать программу, эмулирующее поведение данного кеша.
 * Программа должна считывать текстовые файлы из системы и выдавать текст
 * при запросе имени файла. Если в кеше файла нет. Кеш должен загрузить себе данные.
 * По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории.
 * Пример. Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 */
public class DirFileCache extends AbstractCache<String, String> {

    private String cachingDir;

    public DirFileCache(String cachingDir) {
        setCachingDir(cachingDir);
        load(cachingDir);
    }

    public String getCachingDir() {
        return cachingDir;
    }

    public void setCachingDir(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Назначение - Указать кешируемую директорию
     * Метод проводить разбор всех файлов в указанной дериктории  на пары ключ/значение,
     * проводит запись этих файлов в Map
     * @param key кешируемая директория
     * @return имя диретории или null если такой директории не существует
     */
    @Override
    protected String load(String key) {
        //Path.of(cachingDir); // получили объект Path директории
        String rsl = key;
        if (rsl != null) {
            Path path = Paths.get(cachingDir);
            try (DirectoryStream<Path> directoryStream =
                         Files.newDirectoryStream(Paths.get(cachingDir))) { // получили стрим всех путей/файлов из каталога
                for (Path path1 : directoryStream) {
                    Path nameFile = path1.getFileName(); //имя файла
                    var abspath = path1.toAbsolutePath(); // абсолютный путь
                    String string = abspath.toString();
                    String nameStr = nameFile.toString();
//теперь надо записать что в файле
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(string))) {
                        String value = bufferedReader.readLine(); // записали в значение то что в файле
                        put(nameStr, value); // записавли в Мапу из АбстрактКеш
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rsl;
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        var softReference = new SoftReference<>(value);
        cache.put(key, softReference);
    }

    @Override
    public String get(String key) {
        String v = null;
        if (cache.get(key) != null) {
            var softReference = cache.get(key);
            System.out.println("method get softReference: " + softReference);
            return softReference.get();
        }
        return v;
    }
}

