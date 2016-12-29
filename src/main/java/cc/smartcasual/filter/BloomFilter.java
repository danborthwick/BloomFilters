package cc.smartcasual.filter;

import java.io.Serializable;
import java.util.stream.Stream;

public class BloomFilter<T> implements SetFilter<T>, Serializable {

    protected BitField bitField;            // length = m
    protected final int hashFunctionCount;  // k
    private final Hashes.Builder hashesBuilder;

    protected BloomFilter(int bitCount, int hashFunctionCount, Hashes.Builder hashesBuilder) {
        bitField = new BitField(bitCount);
        this.hashFunctionCount = hashFunctionCount;
        this.hashesBuilder = hashesBuilder;
    }

    @Override
    public void add(T value)
    {
        hashes(value).forEach(bits -> bitField.set(bits));
    }

    @Override
    public boolean mayContain(T value)
    {
        return hashes(value).allMatch(bits -> bitField.get(bits));
    }

    private Stream<Integer> hashes(T value) {
        return hashesBuilder.build(value, bitField.size()).stream().limit(hashFunctionCount);
    }

    public int numberOfBitsSet() {
        return bitField.count();
    }

    @Override
    public int estimatedCount() {
        double mOverK = (double) bitField.size() / (double) hashFunctionCount;
        double xOverM = (double) bitField.count() / (double) bitField.size();
        return (int) (-mOverK * Math.log(1. - xOverM));
    }

}
