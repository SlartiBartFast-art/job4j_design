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
        int result = findByIdIndex(id);
        if (result >= 0) {
            mem.set(result, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int result = findByIdIndex(id);
        if (result >= 0) {
            mem.remove(result);
            rsl = true;
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

    public int findByIdIndex(String id) {
        int result = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                result = i;
            }
        }
        return result;
    }
}
