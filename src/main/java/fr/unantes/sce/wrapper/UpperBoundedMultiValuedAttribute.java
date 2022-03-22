package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.MaximumSizeReachedException;

public class UpperBoundedMultiValuedAttribute<T> extends MultiValuedAttribute<T> {

    private final int maxSize;

    /**
     * Creates a new upper bounder multi valued attribute
     *
     * @param maxSize the upper bound
     */
    public UpperBoundedMultiValuedAttribute(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    private int getMaxSize() {
        return maxSize;
    }

    /**
     * Adds a value to the value's list (within the limit of maxSize element)
     *
     * @param value the added value
     * @throws MaximumSizeReachedException the value's list is full
     */
    @Override
    public void add(T value) throws MaximumSizeReachedException {
        if (cannotAdd()) {
            throw new MaximumSizeReachedException("Invalid operation. The multiValuedAttribute is already full!");
        }

        super.add(value);
    }

    private boolean cannotAdd() {
        return get().size() >= getMaxSize();
    }

}