package fr.unantes.sce.wrapper;

import fr.unantes.sce.exception.MaximumSizeReachedException;
import fr.unantes.sce.exception.MinimumSizeReachedException;

import javax.annotation.Nonnull;

/**
 * BothBoundedMultiValuedAttribute is a multivalued attribute wrapper of multiplicity [minSize, maxSize]
 */
public class BothBoundedMultiValuedAttribute<T> extends UpperBoundedMultiValuedAttribute<T> {

    /**
     * Fields
     */
    private final int minSize;

    /**
     * Creates a new upper bounder multivalued attribute wrapper
     *
     * @param minSize the lower bound size
     * @param maxSize the upper bound size
     */
    public BothBoundedMultiValuedAttribute(int minSize, int maxSize) {
        super(maxSize);
        this.minSize = minSize;
    }

    /**
     * Gets the lower bound size
     *
     * @return the lower bound size
     */
    private int getMinSize() {
        return minSize;
    }

    /**
     * Removes the value from the list (within the limit of minSize element)
     *
     * @param value the removed value
     * @throws MaximumSizeReachedException if the list has reached its minimum size
     */
    @Override
    public void remove(@Nonnull T value) throws MinimumSizeReachedException {
        if (cannotRemove()) {
            throw new MinimumSizeReachedException("Invalid operation. The multiValuedAttribute has reached its minimum size!");
        }

        super.remove(value);
    }

    /**
     * Checks if a value cannot be removed from the list
     *
     * @return true if a value cannot be removed from the list, false otherwise
     */
    private boolean cannotRemove() {
        return get().size() <= getMinSize();
    }

}