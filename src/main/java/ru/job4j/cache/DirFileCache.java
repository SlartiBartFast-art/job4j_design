package ru.job4j.cache;


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

    public DirFileCache() {
    }

    public DirFileCache(String cachingDir) {
        //this.cachingDir.set = cachingDir;
        setCachingDir(cachingDir);
    }

    public String getCachingDir() {
        return cachingDir;
    }

    public void setCachingDir(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        return null;
    }
}
