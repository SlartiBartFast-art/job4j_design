package ru.job4j.io;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Java: как открыть папку, содержащую нужный файл.
 * программа открывает папку автоматически после запуска,
 * в Виндовс открывается папка в новом окне на рабочем столе
 * так как данный класс находится в папке Design открывается именно эта папка после запуска метода майн
 */
public class Shell {
    public static void main(String[] args) {
        File file = new File("c:"); //для ОС Windows
        Desktop desktop = null;

        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            desktop.open(file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
   // File file = new File("."); //для Unix ОС

}
