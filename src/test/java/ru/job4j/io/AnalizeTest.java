package ru.job4j.io;

import static org.junit.Assert.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
//аннотация на потобе Before только она едения для все тестов
import org.junit.Rule;
import org.junit.Test;
//используется для создания объекта Временная папка
//будет почишена после тестов
import org.junit.rules.TemporaryFolder;

import java.io.*;

/**
 * 3.0. Тестирование IO [#471718]
 * Вернитесь к задаче "2. Анализ доступности сервера".
 * Создайте на код тесты с классом TemporaryFolder.
 */
public class AnalizeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException { // указали эксепшен для IO
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) { //заполнии исходный файл
            out.println("200 10:55:01");
            out.println("500 10:56:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:01");
            out.println("200 11:03:01");
        }
        //getAbsolutePath() является частью класса File .
        // Эта функция возвращает абсолютный путь к указанному
        // файловому объекту. Если путь к файловому объекту является абсолютным,
        // то она просто возвращает путь к текущему файловому объекту.
        //Analize.unavailable - вызом метода через объект где этот метод прописан.
        //метод в таком случе д.б. static для участия в тесте
        Analize.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        //чтобы узнать каков итог работы метода, код ниже
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
            //https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
            //    Метод append() — обновляет значение объекта, который вызвал метод.
            //    Этот метод в Java принимает boolean, char, int, long, Strings и т.д.
        }
        assertThat(rsl.toString(), is("10:56:01;10:59:01" +
                "11:01:01;11:03:01"));
    }
}