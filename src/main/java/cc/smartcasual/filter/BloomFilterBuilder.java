package cc.smartcasual.filter;

import static java.lang.Math.*;

public class BloomFilterBuilder<T>
{
    private int elementCount;
    private double falsePositiveRate = 0.01;

    public static BloomFilterBuilder forElementCount(int elementCount)
    {
        BloomFilterBuilder builder = new BloomFilterBuilder();
        builder.elementCount = elementCount;
        return builder;
    }

    public BloomFilterBuilder withFalsePositiveRate(float falsePositiveRate)
    {
        this.falsePositiveRate = falsePositiveRate;
        return this;
    }

    public BloomFilter<T> build()
    {
        int bitCount = (int) ceil(-elementCount * log(falsePositiveRate) / pow(log(2), 2));
        int hashFunctionCount = (int) ceil(-(log(falsePositiveRate)) / log(2));

        return new BloomFilter<T>(bitCount, hashFunctionCount);
    }
}