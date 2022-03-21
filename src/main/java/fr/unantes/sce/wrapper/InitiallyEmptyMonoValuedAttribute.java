package fr.unantes.sce.wrapper;

public class InitiallyEmptyMonoValuedAttribute<T> {

    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    public void unset() {
        this.value = null;
    }

}