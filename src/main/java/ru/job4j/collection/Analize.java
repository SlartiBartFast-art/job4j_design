package ru.job4j.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 2. Статистику по коллекции. [#471665]
 * определению разницы между начальным состояние массива и измененным.
 * Нужно понять:
 * Сколько добавлено новых пользователей.
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
 * Сколько удалено пользователей.
 */
public class Analize {
    private int count = 0;

    /**
     * метод должен возвращать статистику об изменении коллекции.
     *
     * @param previous List<User> previous - начальные данные, // 1 10 13 4 5
     * @param current  List<User> current - измененные данные. // 1 13 4
     * @return объект класса Info
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();

        HashMap<Integer, User> current1 = new HashMap<>();
        for (User u : current) {
            current1.put(u.id, u);
        }
        for (User user : previous) {
            if (current1.containsKey(user.id) && current1.get(user.id).equals(user)) { // current1.containsValue(user)) {
                count++;
                System.out.println("transporte: " + count);
            }
            if (current1.containsKey(user.id) && !current1.get(user.id).equals(user)) { //current1.containsValue(user)
                info.changed++;
                System.out.println("change: " + info.changed);
            }
        }

        info.deleted = previous.size() - info.changed - count;
        info.added = current.size() - (info.changed + count);
        return info;
    }

    public static class User {
        private int id;
        private String name;

        public User(int i, String name) {
            this.id = i;
            this.name = name;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("name='" + name + "'")
                    .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

    }

    public static class Info {
        private int added; // сколько добавлено новых пользователей
        private int changed; // Сколько изменено пользователей.
        private int deleted; //Сколько удалено пользователей.

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getChanged() {
            return changed;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Info.class.getSimpleName() + "[", "]")
                    .add("added=" + added)
                    .add("changed=" + changed)
                    .add("deleted=" + deleted)
                    .toString();
        }
    }

    public static void main(String[] args) {
        List<User> userList1 = new ArrayList<>();
        List<User> userList2 = new ArrayList<>();
        userList1.add(new User(1, "Petr"));
        userList1.add(new User(2, "gert"));
        userList1.add(new User(3, "Fedor"));
        userList1.add(new User(4, "Egor"));
        userList1.add(new User(5, "Retor"));
        userList2.add(new User(1, "Ivan"));
        userList2.add(new User(2, "gert"));
        userList2.add(new User(3, "Petr"));
        userList2.add(new User(41, "Uio"));
        Analize analize = new Analize();
        var toi = analize.diff(userList1, userList2);
        System.out.println("Itogo: " + toi);
    }
}
