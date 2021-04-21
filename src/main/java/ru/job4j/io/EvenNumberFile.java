package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 0.2. FileInputStream [#471730]
 * В классе нужно прочитать файл even.txt.
 * В файл добавьте числа. Одно число  на строку.
 * Для каждого числа проверить является ли оно четным числом.
 * Ответ вывести на консоль.
 */
public class EvenNumberFile {
    public static void main(String[] args) {

        try (var out = new Scanner(new FileInputStream("even.txt"))) {
            int rsl;
            while (out.hasNext()) {
                rsl = out.nextInt();
                if (rsl % 2 == 0) {
                    System.out.println("Это число является четным :" + rsl);
                } else {
                    System.out.println("Это число нечетное : " + rsl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



