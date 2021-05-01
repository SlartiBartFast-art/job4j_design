package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

/**
 * 4.2. Поиск дубликатов [#471725]
 * Нужно написать программу, которая принимает на вход папку, просматривает все файлы в ней
 * (и всех подпапках и под-под-...папках) и сообщает, если находит дубликаты.
 * Дубликаты – это два файла с одинаковым именем и размером.
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        /* Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());*/
        Function<List<Path>, List<FileProperty>> function = path -> {
            Set<FileProperty> sete = new HashSet<>();
            List<FileProperty> fileProperties = new ArrayList<>();
            for (Path p : path) {
                try {
                    var tr = new FileProperty(Files.size(p), p.toFile().getName());
                    if (!sete.add(tr)) {
                        fileProperties.add(tr); // в лист переписать
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return fileProperties;
        };
        Path start = Paths.get(".");
        search(start, function).forEach(System.out::println);
    }

    public static List<FileProperty> search(Path root, Function<List<Path>, List<FileProperty>> function) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor(function);
        Files.walkFileTree(root, duplicatesVisitor);
        return duplicatesVisitor.getFileProperties();
    }
}

