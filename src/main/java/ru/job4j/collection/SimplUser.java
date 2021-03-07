package ru.job4j.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.hash;

public class SimplUser {
    public static void main(String[] args) {
        Map<User, Object> simplmap = new HashMap<>();
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2021, Calendar.JANUARY, 01);
        User user1 = new User("Ivan", 2, calendar);
        User user2 = new User("Ivan", 2, calendar);
        simplmap.put(user1, "Пользователь1");
        simplmap.put(user2, "Пользователь1");
        for (Map.Entry<User, Object> entry : simplmap.entrySet()) {
            User someUser = entry.getKey();
            int rts = Objects.hashCode(someUser);

            int tmp = hash(someUser) & 16;
            int one = simplmap.get(user1).hashCode();
            System.out.println("User 1 hascode" + one);
            int two = simplmap.get(user2).hashCode();
            System.out.println("User 2 hascode" + two);
            int filo = hash(one) & 16;
            System.out.println("User 1 array bucket" + "___" + filo);
            int filo2 = hash(two) & 16;
            System.out.println("User 2 array bucket" + "___" + filo2);
            int heh = user1.hashCode();
            System.out.println("prosto hash" + "---" + heh);
            int heh2 = hash(heh) & 16;
            System.out.println("prosto heh2" + "----" + heh2);
            System.out.println("hashmap size" + "---" + simplmap.size());

            System.out.println(hash(someUser));
            System.out.println(tmp); //получить номер ячейки

        }
        System.out.println("final");
        System.out.println(simplmap);
    }
}
