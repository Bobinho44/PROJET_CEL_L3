package fr.unantes.sce.exception;

import javax.annotation.Nonnull;

/**
 * A InvalidRoleException is a custom exception indicating that the person role is invalid
 */
public class InvalidRoleException extends RuntimeException {

    /**
     * Creates an invalid role exception
     *
     * @param errorMessage the error message associated to the exception
     */
    public InvalidRoleException(@Nonnull String errorMessage) {
        super(errorMessage);
    }

}