package cc.smartcasual.filter;

public interface SetFilter<T> {
    void add(T value);

    boolean mayContain(T value);
}
