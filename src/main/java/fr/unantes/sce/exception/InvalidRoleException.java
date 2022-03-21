package fr.unantes.sce.exception;

public class InvalidRoleException extends RuntimeException {

    public InvalidRoleException(String errorMessage) {
        super(errorMessage);
    }

}