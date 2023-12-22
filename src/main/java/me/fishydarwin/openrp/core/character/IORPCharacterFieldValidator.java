package me.fishydarwin.openrp.core.character;

import me.fishydarwin.openrp.core.character.exception.FieldValidationException;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a validation method that is used to check if a passed
 * value for a specific field is a valid one or not (and if not, then
 * exactly why that might be).
 */
public interface IORPCharacterFieldValidator {

    @NotNull ORPCharacterField getAttachedField();

    /**
     * Validates the attached field and returns true if the validation was successful.
     * @return True if the validation succeeded, false otherwise
     * @throws FieldValidationException when a validation error occurred.
     */
    boolean validateField() throws FieldValidationException;

}
