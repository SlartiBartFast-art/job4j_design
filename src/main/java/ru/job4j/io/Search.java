package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы. [#471717]
 * Разработайте программу Search, которая будет искать файлы
 * только по определенному предикату.
 * программа должна вернуть файлы с расширением js.
 */
public class Search {
    public static void main(String[] args) throws IOException {
      /* Path start = Paths.get("."); // текущая директория
      //выводит все абсолютный путь все файлов дирректории
       Files.walkFileTree(start, new PrintFiles());*/
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith("js")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
