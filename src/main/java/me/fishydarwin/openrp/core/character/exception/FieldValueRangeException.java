package me.fishydarwin.openrp.core.character.exception;

public class FieldValueRangeException extends FieldValidationException {
    public FieldValueRangeException(String message) {
        super("Invalid range! " + message);
    }
}
