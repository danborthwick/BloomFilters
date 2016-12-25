package cc.smartcasual;

public interface SetFilter<T> {
    void add(T value);

    boolean mayContain(T value);
}
