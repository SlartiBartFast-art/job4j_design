package ru.job4j.collection;

import java.util.Calendar;

public class SimpleUser {
    private String name;
    private int children;

    public SimpleUser(String name, int children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

   }
