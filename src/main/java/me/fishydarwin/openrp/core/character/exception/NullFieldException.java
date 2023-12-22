package me.fishydarwin.openrp.core.character.exception;

public class NullFieldException extends FieldValidationException {
    public NullFieldException(String fieldName) {
        super("Value of field " + fieldName+ " is empty.");
    }
}
