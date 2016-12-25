package cc.smartcasual;

import java.io.Serializable;

public class BloomFilter<T> implements SetFilter<T>, Serializable {

    private BitField bitField;

    @Override
    public void add(T value)
    {
    }

    @Override
    public boolean mayContain(T value)
    {
        return false;
    }
}
