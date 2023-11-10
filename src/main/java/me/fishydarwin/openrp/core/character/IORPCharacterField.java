package me.fishydarwin.openrp.core.character;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents a field that belongs to one specific character
 */
public interface IORPCharacterField {

    @NotNull UUID getCharacterUUID();

    /**
     * Returns whether this field has a value assigned to it, or is using
     * the default value
     * @return True if this field is set, false otherwise
     */
    boolean isSet();

    /**
     * Gets the name of the field attached to this character field.
     * @return The name of the field
     */
    @NotNull String getFieldName();

    /**
     * Returns the value of this field (normalized to a String)
     * @return The value of this field
     */
    @Nullable String getFieldValue();

    /**
     * Sets the value of this field to a new value, returning the previous value
     * (if any, null otherwise).
     * @param newValue The new value to set this field to
     * @return The previous value of the field, or null if not isSet() returns false.
     */
    @Nullable String setFieldValue(Object newValue);

    /**
     * Creates an IORPCharacterFieldValidator which can validate this field.
     * @return A new IORPCharacterFieldValidator which validates this field
     */
    @NotNull IORPCharacterFieldValidator createFieldValidator();

}
