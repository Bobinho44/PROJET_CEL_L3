package fr.unantes.sce.exception;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }

}