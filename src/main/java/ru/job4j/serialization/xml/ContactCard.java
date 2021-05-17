package ru.job4j.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *4. JAXB. Преобразование XML в POJO. [#471735]
 * вложенный класс для задания
 */
//Над вложенными сущностями нам нужно поставить просто @XmlElement
@XmlElement(value = "contact")
public class ContactCard {
    //Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute
    @XmlAttribute
    private String phone;
    @XmlAttribute
    private String town;
    @XmlAttribute
    private String street;
    @XmlAttribute
    private int house;

    public ContactCard() {

    }

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
