package ru.job4j.serialization.json;

import java.util.StringJoiner;

/**
 * 2. Формат JSON [#471733]
 * Придумайте Java объект, объект должен иметь поля булево, какой-нибудь числовой тип,
 * строковый тип, вложенный объект и массив.
 * 2) Опишите его представление в формате JSON в комментарии к заданию.
 * 3) Подключите библиотеку Gson, преобразуйте объект вашего класса в json-строку и обратно.
 * 5. Преобразование JSON в POJO. JsonObject [#471736]
 */
public class ContactCard {
    private final String phone;
    private final String town;
    private final String street;
    private final int house;

    public ContactCard(String phone, String town, String street, int house) {
        this.phone = phone;
        this.town = town;
        this.street = street;
        this.house = house;
    }

    public String getPhone() {
        return phone;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    @Override
    public String toString() {
        return "ContactCard{"
                + "phone='" + phone + '\''
                + "town='" + town + '\''
                + "street='" + street + '\''
                + "house='" + house + '\''
                + '}';
    }
}
