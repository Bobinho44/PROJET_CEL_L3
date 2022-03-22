package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.exception.MinimumSizeReachedException;

public class BothBoundedMultiValuedAttribute<T> extends MultiValuedAttribute<T> {

    private final int maxSize;
    private final int minSize;

    /**
     * Creates a new upper bounder multi valued attribute
     *
     * @param maxSize the upper bound
     */
    public BothBoundedMultiValuedAttribute(int minSize, int maxSize) {
        super();
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    private int getMinSize() {
        return minSize;
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

    @Override
    public void remove(T value) throws MinimumSizeReachedException {
        if (cannotRemove()) {
            throw new MinimumSizeReachedException("Invalid operation. The multiValuedAttribute has reached its minimum size!");
        }

        super.remove(value);
    }

    private boolean cannotRemove() {
        return get().size() <= getMinSize();
    }

}