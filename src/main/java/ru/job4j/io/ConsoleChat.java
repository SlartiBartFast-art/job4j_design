package ru.job4j.io;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 6. Кодировка. [#471729]
 * В этом задании необходимо создать программу 'Консольный чат'.
 * - пользователь вводит слово-фразу, программа берет случайную
 * фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп»,
 * при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть
 * записаны в текстовый лог.
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private List<String> answerTxt;
    private List<String> logAllTalks;

    /**
     * @param path       имя файла, в который будет записан весь диалог между ботом и пользователем
     * @param botAnswers имя файла в котором находятся строки с ответами,
     *                   которые будет использовать бот
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.answerTxt = new ArrayList<>();
        this.logAllTalks = new ArrayList<>();
    }

    /**
     * Метод ведет диалог от имени бота с пользователем
     * открывает ввод данных с клавиатуры и записывает весь диалог с ботом в фаил по ссылке path
     */
    public void run() {
        getAnswerTxt();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String str;
            String f = "Добро пожаловать, введите ваш запрос! ";
            String s = "Введите стоп для приостановки общения.";
            String z = "Введите закончить для завершения.";
            System.out.println(f);
            System.out.println(s);
            System.out.println(z);
            logAllTalks.add(f);
            logAllTalks.add(s);
            logAllTalks.add(z);

            do {
                str = bufferedReader.readLine();
                logAllTalks.add(str);
                //System.out.println(str);
                if (str.equals(STOP)) {
                    String s1 = "Вы меня отключили а мы так хорошо общались, "
                            + "чтобы продолжить диалог введите - продолжить";
                    logAllTalks.add(s1);
                    System.out.println(s1);
                    do {
                        str = bufferedReader.readLine();
                        logAllTalks.add(str);
                    } while (!str.equals(CONTINUE));

                }
                String string = answerTxt.get(random());
                System.out.println(string);
                logAllTalks.add(string);
            } while (!str.equals(OUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        replace(logAllTalks);
    }

    /**
     * Метод производить запись(логирование) всегод диалога между ботом и пользователем в фаил
     * @param list принимает List<String> с фразами диалога
     */
    private void replace(List<String> list) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (String string : list) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записывает варианты ответов бота в List
     *
     * @return List<String> ответов бота
     */
    private List<String> getAnswerTxt() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(p -> {
                // System.out.println(p);
                answerTxt.add(p);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answerTxt;
    }

    /**
     * Метод возвращает произвольное число
     *
     * @return число в диапазоне длины List<String> answerTxt типа int
     */
    private int random() {
        int max = (answerTxt.size() + 1);
        return (int) (Math.random() * max);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConsoleChat.class.getSimpleName() + "[", "]")
                .add("path='" + path + "'")
                .add("botAnswers='" + botAnswers + "'")
                .add("answerTxt=" + answerTxt)
                .add("logAllTalks=" + logAllTalks)
                .toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
       /* ConsoleChat cc = new ConsoleChat("", "");
        cc.run();*/
        ConsoleChat consoleChat = new ConsoleChat("dialog.txt", "answer.txt");
        consoleChat.getAnswerTxt();
        consoleChat.run();
        /*System.out.println("List" + consoleChat.getAnswerTxtGet());
        System.out.println("random " + consoleChat.random());*/
    }
}
