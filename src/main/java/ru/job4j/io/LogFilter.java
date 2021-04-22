package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.3. BufferedReader. [#471721]
 * Создайте файл log.txt. Запишите в него текст.
 * Метод filter должен прочитать файл и вернуть строки, где предпоследнее число - это 404.
 * 0.4. BufferedOutputStream [#471722]
 * Нужно добавить метод save(String log). Метод должен записывать результат фильтрации в файл
 */
public class LogFilter {

    /**
     * Метод должен прочитать файл и вернуть строки, где предпоследнее число - это 404.
     * @param file название файла где храняться исходные данне
     * @return возвращает коллекцию с результатами работы метода
     */
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.lines().filter(x -> x.contains("404")).forEach(list::add);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Метод записывает результаты фильтрации в файл
     * @param log отсортированная коллекция хранящаяя результаты работы метода filter(как пример)
     * @param file название файлу куда необходимо записать отсортированную коллекцию
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(x -> printWriter.printf("%s%n", x));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt"); // результат работы filter
        System.out.println(log);
        save(log, "save.txt"); //результат работы save(созданный файл в корне проекта)
    }
}
