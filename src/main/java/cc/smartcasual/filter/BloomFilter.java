package cc.smartcasual.filter;

import java.io.Serializable;

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
        for (int bits : hashes(value)) {
            bitField.set(bits);
        }
    }

    @Override
    public boolean mayContain(T value)
    {
        for (int bits : hashes(value)) {
            if (!bitField.get(bits)) {
                return false;
            }
        }
        return true;
    }

    private RandomHashes hashes(T value) {
        return new RandomHashes(value, hashFunctionCount, bitField.size());
    }

    public int numberOfBitsSet() {
        return bitField.count();
    }

}
