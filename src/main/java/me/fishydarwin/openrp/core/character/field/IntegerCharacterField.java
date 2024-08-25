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

package me.fishydarwin.openrp.core.character.field;

import me.fishydarwin.openrp.core.character.IORPCharacterFieldValidator;
import me.fishydarwin.openrp.core.character.ORPCharacterField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class IntegerCharacterField extends ORPCharacterField {
    private Integer value;
    private Integer minValue;
    private Integer maxValue;

    public IntegerCharacterField(UUID characterUUID, String name) {
        super(characterUUID, name);
    }

    @Override
    public boolean isSet() {
        return value != null;
    }

    @Override
    public @Nullable String getFieldValue() {
        return Integer.toString(value);
    }

    @Override
    public @Nullable String setFieldValue(String newValue) {
        Integer previous = value;
        value = Integer.parseInt(newValue);
        return Integer.toString(previous);
    }

    @Override
    public @NotNull IORPCharacterFieldValidator createFieldValidator() {
        return new IntegerCharacterFieldValidator(this, minValue, maxValue);
    }

    @Override
    public void setInternalParams(@NotNull Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("min-value")) {
                minValue = Integer.parseInt(entry.getValue());
            }
            else if (entry.getKey().equalsIgnoreCase("max-value")) {
                maxValue = Integer.parseInt(entry.getValue());
            }
        }
    }
}
