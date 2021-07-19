package ru.job4j.cache;

import java.nio.file.Path;

public class Temp {
    public static void main(String[] args) {
        Path path1 = Path.of("answer.txt");
        Path nameFile =  path1.getFileName();//имя файла
        String n = nameFile.toString();
        System.out.println("Имя после стринга : " + n);

    }
}
