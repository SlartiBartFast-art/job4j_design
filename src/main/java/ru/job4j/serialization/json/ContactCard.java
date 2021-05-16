package ru.job4j.serialization.json;

import java.util.StringJoiner;

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
