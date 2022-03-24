package fr.unantes.sce.exception;

import javax.annotation.Nonnull;

/**
 * A InconsistentArgumentException is a custom exception indicating that the argument is inconsistent
 */
public class InconsistentArgumentException extends RuntimeException {

    /**
     * Creates an inconsistent argument exception
     *
     * @param errorMessage the error message associated to the exception
     */
    public InconsistentArgumentException(@Nonnull String errorMessage) {
        super(errorMessage);
    }

}