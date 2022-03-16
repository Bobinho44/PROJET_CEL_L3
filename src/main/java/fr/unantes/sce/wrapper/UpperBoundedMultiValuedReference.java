package fr.unantes.sce.wrapper;

import org.atlanmod.commons.Guards;

import java.util.ArrayList;
import java.util.List;

public class UpperBoundedMultiValuedReference<T> {

    private final List<T> values = new ArrayList<>();
    private final int maxSize;

    public UpperBoundedMultiValuedReference(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<T> get() {
        return values;
    }

    public void add(T value) {
        Guards.checkArgument(canAdd(), "values is already full!");

        values.add(value);
    }

    public void remove(T value) {
        if (!contain(value)) {
            throw new IllegalArgumentException("this value is not linked to values!");
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