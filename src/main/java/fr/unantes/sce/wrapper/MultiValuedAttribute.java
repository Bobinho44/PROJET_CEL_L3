package fr.unantes.sce.wrapper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * MultiValuedAttribute is a multivalued attribute wrapper of multiplicity [*]
 */
public class MultiValuedAttribute<T> {

    /**
     * Fields
     */
    private final List<T> values = new ArrayList<>();

    /**
     * Gets the i nth value of the list
     *
     * @param i the index of the desired value
     * @return the i nth value of the list
     */
    @Nonnull
    public T get(int i) {
        return this.values.get(i);
    }

    /**
     * Gets the list size
     *
     * @return the list size
     */
    public int size() {
        return this.values.size();
    }

    /**
     * Checks if the list is empty
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    /**
     * Checks if the list contain the value
     *
     * @param value the desired value
     * @return true if the list contain the value, false otherwise
     */
    public boolean contain(@Nonnull T value) {
        return this.values.contains(value);
    }

    /**
     * Adds the value to the list
     *
     * @param value the added value
     */
    public void add(@Nonnull T value) {
        this.values.add(value);
    }

    /**
     * Remove the value from the list without any check
     *
     * @param value the removed value
     */
    public void basicRemove(@Nonnull T value) {
        this.values.remove(value);
    }

    /**
     * Remove the value from the list with restriction
     *
     * @param value the removed value
     * @throws IllegalArgumentException if the value is not in the list
     */
    public void remove(@Nonnull T value) throws IllegalArgumentException {
        if (!contain(value)) {
            throw new IllegalArgumentException("Invalid operation. The multiValuedAttribute does not contain this value!");
        }

        this.values.remove(value);
    }

}