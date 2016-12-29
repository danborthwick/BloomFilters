package cc.smartcasual.filter;

import java.io.Serializable;

public class BitField implements Serializable
{
    private long[] bits;
    private int size;

    private static final int BITS_PER_LONG = 64;

    public BitField(int bitCount)
    {
        size = bitCount;
        bits = new long[((bitCount - 1) / BITS_PER_LONG) + 1];
    }

    public boolean get(int index)
    {
        validateIndex(index);
        long entry = bits[entryIndexForBitIndex(index)];
        long mask = maskForIndex(index);
        return (entry & mask) != 0L;
    }

    public void set(int index)
    {
        validateIndex(index);
        int entryIndex = entryIndexForBitIndex(index);
        bits[entryIndex] |= maskForIndex(index);
    }

    public int size()
    {
        return size;
    }

    private void validateIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IllegalArgumentException(String.format("Index %d not valid for BitField of size %d", index, size));
        }
    }

    private int entryIndexForBitIndex(int bitIndex)
    {
        return bitIndex / BITS_PER_LONG;
    }

    private long maskForIndex(int index)
    {
        return 1L << (index % BITS_PER_LONG);
    }

    public int count() {
        //TODO: Optimise
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (get(i)) {
                count++;
            }
        }
        return count;
    }
}
