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

public class DoubleCharacterField extends ORPCharacterField {
    private Double value;
    private Double minValue;
    private Double maxValue;
    private Integer roundPrecision;

    public DoubleCharacterField(UUID characterUUID, String name) {
        super(characterUUID, name);
    }

    @Override
    public boolean isSet() {
        return value != null;
    }

    @Override
    public @Nullable String getFieldValue() {
        return Double.toString(value);
    }

    @Override
    public @Nullable String setFieldValue(String newValue) {
        Double previous = value;
        value = Double.parseDouble(newValue);

        if (roundPrecision != null && roundPrecision > 0 && roundPrecision < 6){
            value = roundToDigits(value, roundPrecision);
        }

        return Double.toString(previous);
    }

    @Override
    public @NotNull IORPCharacterFieldValidator createFieldValidator() {
        return new DoubleCharacterFieldValidator(this, minValue, maxValue);
    }

    @Override
    public void setInternalParams(@NotNull Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("min-value")) {
                minValue = Double.parseDouble(entry.getValue());
            }
            else if (entry.getKey().equalsIgnoreCase("max-value")) {
                maxValue = Double.parseDouble(entry.getValue());
            }
            else if (entry.getKey().equalsIgnoreCase("round-precision")) {
                roundPrecision = Integer.parseInt(entry.getValue());
            }
        }
    }

    private Double roundToDigits(Double value, Integer precision){
        Double power = Math.pow(10, precision);
        Integer newValue = (int) (value * power);
        return newValue / power;
    }
}
