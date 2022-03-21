package fr.unantes.sce.wrapper;

public class MonoValuedAttribute<T> extends InitiallyEmptyMonoValuedAttribute<T> {

    public MonoValuedAttribute(T value) {
        super.set(value);
    }

}