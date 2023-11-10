package me.fishydarwin.openrp.core.character;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TextCharacterField implements IORPCharacterField {
    private final String name;
    private String value = null;

    private final UUID characterUUID;

    public TextCharacterField(UUID characterUUID, String name) {
        this.characterUUID = characterUUID;
        this.name = name;
    }

    @Override
    public @NotNull UUID getCharacterUUID() {
        return characterUUID;
    }

    /**
     * Returns whether this field has a value assigned to it, or is using
     * the default value
     *
     * @return True if this field is set, false otherwise
     */
    @Override
    public boolean isSet() {
        return value != null;
    }

    /**
     * Gets the name of the field attached to this character field.
     *
     * @return The name of the field
     */
    @Override
    public @NotNull String getFieldName() {
        return name;
    }

    /**
     * Returns the value of this field (normalized to a String)
     *
     * @return The value of this field
     */
    @Override
    public @Nullable String getFieldValue() {
        return name;
    }

    /**
     * Sets the value of this field to a new value, returning the previous value
     * (if any, null otherwise).
     *
     * @param newValue The new value to set this field to
     * @return The previous value of the field, or null if not isSet() returns false.
     */
    @Override
    public @Nullable String setFieldValue(Object newValue) {
        String previous = value;
        value = (String) newValue;
        return previous;
    }

    /**
     * Creates an IORPCharacterFieldValidator which can validate this field.
     *
     * @return A new IORPCharacterFieldValidator which validates this field
     */
    @Override
    public @NotNull IORPCharacterFieldValidator createFieldValidator() {
        return null;
    }
}
