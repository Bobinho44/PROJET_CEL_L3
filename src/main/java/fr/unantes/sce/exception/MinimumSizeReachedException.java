package fr.unantes.sce.exception;

public class MinimumSizeReachedException extends RuntimeException {

    public MinimumSizeReachedException(String errorMessage) {
            super(errorMessage);
        }

}