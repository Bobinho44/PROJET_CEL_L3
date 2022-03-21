package fr.unantes.sce.exception;

public class MaximumSizeReachedException extends Exception {

    public MaximumSizeReachedException(String errorMessage) {
        super(errorMessage);
    }

}