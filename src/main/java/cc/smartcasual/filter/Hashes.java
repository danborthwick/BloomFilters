package cc.smartcasual.filter;

import java.io.Serializable;
import java.util.stream.Stream;

public interface Hashes {
    Stream<Integer> stream();

    interface Builder<T> extends Serializable {
        Hashes build(T value, int bitFieldSize);
    }
}
