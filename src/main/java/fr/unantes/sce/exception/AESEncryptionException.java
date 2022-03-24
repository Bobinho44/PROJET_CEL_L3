package fr.unantes.sce.exception;

import javax.annotation.Nonnull;

/**
 * A EncryptionException is a custom exception indicating that an AES encryption went wrong
 */
public class AESEncryptionException extends RuntimeException {

    /**
     * Creates an AES encryption exception
     *
     * @param errorMessage the error message associated to the exception
     */
    public AESEncryptionException(@Nonnull String errorMessage) {
        super(errorMessage);
    }

}