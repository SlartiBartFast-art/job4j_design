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
 * 5. Валидация параметров запуска. [#471719]
 * Изменить программу, чтобы начальная папка передавалась через аргументы запуска.
 * Доработайте программу ru.job4j.io.Search. Программа должна запускаться с параметрами.
 * Первый параметр - начальная папка.
 * Второй параметр - расширение файлов, которые нужно искать.
 */
public class Search {
    public static void main(String[] args) throws IOException {
      /* Path start = Paths.get("."); // текущая директория
      //выводит все абсолютный путь все файлов дирректории
       Files.walkFileTree(start, new PrintFiles());*/
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Root folder is null or File extension not specified. Usage java -jar Search.jar ROOT_FOLDER.");
        }
        Path start = Paths.get(args[0]);
        String string = args[1];
        Predicate<Path> test = p -> p.toFile().getName().endsWith(string);
        search(start, test).forEach(System.out::println);
        /*Path start = Paths.get(".");
         String string = "js";
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith("js")).forEach(System.out::println);*/
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
