package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * пример для решения задения 0.2. FileInputStream [#471730]
 */
public class Del {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
              //  System.out.println(read);
                text.append((char) read);
                System.out.println("in while__" + text);
            }
            System.out.println(text);
            System.out.println("___");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
