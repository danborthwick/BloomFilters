package cc.smartcasual.filter;

import java.util.Iterator;
import java.util.Random;

class RandomHashes<T> implements Iterable<Integer>
{
    private int hashCount = 0;
    private int numberOfHashes;
    private final int maxHashValue;
    private final Random random;

    RandomHashes(T value, int numberOfHashes, int maxHashValue)
    {
        this.maxHashValue = maxHashValue;
        this.numberOfHashes = numberOfHashes;
        random = new Random(value.hashCode());
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return hashCount < numberOfHashes;
            }

            @Override
            public Integer next() {
                hashCount++;
                return random.nextInt(maxHashValue);
            }
        };
    }
}
