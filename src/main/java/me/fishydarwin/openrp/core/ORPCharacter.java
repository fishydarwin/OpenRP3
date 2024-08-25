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

package me.fishydarwin.openrp.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import me.fishydarwin.openrp.core.character.IORPCharacterSkin;
import me.fishydarwin.openrp.core.character.ORPCharacterField;

public class ORPCharacter implements IORPCharacter {
    private final UUID characterUUID;
    private final UUID playerUUID;
    private final Map<String, ORPCharacterField> fieldMap;

    private IORPCharacterSkin currentSkin;
    private Set<IORPCharacterSkin> allSkins;

    public ORPCharacter(UUID characterUUID, UUID playerUUID, Map<String, ORPCharacterField> fieldMap) {
        this.characterUUID = characterUUID;
        this.playerUUID = playerUUID;
        this.fieldMap = fieldMap;
    }

    @Override
    public @NotNull UUID getCharacterUUID() {
        return characterUUID;
    }

    @Override
    public @NotNull UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public @NotNull Map<String, ORPCharacterField> getFields() {
        return fieldMap;
    }

    @Override
    public @Nullable IORPCharacterSkin getCurrentSkin() {
        return currentSkin;
    }

    @Override
    public @NotNull Set<IORPCharacterSkin> getAllSkins() {
        return allSkins;
    }

    @Override
    public void setCurrentSkin(IORPCharacterSkin characterSkin) throws IllegalStateException {
        if (allSkins.stream().noneMatch(s -> s.equals(characterSkin)))
            throw new IllegalStateException("This skin has not been registered by this character");

        currentSkin = characterSkin;
    }

    @Override
    public void addNewSkin(IORPCharacterSkin characterSkin) throws IllegalArgumentException {
        if (allSkins.stream().anyMatch(s -> s.equals(characterSkin)))
            throw new IllegalArgumentException("This skin has already been registered");

        allSkins.add(characterSkin);
    }

    @Override
    public void removeSkin(IORPCharacterSkin characterSkin) throws IllegalArgumentException {
        if (allSkins.stream().noneMatch(s -> s.equals(characterSkin)))
            throw new IllegalArgumentException("This skin has not been registered by this character");

        allSkins.remove(characterSkin);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ORPCharacter otherCharacter)) return false;
        return otherCharacter.getCharacterUUID().equals(this.getCharacterUUID())
                && otherCharacter.getPlayerUUID().equals(this.getPlayerUUID());
    }

    @Override
    public String toString() {
        return "ORPCharacter{" +
                "characterUUID=" + characterUUID +
                ", playerUUID=" + playerUUID +
                ", fieldMap=" + fieldMap +
                ", currentSkin=" + currentSkin +
                ", allSkins=" + allSkins +
                '}';
    }
}
