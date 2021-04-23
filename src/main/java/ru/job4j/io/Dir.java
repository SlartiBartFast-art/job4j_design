package ru.job4j.io;

import java.io.File;

/**
 * 4.0. File [#471723]
 * 1. Доработайте программу ru.job4j.io.Dir, так что бы она выводила только имя файла и его размер.
 * Размер файла измеряется методом length()
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        //System.out.println(String.format("size : %s", file.getTotalSpace()));
        System.out.println(String.format("length : %s", file.length()));
        String string = String.format("just name : %s", file);

        String[] rsl = string.split("\\\\");
        System.out.println("file name: " + rsl[1]);
    }
}
