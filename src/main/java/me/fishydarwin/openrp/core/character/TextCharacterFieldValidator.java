package me.fishydarwin.openrp.core.character;

import me.fishydarwin.openrp.core.character.exception.FieldLengthException;
import me.fishydarwin.openrp.core.character.exception.FieldValidationException;
import me.fishydarwin.openrp.core.character.exception.NullFieldException;
import org.jetbrains.annotations.NotNull;

public class TextCharacterFieldValidator implements IORPCharacterFieldValidator {

    private final TextCharacterField field;
    private final int minLength;
    private final int maxLength;

    public TextCharacterFieldValidator(TextCharacterField field, int minLength, int maxLength) {
        this.field = field;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public @NotNull ORPCharacterField getAttachedField() {
        return field;
    }

    @Override
    public boolean validateField() throws FieldValidationException {
        if (field.getFieldValue() == null) throw new NullFieldException(field.getFieldName());
        if (maxLength > 0)
            if (field.getFieldValue().length() < minLength ||
                    field.getFieldValue().length() > maxLength)
                throw new FieldLengthException(minLength, maxLength);
        return true;
    }

}
