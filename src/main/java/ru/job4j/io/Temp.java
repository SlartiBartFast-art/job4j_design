package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    //    проверка метода Math.random()
//    метод работает но возможен выход за границу массива из-за увеличения значения data1.length + 1
//    правильный вариант data1.length
    private static int example(int[] data1) {
        int max = data1.length;
        return ((int) (Math.random() * data1.length + 1));

    }

    //проверка метода Math.random()
    // data2.length + 1) дает падение и выход за границу массива
    private static void example2(int[] data2) {
        for (int i = 0; i < 1_000_000; i++) {
            int value = data2[((int) (Math.random() * data2.length + 1))];
        }
    }

    //Блок IO/NIO, регулярное выражение
    //переделывает входящую строку, подставляя символы и создает регулярное выражение на основе вход строки
    private static String maskToRegex(String mask) {
        var builder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            var symbol = mask.charAt(i);
            if (symbol == '*') {
                //Метод append() — обновляет значение объекта, который вызвал метод.
                // Этот метод в Java принимает boolean, char, int, long, Strings и т.д.
                builder.append(".*");
            } else if (symbol == '.') {
                builder.append("\\.");
            } else {
                builder.append(symbol);
            }

        }
        return builder.toString();
    }

    //регулярные выражения Блок IO/Nio
// переделывает строку - создает новую с учетом символов треуемых для регулярки
// в частности ^ - указывает что строка начинается с символа за знаком
// .+ - любой символ фигурирует один или более раз
    private static String buildert(String str) {
        // String str = argsName.get("n"); //-n - имя файла, маска, либо регулярное выражение.
        return "^" + str + ".+";
    }

    public static void main(String[] args) {
        /* ServerSocket serverSocket = new ServerSocket(9000);
        Socket clientSocket = serverSocket.accept();
        clientSocket.getOutputStream().write("HTTP/1.1 200 OK\\r\\n\\".getBytes());
        clientSocket.close();
        serverSocket.close();*/
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
     /*   List<String> list = new ArrayList<>();
        list.add("1. Я высокий зеленоглазый брюнет. А ты как выглядишь?");
        list.add("2. Что-то мне приключений захотелось... Почудим?");
        list.add("3. Боты, как люди, разными бывают.");
        list.add("4. Малыш, а как же я? Я же лучше собаки))");
        list.add("5. Хай! Я Ванек. А ты?");*/
//          заполнение файла фразами
//        Temp temp = new Temp();
//        temp.replace(list);

     /*   int[] data = {1, 2, 3, 4, 5};
        System.out.println(Temp.example(data));
        System.out.println(Temp.example(data));
        System.out.println(Temp.example(data));
        System.out.println("DOWN");*/
        // Temp.example2(data);

       /* //регулярные выражения пример
        String a;
        a = "-\\w+=.+"; // -\\w любой символ кроме цифры или знака подчеркивания
        System.out.println(a);
        System.out.println(Pattern.matches(a, "-Xmx=512")); // сравниваем получаем или true false

        String b;
        // -\\w любой символ кроме цифры или знака подчеркивания
        //+ означает один или более символ точка. это любой символ
        b = "-[Xxm]+=.+";
        System.out.println(b);
        System.out.println(Pattern.matches(b, "-Xmx=512")); // сравниваем получаем или true false
        // a = "-\\\\w+=.+";
        a = "-\\\\w+=.+";
        System.out.println(a);
        System.out.println(Pattern.matches(a, "-Xmx=512")); */

        // регулярное выражение меняем mask/шаблон на regExp
        String mask = "*.txt";
        String str = Temp.maskToRegex(mask);
        System.out.println("то что получилосэь :" + str);
        var s = mask.split("\\."); // разделит по . не включая ее * txt
        //var s = mask.split("\\*"); // разделит по * не включая ее .txt

        //System.out.println(s[0]);
        // String regx = s[1];
        for (String g : s) {
            System.out.println(g);
        }
        String t = Temp.buildert(mask);
        System.out.println(t);
        String s2 = " when Find me then delete";
        var match = s2.matches(".*Find.+");
        System.out.println("-Match ^m.+ " + match);
    }
}
