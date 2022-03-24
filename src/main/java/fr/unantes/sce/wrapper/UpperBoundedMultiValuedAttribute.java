package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.MaximumSizeReachedException;

import javax.annotation.Nonnull;

/**
 * UpperBoundedMultiValuedAttribute is a multivalued attribute wrapper of multiplicity [0, maxSize]
 */
public class UpperBoundedMultiValuedAttribute<T> extends MultiValuedAttribute<T> {

    /**
     * Fields
     */
    private final int maxSize;

    /**
     * Creates a new upper bounder multivalued attribute wrapper
     *
     * @param maxSize the upper bound
     */
    public UpperBoundedMultiValuedAttribute(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    /**
     * Gets the upper bound size
     *
     * @return the upper bound size
     */
    private int getMaxSize() {
        return maxSize;
    }

    /**
     * Adds the value to the list (within the limit of maxSize element)
     *
     * @param value the added value
     * @throws MaximumSizeReachedException if the list is full
     */
    @Override
    public void add(@Nonnull T value) throws MaximumSizeReachedException {
        if (cannotAdd()) {
            throw new MaximumSizeReachedException("Invalid operation. The multiValuedAttribute is already full!");
        }

        super.add(value);
    }

    /**
     * Checks if a value cannot be added to the list
     *
     * @return true if a value cannot be added to the list, false otherwise
     */
    private boolean cannotAdd() {
        return get().size() >= getMaxSize();
    }

}