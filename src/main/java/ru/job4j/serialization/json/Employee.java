package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

/**
 * 2. Формат JSON [#471733]
 * Придумайте Java объект, объект должен иметь поля булево, какой-нибудь числовой тип,
 * строковый тип, вложенный объект и массив.
 * 2) Опишите его представление в формате JSON в комментарии к заданию.
 * 3) Подключите библиотеку Gson, преобразуйте объект вашего класса в json-строку и обратно.
 */
public class Employee {
    private final boolean marriage;
    private final int age;
    private final ContactCard contact;
    private final String[] statuses;

    public Employee(boolean marriage, int age, ContactCard contact, String... statuses) {
        this.marriage = marriage;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "marriage=" + marriage
                + ", age=" + age
                + ", ContactCard=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Employee employee = new Employee(true, 65,
                new ContactCard("3194805555", "NewYork", "QuencySt", 125),
                "Son", "Father", "Grandfather",  "retiree");
        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println("Employee go to Gson: " + gson.toJson(employee));

        //Модифицируем  json-строку
       final String employeeJson =
                "{"
                        + "\"marriage\":true,"
                        + "\"age\":65,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"3194805555\","
                        + "\"town\":\"NewYork\","
                        + "\"street\":\"QuencySt\","
                        + "\"house\":\"125\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Son\",\"Father\",\"Grandfather\",\"retiree\"]"
                        + "}";
        final Employee employeeAfterJS = gson.fromJson(employeeJson, Employee.class);
        System.out.println("Person out JSON: " + employeeAfterJS);
    }
}
