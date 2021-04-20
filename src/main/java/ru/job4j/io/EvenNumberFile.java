package ru.job4j.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * 0.2. FileInputStream [#471730]
 *  В классе нужно прочитать файл even.txt.
 *  В файл добавьте числа. Одно число  на строку.
 *  Для каждого числа проверить является ли оно четным числом.
 *  Ответ вывести на консоль.
 */
public class EvenNumberFile {
    public static void main(String[] args) throws FileNotFoundException {
        var file = new FileInputStream("even.txt");
        try (var out = new Scanner(file)) {
            int rsl;
            while (out.hasNext()) {
                rsl = out.nextInt();
                if (rsl % 2 == 0) {
                    System.out.println("Это число является четным :" + rsl);
                } else {
                    System.out.println("Это число не четное : " + rsl);
                }
            }
        }
    }
}




