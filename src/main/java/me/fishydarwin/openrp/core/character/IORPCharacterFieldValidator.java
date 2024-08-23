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
