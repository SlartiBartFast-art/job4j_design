package ru.job4j.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Аналог задания 5.1. Именованные аргументы [#471724]
 * часть сборки задания, Поиск файлов по критерию [#783 #158176]
 * Класс принимает массив параметров и разбивает их на пары: ключ, значение.
 * * Exception - массив аргументов пуст, value - пусто
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        //validation
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(args).forEach(p -> {
            System.out.println(p);
            String[] str = p.substring(1).split("=");
            if (str.length < 2) {
                throw new IllegalArgumentException();
            }
            System.out.println("Dlina massiva: " + str.length);

            values.put(str[0], str[1]);
            System.out.println("key map " + str[0] + " value map " + str[1]);
        });
        System.out.println("to cho zashlo in Map" + values);
    }

    /**
     * Метод возвращаем объект ArgsName с уже созданной Мапой из массива аргументов
     */
    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ru.job4j.io.ArgsName.class.getSimpleName() + "[", "]")
                .add("values=" + values)
                .toString();
    }

    public static void main(String[] args) {
       /* ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));*/
       /* ArgsName tmp = ArgsName.of(new String[]{"-d=c:/", "-n=*.txt", "-t=mask", "-o=log.txt"});
        for (var st : tmp.values.entrySet()) {
            System.out.println(st);
        }*/
    }
}

