package fr.unantes.sce.wrapper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MultiValuedAttribute<T> {

    private final List<T> values = new ArrayList<>();

    /**
     * Gets the value's list
     *
     * @return the value's list
     */
    @Nonnull
    public List<T> get() {
        return values;
    }

    @Nonnull
    public T get(int i) {
        return values.get(i);
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean contain(T value) {
        return values.contains(value);
    }

    public void add(T value) {
        values.add(value);
    }

    public void basicRemove(T value) {
        values.remove(value);
    }

    public void remove(T value) throws IllegalArgumentException {
        if (!contain(value)) {
            throw new IllegalArgumentException("Invalid operation. The multiValuedAttribute does not contain this value!");
        }

        values.remove(value);
    }

}