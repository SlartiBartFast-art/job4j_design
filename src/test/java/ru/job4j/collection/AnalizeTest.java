package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {

    private List<Analize.User> userList1 = new ArrayList<>();
    private List<Analize.User> userList2 = new ArrayList<>();

   /* @Before
    public void setUp() {
        userList1.add(new Analize.User(1, "Petr"));
        userList1.add(new Analize.User(2, "gert"));
        userList1.add(new Analize.User(3, "Fedor"));
        userList1.add(new Analize.User(4, "Egor"));
        userList1.add(new Analize.User(5, "Retor"));
        userList2.add(new Analize.User(1, "Ivan"));
        userList2.add(new Analize.User(2, "gert"));
        userList2.add(new Analize.User(3, "Petr"));
        userList2.add(new Analize.User(41, "Uio"));
    }*/

    @Test
    public void info() {
        userList1.add(new Analize.User(1, "Petr"));
        userList1.add(new Analize.User(2, "gert"));
        userList1.add(new Analize.User(3, "Fedor"));
        userList1.add(new Analize.User(4, "Egor"));
        userList1.add(new Analize.User(5, "Retor"));
        userList2.add(new Analize.User(1, "Ivan"));
        userList2.add(new Analize.User(2, "gert"));
        userList2.add(new Analize.User(3, "Petr"));
        userList2.add(new Analize.User(41, "Uio"));

        Analize analize = new Analize();
        var tr = analize.diff(userList1, userList2);
        assertThat(tr. toString(),  is("Info[added=1, changed=2, deleted=2]"));
    }

    @Test
    public void info2() {
        userList1.add(new Analize.User(1, "Petr"));
        userList1.add(new Analize.User(2, "gert"));
        userList1.add(new Analize.User(3, "Fedor"));
        userList1.add(new Analize.User(4, "Egor"));
        userList1.add(new Analize.User(5, "Retor"));
        userList2.add(new Analize.User(1, "Petr"));
        userList2.add(new Analize.User(2, "gert"));
        userList2.add(new Analize.User(3, "Fedor"));
        userList2.add(new Analize.User(4, "Egor"));
        userList2.add(new Analize.User(5, "Retor"));

        Analize analize = new Analize();
        var tr = analize.diff(userList1, userList2);
        assertThat(tr. toString(),  is("Info[added=0, changed=0, deleted=0]"));
    }
}