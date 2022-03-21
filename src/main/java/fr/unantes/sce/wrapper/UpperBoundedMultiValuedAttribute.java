package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.InvalidArgumentException;
import fr.unantes.sce.exception.MaximumSizeReachedException;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UpperBoundedMultiValuedAttribute<T> {

    private final List<T> values = new ArrayList<>();
    private final int maxSize;

    /**
     * Creates a new upper bounder multi valued attribute
     *
     * @param maxSize the upper bound
     */
    public UpperBoundedMultiValuedAttribute(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * Gets the value's list
     *
     * @return the value's list
     */
    @Nonnull
    public List<T> get() {
        return values;
    }

    /**
     * Adds a value to the value's list (within the limit of maxSize element)
     *
     * @param value the added value
     * @throws MaximumSizeReachedException the value's list is full
     */
    public void add(T value) throws MaximumSizeReachedException {
        if (!canAdd()) {
            throw new MaximumSizeReachedException("Invalid operation. The multiValuedAttribute is already full!");
        }

        values.add(value);
    }

    public void remove(T value) throws IllegalArgumentException {
        if (!contain(value)) {
            throw new IllegalArgumentException("Invalid operation. The multiValuedAttribute does not contain this value!");
        }

        values.remove(value);
    }

    public boolean contain(T value) {
        return values.contains(value);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int size() {
        return values.size();
    }

    public T get(int i) {
        return values.get(i);
    }

    public boolean canAdd() {
        return get().size() < getMaxSize();
    }

}