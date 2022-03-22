package fr.unantes.sce.wrapper;

import java.util.Objects;

public class MonoValuedAttribute<T> {

    private T value;

    public MonoValuedAttribute(T value) {
        this.value = value;
    }

    protected MonoValuedAttribute() {
        this.value = null;
    }

    public T get() {
        return value;
    }

    public void set(T value) throws IllegalArgumentException {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Invalid operation. The value is null!");
        }

        this.value = value;
    }

    protected void unset() {
        this.value = null;
    }

}