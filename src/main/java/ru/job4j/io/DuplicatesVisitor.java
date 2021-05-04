package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * 4.2. Поиск дубликатов [#471725]
 * Нужно написать программу, которая принимает на вход папку, просматривает все файлы в ней
 * (и всех подпапках и под-под-...папках) и сообщает, если находит дубликаты.
 * Дубликаты – это два файла с одинаковым именем и размером.
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<FileProperty> fileProperties;
    private Set<FileProperty> setFileProp;

    public DuplicatesVisitor() {
        this.fileProperties = new ArrayList<>();
        this.setFileProp = new HashSet<>();
    }

    public List<FileProperty> getFileProperties() {
        return fileProperties;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var path = file.toAbsolutePath();
        var tr = new FileProperty(Files.size(path), path.toFile().getName());
        if (!setFileProp.add(tr)) {
            fileProperties.add(tr);
        }
        //listPath.add(file.toAbsolutePath()); // добавили все файлы полностью строка пути в Лист
        return super.visitFile(file, attrs);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DuplicatesVisitor.class.getSimpleName() + "[", "]")
                .add("fileProperties=" + fileProperties)
                .add("setFileProp=" + setFileProp)
                .toString();
    }
}
