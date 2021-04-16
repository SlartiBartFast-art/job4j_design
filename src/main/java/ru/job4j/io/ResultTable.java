package ru.job4j.io;

import java.io.*;

/**
 * 0,1. FileOutputStream. [# 471720]
 * вывод таблицы умножения на консоль.
 * Вам нужно записать результат вычисления в файл.
 */
public class ResultTable {

    public static void main(String[] args) throws IOException {

        int[][] table = new int[10][10];
        File file = new File("temp1.txt");
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {

                table[i][j] = (i + 1) * (j + 1);

                fileWriter.append(" " + table[i][j]);
            }
            fileWriter.newLine();
        }

        fileWriter.close();
    }
}
