package ru.job4j.io;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 4.2. Поиск дубликатов [#471725]
 * Модель данных - описывается двумя свойствами: размером и именем.
 */
public class FileProperty {
    private long size;
    private String name;

    public FileProperty(long size, String name) {
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileProperty.class.getSimpleName() + "[", "]")
                .add("size=" + size)
                .add("name='" + name + "'")
                .toString();
    }
}
