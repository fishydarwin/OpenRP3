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

package me.fishydarwin.openrp.registry;

import me.fishydarwin.openrp.core.character.ORPCharacterField;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterFieldRegistry {

    private static final Map<String, Class<ORPCharacterField>> fields = new HashMap<>();

    public static void registerField(String fieldName, Class<ORPCharacterField> fieldClass) {
        fields.put(fieldName, fieldClass);
    }

    public static Map<String, ORPCharacterField> makeFields(UUID characterUUID) {
        return fields.entrySet().stream()
                .map((entry) -> {
                    String fieldName = entry.getKey();
                    Class<ORPCharacterField> fieldClass = entry.getValue();
                    try {
                        return fieldClass.getDeclaredConstructor(UUID.class, String.class)
                                .newInstance(characterUUID, fieldName);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toMap(ORPCharacterField::getFieldName, Function.identity()));
    }

}
