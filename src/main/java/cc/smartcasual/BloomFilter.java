package cc.smartcasual;

public class BloomFilter<T> implements SetFilter<T> {

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
