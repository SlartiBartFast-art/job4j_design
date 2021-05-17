package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * 2. Формат JSON [#471733]
 * Придумайте Java объект, объект должен иметь поля булево, какой-нибудь числовой тип,
 * строковый тип, вложенный объект и массив.
 * 2) Опишите его представление в формате JSON в комментарии к заданию.
 * 3) Подключите библиотеку Gson, преобразуйте объект вашего класса в json-строку и обратно.
 * 5. Преобразование JSON в POJO. JsonObject [#471736]
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

    public boolean isMarriage() {
        return marriage;
    }

    public int getAge() {
        return age;
    }

    public ContactCard getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("marriage=" + marriage)
                .add("age=" + age)
                .add("contact=" + contact)
                .add("statuses=" + Arrays.toString(statuses))
                .toString();
    }

    public static void main(String[] args) {
       /* final Employee employee = new Employee(true, 65,
                new ContactCard("3194805555", "NewYork", "QuencySt", 125),
                "Son", "Father", "Grandfather",  "retiree");
        // Преобразуем объект person в json-строку.
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
        System.out.println("Person out JSON: " + employeeAfterJS);*/

        //5. Преобразование JSON в POJO. JsonObject [#471736]
        JSONObject jsonContactCard = new JSONObject(
                "{\"phone\":\"3194805555\",\"town\":\"NewYork\",\"street\":\"Quency\",\"house\":\"125\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Son");
        list.add("Father");
        list.add("Grandfather");
        list.add("retiree");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Employee employee = new Employee(true, 65,
                new ContactCard("3194805555", "NewYork", "QuencySt", 125),
                "Son", "Father", "Grandfather", "retiree");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", employee.isMarriage());
        jsonObject.put("age", employee.getAge());
        jsonObject.put("contact", jsonContactCard);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект employee в json-строку */
        System.out.println(new JSONObject(employee).toString());
    }
}
