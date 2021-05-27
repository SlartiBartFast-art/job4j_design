package ru.job4j.search;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Блок IO Котрольное задание Поиск файлов по критерию [#471739]
 * Переехавшая платфорама на новый ресурс Поиск файлов по критерию [#783]
 * 1. Создать программу для поиска файла. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через
 * java -jar find.jar -d=c:/projects/ -n=*.txt -t=mask -o=log.txt
 * или
 * java -jar find.jar -d=c:/projects/ -n=имя файла(например Find) -t=name -o=log.txt
 * (последняя сохранненная версия в log.txt - поиск по имени файла и маске - name)
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл: find.jar
 * find.jar находится в папке targer
 * Сборка через maven
 * Запуск собранного архива find.jar через консоль java -jar find.jar -d=c:/projects/ -n=*.txt -t=mask -o=log.txt
 */
public class Finish {
    private SearchFile searchFile;
    private ArgsName argsName;
    private SearchFileToFile searchFileToFile;

    public Finish(String[] args) throws IOException { // принимаем массив аргументов
        this.argsName = ArgsName.of(args); //записали ключи
        this.searchFileToFile = new SearchFileToFile(); // есть метод записи в фаил
        records(); // запуск всех приват методов и запись результатов в файл
    }

    /**
     * Метод просматривает выбирает подходящий предикат в зависимости от типа маски
     * @return предикат соовествующий указанной в консеоли маске
     */
    private Predicate<String> run() {
        Predicate<String> predicate = null; // вариант №2
        if (argsName.get("t").equals("mask")) {
            String str = argsName.get("n"); //-n - имя файла, маска, либо регулярное выражение.
            String[] s = str.split("\\*");
            String regx = s[1]; // получим .txt
            Pattern pattern = Pattern.compile(regx);
            predicate = pattern.asPredicate();
        } else if (argsName.get("t").equals("regExp")) {
            String regx = regex(argsName.get("n")); // получим .*\.txt
            // String regx = argsName.get("n"); //-n - имя файла, маска, либо регулярное выражение.
            Pattern pattern = Pattern.compile(regx);
            predicate = pattern.asPredicate();
        } else if (argsName.get("t").equals("name")) {
            String regx = buildert(argsName.get("n")); // получим ^*.txt.+ где ^имя файла.+
            System.out.println("Ищем : " + regx);
            Pattern pattern = Pattern.compile(regx);
            predicate = pattern.asPredicate();
        }
       /* Predicate<Path> predicate = null; // вариант №1
        if (argsName.get("t").equals("name")) {
            predicate = path -> path.toFile().getName().equals(argsName.get("n"));
        } else if (argsName.get("t").equals("mask")) {
            // System.out.println("vzal nyjnyi predicat: ");
            predicate = path -> path.toFile().getName().endsWith(argsName.get("n"));
        } else if (argsName.get("t").equals("regExp")) {
            predicate = path -> path.toFile().getName().endsWith(argsName.get("n"));
        }*/
        return predicate;
    }

    /**
     * Метод вспомогательный, для создания регулярки поиск по имени
     * @param str имя файла для пойска
     * @return стровое представление для regExp
     */
    private static String buildert(String str) {
        return "^" + str + ".+";
    }

    /**
     * Метод переделывает ключ в случае поиска по mask под regExp
     * @param mask строковое представление ключа по маске
     * @return переделанное выражение для поиска по regExp
     */
    private static String regex(String mask) {
        var builder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            var symbol = mask.charAt(i);
            if (symbol == '*') {
                //Метод append() — обновляет значение объекта, который вызвал метод.
                // Этот метод в Java принимает boolean, char, int, long, Strings и т.д.
                builder.append(".*");
            } else if (symbol == '.') {
                builder.append("\\.");
            } else {
                builder.append(symbol);
            }
        }
        return builder.toString();
    }

    /**
     * проводит поиск по всему указанному каталогу(дереву), пишет все в List<Path>
     *
     * @param root      путь с которого начинать поиск
     * @param condition предикат для уловия соотвествия
     * @return List<Path>
     * @throws IOException
     */
    private List<Path> searcher(Path root, Predicate<String> condition) throws IOException {
        this.searchFile = new SearchFile(run()); //создали объект где есть метод обхода дереве
        var t = argsName.get("d");
        Files.walkFileTree(Path.of(t), searchFile);
        return searchFile.getPaths();
    }

    /**
     * Метод проводит запись в указанный файл,
     * записывает объекты  отфильтрованные по предикату
     *
     * @throws IOException
     */
    private void records() throws IOException {
        var t = argsName.get("d");
        searchFileToFile.packFiles(searcher(Paths.get(argsName.get("d")), run()), argsName.get("o"));
    }

    public static void main(String[] args) throws IOException {
        //args = new String[]{"-d=c:/projects/", "-n=.txt", "-t=mask", "-o=log.txt"};
        Finish finish = new Finish(args);
         /*
        if (argsName.get("t").equals("mask")) {
            String regx = "mask"; // то что ищем по n
            String actualStr = argsName.get("t"); // то где ищем полностью путь + это объекат Path
            Pattern pattern = Pattern.compile(regx);
            //возвращает предикат, который можно использовать для сопоставления строки.
            Predicate<String> predicateP = pattern.asPredicate();
            boolean value = predicateP.test(actualStr);*/
    }
}
