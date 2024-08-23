/*
 *    Copyright 2024 The OpenRP Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package me.fishydarwin.openrp.core.character;

import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

/**
 * Represents a field that belongs to one specific character
 */
public abstract class ORPCharacterField {

    protected final UUID characterUUID;
    protected final String name;

    public ORPCharacterField(UUID characterUUID, String name) {
        this.characterUUID = characterUUID;
        this.name = name;
    }

    /**
     * Returns the character UUID attached to this field.
     * @return The character UUID
     */
    public @NotNull UUID getCharacterUUID() {
        return characterUUID;
    }

    /**
     * Returns whether this field has a value assigned to it, or is using
     * the default value
     * @return True if this field is set, false otherwise
     */
    public boolean isSet() {
        throw new NotImplementedException("Missed implementing isSet in field type "
                + this.getClass().getName());
    }

    /**
     * Gets the name of the field attached to this character field.
     * @return The name of the field
     */
    public @NotNull String getFieldName() {
        return name;
    }

    /**
     * Returns the value of this field (normalized to a String)
     * @return The value of this field
     */
    public @Nullable String getFieldValue() {
        throw new NotImplementedException("Missed implementing getFieldValue in field type "
                + this.getClass().getName());
    }

    /**
     * Sets the value of this field to a new value, returning the previous value
     * (if any, null otherwise).
     * @param newValue The new value to set this field to
     * @return The previous value of the field, or null if not isSet() returns false.
     */
    public @Nullable String setFieldValue(String newValue) {
        throw new NotImplementedException("Missed implementing setFieldValue in field type "
                + this.getClass().getName());
    }

    /**
     * Creates an IORPCharacterFieldValidator which can validate this field.
     * @return A new IORPCharacterFieldValidator which validates this field
     */
    public @NotNull IORPCharacterFieldValidator createFieldValidator() {
        throw new NotImplementedException("Missed implementing createFieldValidator in field type "
                + this.getClass().getName());
    }

    /**
     * Sets the internal parameters of this field based on the key-value pairings provided.
     * @param params The key-value pairings - the internal param mapped to some value.
     */
    public void setInternalParams(@NotNull Map<String, String> params) {}

}
