package ru.job4j.search;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Поиск файлов по критерию [#471739] в старром трекер
 * Поиск файлов по критерию [#783 #158176] новый трекер
 * поиск по каталогам + Predicates
 * класс расширяет class SimpleFileVisitor (implements FileVisitor)
 */
public class SearchFile extends SimpleFileVisitor<Path> {
    private Predicate<String> condition; //Predicate<Path> condition было
    private List<Path> pathList;

    public SearchFile(Predicate<String> condition) { // было Predicate<Path> condition
        this.condition = condition;
        this.pathList = new ArrayList<>();
    }

    /**
     * Возвращает пути к объектам отфильтрованным согласно условия
     * @return List<Path>
     */
    public List<Path> getPaths() {
        return pathList;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
       // System.out.println(file.toAbsolutePath());
        if (condition.test(file.toFile().getName())) { // file.toAbsolutePath()
            System.out.println("запись этого файла в лист :" + file.toAbsolutePath());
            pathList.add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

  /*  public static void main(String[] args) throws IOException {
        //ArgsName argsName = new ArgsName();
        ArgsName argsName = ArgsName.of(new String[]{"-d=c:/projects/", "-n=.txt", "-t=mask", "-o=log.txt"});
       Predicate<Path> predicate = path -> path.toFile().getName().endsWith(argsName.get("n"));
        SearchFile searchFile = new SearchFile(predicate);
        var t = argsName.get("d");
        System.out.println("IZ MAPA: " + t);
        Path g = Paths.get(".");
        Files.walkFileTree(g, searchFile);
        for (Path p : searchFile.getPaths()) {
            System.out.println("PUTI in LIST : " + p);
        }
    }*/
}
