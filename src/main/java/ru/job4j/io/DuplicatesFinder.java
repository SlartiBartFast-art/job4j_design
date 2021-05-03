package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 4.2. Поиск дубликатов [#471725]
 * Нужно написать программу, которая принимает на вход папку, просматривает все файлы в ней
 * (и всех подпапках и под-под-...папках) и сообщает, если находит дубликаты.
 * Дубликаты – это два файла с одинаковым именем и размером.
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        /* Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());*/

        Path start = Paths.get(".");
        search(start).forEach(System.out::println);
    }

    public static List<FileProperty> search(Path root) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(root, duplicatesVisitor);
        return duplicatesVisitor.getFileProperties();
    }
}

