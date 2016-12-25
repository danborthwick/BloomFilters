package cc.smartcasual.filter;

import java.io.Serializable;
import java.util.Iterator;

public class BloomFilter<T> implements SetFilter<T>, Serializable {

    protected BitField bitField;      // length = m
    protected int hashFunctionCount;  // k

    protected BloomFilter(int bitCount, int hashFunctionCount) {
        bitField = new BitField(bitCount);
        this.hashFunctionCount = hashFunctionCount;
    }

    @Override
    public void add(T value)
    {
        for (int bits : new HashCollection(value)) {
            bitField.set(bits);
        }
    }

    @Override
    public boolean mayContain(T value)
    {
        for (int bits : new HashCollection(value)) {
            if (!bitField.get(bits)) {
                return false;
            }
        }
        return true;
    }

    public int numberOfBitsSet() {
        return bitField.count();
    }

    class HashCollection implements Iterable<Integer>
    {
        private int hash;
        private final int mask;
        private int hashCount = 0;

        HashCollection(T value)
        {
            hash = value.hashCode();
            mask = (1 << bitField.size()) - 1;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                @Override
                public boolean hasNext() {
                    return hashCount < hashFunctionCount;
                }

                @Override
                public Integer next() {
                    int bits = hash & mask;
                    hash  = new Integer(hash).hashCode();
                    hashCount++;
                    return bits;
                }
            };
        }
    }

}
