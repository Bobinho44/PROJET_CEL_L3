package fr.unantes.sce.exception;

public class MaximumSizeReachedException extends RuntimeException {

    public MaximumSizeReachedException(String errorMessage) {
        super(errorMessage);
    }

}