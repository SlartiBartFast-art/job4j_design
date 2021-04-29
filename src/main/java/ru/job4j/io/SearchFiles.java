package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы. [#471717]
 * Разработайте программу Search, которая будет искать файлы
 * только по определенному предикату.
 * программа должна вернуть файлы с расширением js.
 */
public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> condition;
    private List<Path> pathList;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        this.pathList = new ArrayList<>();
    }

    public List<Path> getPaths() {
        return pathList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //  System.out.println(file.toAbsolutePath());
        if (condition.test(file.toAbsolutePath())) {
            pathList.add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
