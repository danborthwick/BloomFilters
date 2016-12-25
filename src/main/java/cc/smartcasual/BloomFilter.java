package cc.smartcasual;

import java.io.Serializable;

public class BloomFilter<T> implements SetFilter<T>, Serializable {

    protected BitField bitField;      // length = m
    protected int hashFunctionCount;  // k

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
