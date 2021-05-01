package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Function;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> listPath;
    private Function<List<Path>, List<FileProperty>> function;
    private List<FileProperty> fileProperties;

    public DuplicatesVisitor(Function<List<Path>, List<FileProperty>> function) {
        this.function = function;
        this.fileProperties = new ArrayList<>();
        this.listPath = new ArrayList<>();
    }

    public List<FileProperty> getFileProperties() {
        fileProperties = function.apply(listPath); // прогнали весь Аррайлист через функцию
        return fileProperties;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        listPath.add(file.toAbsolutePath()); // добавили все файлы полностью строка пути в Лист
        return super.visitFile(file, attrs);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DuplicatesVisitor.class.getSimpleName() + "[", "]")
                .add("function=" + function)
                .add("fileProperties=" + fileProperties)
                .toString();
    }
}
