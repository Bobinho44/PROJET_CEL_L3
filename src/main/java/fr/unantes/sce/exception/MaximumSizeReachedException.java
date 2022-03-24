package fr.unantes.sce.exception;

import javax.annotation.Nonnull;

/**
 * A MaximumSizeReachedException is a custom exception indicating that the list has reached its maximum size
 */
public class MaximumSizeReachedException extends RuntimeException {

    /**
     * Creates a maximum size reached exception
     *
     * @param errorMessage the error message associated to the exception
     */
    public MaximumSizeReachedException(@Nonnull String errorMessage) {
        super(errorMessage);
    }

}