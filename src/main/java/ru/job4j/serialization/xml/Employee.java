package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.io.StringReader;

/**
 *4. JAXB. Преобразование XML в POJO. [#471735]
 * + вложенный класс ContactCard
 * Для того чтобы сериализовать и десериализовать нам нужно добавить аннотации JAXB,
 * которую дадут библиотеке информацию о том как парсить объект.
 * Сущность созданную в прошлом задании сериализовать/десериализовать с помощью JAXB.
 */
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private boolean marriage;

    @XmlAttribute
    private int age;

    private ContactCard contact;

    private String[] statuses;

    public Employee() {
    }

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

    public static void main(String[] args) throws JAXBException, IOException {
       /* final Employee employee = new Employee(true, 65,
                new ContactCard("3194805555", "NewYork", "QuencySt", 125),
                "Son", "Father", "Grandfather",  "retiree");*/
        /* Преобразуем объект person в json-строку. */
    /*    final Gson gson = new GsonBuilder().create();
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
      /*  final Employee employee = new Employee(true, 65,
                new ContactCard("3194805555", "NewYork", "QuencySt", 125),
                "Son", "Father", "Grandfather", "retiree");
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }*/
        Employee employee = new Employee(true, 65,
                new ContactCard("3194805555", "NewYork", "QuencySt", 125),
                "Son", "Father", "Grandfather", "retiree");

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println("to xlm file : " + xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Employee result = (Employee) unmarshaller.unmarshal(reader);
            System.out.println("out from xlm : " + result);
        }
    }
}
