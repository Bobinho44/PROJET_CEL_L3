package fr.unantes.sce.wrapper;

import javax.annotation.Nonnull;

/**
 * MonoValuedAttribute is a monovalued attribute wrapper that cannot be empty
 */
public class MonoValuedAttribute<T> {

    /**
     * Fields
     */
    private T value;

    /**
     * Creates a new monovalued attribute wrapper
     *
     * @param value the initial value
     */
    public MonoValuedAttribute(@Nonnull T value) {
        this.value = value;
    }

    /**
     * Creates a new empty monovalued attribute wrapper
     */
    protected MonoValuedAttribute() {
    }

    /**
     * Gets the value
     *
     * @return the value
     */
    public T get() {
        return value;
    }

    /**
     * Sets the new value
     *
     * @param value the new value
     * @throws IllegalArgumentException if the new value is null
     */
    public void set(@Nonnull T value) throws IllegalArgumentException {
        this.value = value;
    }

    /**
     * Unsets the value
     */
    protected void unset() {
        this.value = null;
    }

}