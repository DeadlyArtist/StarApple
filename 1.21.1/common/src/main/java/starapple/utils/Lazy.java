package starapple.utils;

import java.util.function.Supplier;

public final class Lazy<T> implements Supplier<T> {
    private Supplier<T> supplier;
    private T instance;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (instance == null) {
            instance = supplier.get();
            supplier = null;
        }
        return instance;
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
}
