package fr.unantes.sce.wrapper;

public class NonNullMonoValuedAttribute<T> {

    private T value;

    public NonNullMonoValuedAttribute(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

}