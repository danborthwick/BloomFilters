package cc.smartcasual.filter;

import java.io.Serializable;

public class BitField implements Serializable
{
    private long[] bits;

    private static final int BITS_PER_LONG = 64;

    public BitField(int bitCount)
    {
        bits = new long[((bitCount - 1) / BITS_PER_LONG) + 1];
    }

    public boolean get(int index)
    {
        long entry = bits[entryIndexForBitIndex(index)];
        long mask = maskForIndex(index);
        return (entry & mask) != 0;
    }

    public void set(int index)
    {
        int entryIndex = entryIndexForBitIndex(index);
        bits[entryIndex] |= maskForIndex(index);
    }

    public int size()
    {
        return bits.length;
    }

    private int entryIndexForBitIndex(int bitIndex)
    {
        return bitIndex / BITS_PER_LONG;
    }

    private long maskForIndex(int index)
    {
        return 1L << (index % BITS_PER_LONG);
    }
}
