package me.fishydarwin.openrp.core.character.exception;

public class FieldLengthException extends FieldValidationException {
    public FieldLengthException(int minLength, int maxLength) {
        super("Field length must be between " + minLength + " and " + maxLength);
    }
}
