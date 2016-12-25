package cc.smartcasual;

import java.io.Serializable;
import java.util.HashSet;

public class HashSetFilter<T> implements SetFilter<T>, Serializable {

    HashSet<T> hashSet = new HashSet<>();

    @Override
    public void add(T value) {
        hashSet.add(value);
    }

    @Override
    public boolean mayContain(T value) {
        return hashSet.contains(value);
    }
}
