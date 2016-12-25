package cc.smartcasual;

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

    public SetFilter<T> build()
    {
        return new BloomFilter<T>() {{
            int bitCount = (int) ceil(-elementCount * log(falsePositiveRate) / pow(log(2), 2));
            bitField = new BitField(bitCount);
            hashFunctionCount = (int) ceil(-(log(falsePositiveRate)) / log(2));
        }};
    }
}
