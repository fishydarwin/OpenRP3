package me.fishydarwin.openrp.core;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import me.fishydarwin.openrp.core.character.IORPCharacterField;
import me.fishydarwin.openrp.core.character.IORPCharacterSkin;

public class ORPCharacter implements IORPCharacter {
    private final UUID characterUUID;
    private final UUID playerUUID;
    private final Map<String, IORPCharacterField> fieldMap;

    private IORPCharacterSkin currentSkin;
    private Set<IORPCharacterSkin> allSkins;

    public ORPCharacter(UUID characterUUID, UUID playerUUID, Map<String, IORPCharacterField> fieldMap) {
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

    /**
     * Returns all fields that this character owns;
     *
     * @return a map of field names -> character fields
     */
    @Override
    public @NotNull Map<String, IORPCharacterField> getFields() {
        return fieldMap;
    }

    /**
     * Grabs this character's current skin, or null if the default Minecraft
     * one is currently applied.
     *
     * @return This character's current skin, or null if none is set
     */
    @Override
    public @Nullable IORPCharacterSkin getCurrentSkin() {
        return currentSkin;
    }

    /**
     * Grabs all skins which this character has saved, or an empty set if none.
     *
     * @return All the skins this character has saved.
     */
    @Override
    public @NotNull Set<IORPCharacterSkin> getAllSkins() {
        return allSkins;
    }

    /**
     * //TODO: Add information
     *
     * @param characterSkin
     * @throws IllegalStateException //TODO: Add information
     */
    @Override
    public void setCurrentSkin(IORPCharacterSkin characterSkin) throws IllegalStateException {
        if (allSkins.stream().noneMatch(skin -> skin.equals(characterSkin)))
            throw new IllegalStateException("This skin has not been added to this character's skins");

        currentSkin = characterSkin;
    }

    /**
     * //TODO: Add information
     *
     * @param characterSkin
     * @throws IllegalArgumentException //TODO: Add information
     */
    @Override
    public void addNewSkin(IORPCharacterSkin characterSkin) throws IllegalArgumentException {
        if (allSkins.stream().anyMatch(skin -> !skin.equals(characterSkin)))
            throw new IllegalArgumentException("Skin is already implemented");

        allSkins.add(characterSkin);
    }

    /**
     * //TODO: Add information
     *
     * @param characterSkin
     * @throws IllegalArgumentException //TODO: Add information
     */
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
}
