package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Temp {
    private void replace(List<String> list) {
        String path = "answerDelete.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (String string : list) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
                // bufferedWriter.write("\r\n");
                // bufferedWriter.write(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       /* System.out.print("primer chto zapiset");
       // String g = System.lineSeparator();
        //System.out.println(g);
        System.out.println(System.lineSeparator() + "chto vivelfggfd");
        System.out.println("kflkslkf");
        System.out.println("\r\n");
        System.out.println("kmfklmslkdmf");
        System.out.println(System.lineSeparator() + "chto vivel234");
        System.out.println(System.lineSeparator() + "chto vivel");
       // System.out.println(System.lineSeparator());
        System.out.println("chto vivel2");*/
        List<String> list = new ArrayList<>();
        list.add("1. Я высокий зеленоглазый брюнет. А ты как выглядишь?");
list.add("2. Что-то мне приключений захотелось... Почудим?");
        list.add("3. Боты, как люди, разными бывают.");
        list.add("4. Малыш, а как же я? Я же лучше собаки))");
        list.add("5. Хай! Я Ванек. А ты?");

        Temp temp = new Temp();
        temp.replace(list);

    }
}
