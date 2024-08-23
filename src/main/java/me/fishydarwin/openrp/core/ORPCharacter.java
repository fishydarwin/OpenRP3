package me.fishydarwin.openrp.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import me.fishydarwin.openrp.core.character.ORPCharacterField;
import me.fishydarwin.openrp.core.character.IORPCharacterSkin;

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
        if (allSkins.stream().noneMatch(skin -> skin.equals(characterSkin)))
            throw new IllegalStateException("This skin has not been added to this character's skins");

        currentSkin = characterSkin;
    }

    @Override
    public void addNewSkin(IORPCharacterSkin characterSkin) throws IllegalArgumentException {
        if (allSkins.stream().anyMatch(skin -> !skin.equals(characterSkin)))
            throw new IllegalArgumentException("Skin is already implemented");

        allSkins.add(characterSkin);
    }

    @Override
    public void removeSkin(IORPCharacterSkin characterSkin) throws IllegalArgumentException {
        if (allSkins.stream().noneMatch(skin -> skin.equals(characterSkin)))
            throw new IllegalArgumentException("Skin did not exist in the first place");

        allSkins.remove(characterSkin);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ORPCharacter)) return false;
        ORPCharacter otherCharacter = (ORPCharacter) other;
        if (otherCharacter.playerUUID != this.playerUUID) return false;
        return otherCharacter.characterUUID == this.characterUUID;
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
