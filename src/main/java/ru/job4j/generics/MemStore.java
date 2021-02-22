package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.set(i, model);
                rsl = true;
            }
            return rsl;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.remove(i);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        for (T t : mem) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
