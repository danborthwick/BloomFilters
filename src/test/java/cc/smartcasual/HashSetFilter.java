package cc.smartcasual;

import cc.smartcasual.filter.SetFilter;

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

    @Override
    public int estimatedCount() {
        return hashSet.size();
    }
}
