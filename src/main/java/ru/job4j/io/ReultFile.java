package ru.job4j.io;

import java.io.*;

/**
 * 0,1. FileOutputStream. [# 471720]
 * вывод таблицы умножения на консоль.
 * Вам нужно записать результат вычисления в файл.
 * вариант №2
 */
public class ReultFile {
    public static void main(String[] args) {

        try (var out = new DataOutputStream(new FileOutputStream("result.txt"))) {
            int[][] table = new int[10][10];
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {

                    table[i][j] = (i + 1) * (j + 1);
                    out.writeInt(table[i][j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
