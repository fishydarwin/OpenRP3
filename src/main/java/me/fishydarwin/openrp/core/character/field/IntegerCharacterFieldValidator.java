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
import me.fishydarwin.openrp.core.character.exception.FieldLengthException;
import me.fishydarwin.openrp.core.character.exception.FieldValidationException;
import me.fishydarwin.openrp.core.character.exception.NullFieldException;
import org.jetbrains.annotations.NotNull;

public class IntegerCharacterFieldValidator implements IORPCharacterFieldValidator {

    private final IntegerCharacterField field;
    private final Integer minValue;
    private final Integer maxValue;

    public IntegerCharacterFieldValidator(IntegerCharacterField field, Integer minValue, Integer maxValue) {
        this.field = field;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public @NotNull ORPCharacterField getAttachedField() {
        return field;
    }

    @Override
    public boolean validateField() throws FieldValidationException {
        if (field.getFieldValue() == null) throw new NullFieldException(field.getFieldName());

        int value = Integer.parseInt(field.getFieldValue());
        if (minValue != null){
            if (value < minValue) return false;
        }
        if (maxValue != null){
            if (value > maxValue) return false;
        }
        return true;
    }
}
