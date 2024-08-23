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
