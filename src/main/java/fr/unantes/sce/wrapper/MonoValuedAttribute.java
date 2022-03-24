package fr.unantes.sce.wrapper;

import javax.annotation.Nonnull;
import java.util.Objects;

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
        this.value = null;
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
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Invalid operation. The value is null!");
        }

        this.value = value;
    }

    /**
     * Unets the value
     */
    protected void unset() {
        this.value = null;
    }

}