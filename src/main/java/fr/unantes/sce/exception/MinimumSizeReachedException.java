package fr.unantes.sce.exception;

import javax.annotation.Nonnull;

/**
 * A MaximumSizeReachedException is a custom exception indicating that the list has reached its minimum size
 */
public class MinimumSizeReachedException extends RuntimeException {

    /**
     * Creates a minimum size reached exception
     *
     * @param errorMessage the error message associated to the exception
     */
    public MinimumSizeReachedException(@Nonnull String errorMessage) {
            super(errorMessage);
        }

}