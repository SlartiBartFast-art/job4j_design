package ru.job4j.io;

import java.io.*;
import java.util.stream.Stream;

//TODO 1. Реализуйте метод unavailable.
//source - имя файла лога
//target - имя файла после анализа.
//Метод anavailable должен находить диапазоны, когда сервер не работал.
//Сервер не работал. если status = 400 или 500.

/**
 * 2. Анализ доступности сервера. [#471727]
 * Метод main - записывает текст в файл "unavailable.csv" *
 */
public class Analize {

    /**
     * Метод anavailable должен находить диапазоны, когда сервер не работал
     * Сервер не работал. если status = 400 или 500.
     *
     * @param source - имя файла лога
     * @param target - имя файла после анализа.
     */
    public static void unavailable(String source, String target) {
        try (var buffer = new BufferedReader(new FileReader(source))) {
            String[] strings = new String[2];
            // отсортировать на у которых есть 400 500  скидать в файл таргет
            buffer.lines().forEach(split -> {
                if (strings[0] == null && split.contains("400") || split.contains("500")) {
                    strings[0] = split;
                }
                if ((strings[0] != null && strings[1] == null) && split.contains("200") || split.contains("300")) {
                    strings[1] = split;
                }
                if (strings[0] != null && strings[1] != null) {
                    System.out.println(split);
                    try (var rsl = new BufferedWriter(new FileWriter(target, true))) {
                        rsl.write(strings[0] + ": " + strings[1]);
                        rsl.newLine();
                        strings[0] = null;
                        strings[1] = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод main - записывает текст в файл "unavailable.csv"
     *
     * @param args
     */
    public static void main(String[] args) {
       /* try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.txt"))) {
            out.println("200 10:55:01");
            out.println("500 10:56:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:01");
            out.println("200 11:03:01");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Analize analize = new Analize();
        analize.unavailable("unavailable.txt", "targetFile.txt");
    }
}
