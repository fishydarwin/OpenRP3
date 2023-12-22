package me.fishydarwin.openrp.core.character;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class TextCharacterField extends ORPCharacterField {
    private String value = null;
    private int minLength;
    private int maxLength = -1;

    public TextCharacterField(UUID characterUUID, String name) {
        super(characterUUID, name);
    }

    @Override
    public boolean isSet() {
        return value != null;
    }

    @Override
    public @Nullable String getFieldValue() {
        return value;
    }

    @Override
    public @Nullable String setFieldValue(String newValue) {
        String previous = value;
        value = newValue;
        return previous;
    }

    @Override
    public @NotNull IORPCharacterFieldValidator createFieldValidator() {
        return new TextCharacterFieldValidator(this, minLength, maxLength);
    }

    @Override
    public void setInternalParams(@NotNull Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("min-length")) {
                minLength = Integer.parseInt(entry.getValue());
            }
            else if (entry.getKey().equalsIgnoreCase("max-length")) {
                maxLength = Integer.parseInt(entry.getValue());
            }
        }
    }
}
