package ru.job4j.io;

import java.io.File;
import java.util.Arrays;

/**
 * 4.0. File [#471723]
 * 1. Доработайте программу ru.job4j.io.Dir,
 * так что бы она выводила только имя файла и его размер.
 * Размер файла измеряется методом length()
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        //1. Проверяем, что файл существует.
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        //2. Проверяет, что файл - это директория.
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        //3. Получаем список файлов в этой директории.
        //System.out.println(String.format("size : %s", file.getTotalSpace()));
        //for (File subfile : file.listFiles()) {
        //            System.out.println(subfile.getAbsoluteFile());
        //        }
        for (File subfile : file.listFiles()) { // вариант 2
            // System.out.println(String.format("length : %s", subfile.length()));
            System.out.println("new dir: " + subfile);
            String[] rsl = subfile.list();
            for (int i = 0; i < rsl.length; i++) {
                System.out.println("file name: " + rsl[i]);
                System.out.println("file length: " + rsl[i].length());
            }
        }
    }
}
