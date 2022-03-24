package fr.unantes.sce.exception;

public class InconsistentArgumentException extends RuntimeException {

    public InconsistentArgumentException(String errorMessage) {
        super(errorMessage);
    }

}