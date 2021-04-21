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
     * @param log название файла в коротом нужно провести фильтрацию
     * @return название файла в который будут записаны результаты фильтрации
     */
    public static String save(String log) {
        String string;
        List<String> saveLog;
        saveLog = filter(log);
        try (PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream("writeLog.txt")))) {
            saveLog.forEach(x -> printWriter.printf("%s%n", x));

        } catch (Exception e) {
            e.printStackTrace();
        }

        string = "writeLog.txt";
        return string; // записать название файла
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        String string = save("log.txt");
    }
}
