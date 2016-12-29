package cc.smartcasual.filter;

public interface SetFilter<T> {
    boolean mayContain(T value);

    void add(T value);

    default void add(Iterable<T> values) {
        values.forEach(value -> add(value));
    }

    int estimatedCount();
}
