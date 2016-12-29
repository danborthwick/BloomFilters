package cc.smartcasual.filter;

import static java.lang.Math.*;

public class BloomFilterBuilder<T>
{
    private int elementCount;
    private double falsePositiveRate = 0.01;
    private Hashes.Builder<T> hashesBuilder = new RandomHashes.Builder<>();

    public static BloomFilterBuilder forElementCount(int elementCount)
    {
        BloomFilterBuilder builder = new BloomFilterBuilder();
        builder.elementCount = elementCount;
        return builder;
    }

    public BloomFilterBuilder withFalsePositiveRate(double falsePositiveRate)
    {
        this.falsePositiveRate = falsePositiveRate;
        return this;
    }

    public BloomFilterBuilder withHashes(Hashes.Builder<T> builder)
    {
        this.hashesBuilder = builder;
        return this;
    }

    public BloomFilter<T> build()
    {
        int bitCount = (int) ceil(-elementCount * log(falsePositiveRate) / pow(log(2), 2));
        int hashFunctionCount = (int) ceil(-(log(falsePositiveRate)) / log(2));

        return new BloomFilter<>(bitCount, hashFunctionCount, hashesBuilder);
    }
}
