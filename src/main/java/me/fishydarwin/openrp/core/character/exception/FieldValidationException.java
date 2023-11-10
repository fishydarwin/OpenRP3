package me.fishydarwin.openrp.core.character.exception;

/**
 * An exception that is thrown when a character field validation error occurs.
 */
public abstract class FieldValidationException extends Exception {
    public FieldValidationException(String message) {
        super(message);
    }
}
