package cc.smartcasual.filter;

import java.util.Random;
import java.util.stream.Stream;

class RandomHashes implements Hashes
{
    private final int maxHashValue;
    private final Random random;

    RandomHashes(Object value, int maxHashValue)
    {
        this.maxHashValue = maxHashValue;
        random = new Random(value.hashCode());
    }

    @Override
    public Stream<Integer> stream() {
        return Stream.generate(() -> random.nextInt(maxHashValue));
    }

    static class Builder<T> implements Hashes.Builder<T> {
        @Override
        public Hashes build(T value, int maxHashValue) {
            return new RandomHashes(value, maxHashValue);
        }
    }
}
