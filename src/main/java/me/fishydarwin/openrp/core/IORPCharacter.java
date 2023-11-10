package me.fishydarwin.openrp.core;

import me.fishydarwin.openrp.core.character.IORPCharacterField;
import me.fishydarwin.openrp.core.character.IORPCharacterSkin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Represents an OpenRP Character.
 */
public interface IORPCharacter {

    @NotNull UUID getCharacterUUID();
    @NotNull UUID getPlayerUUID();

    /**
     * Returns all fields that this character owns;
     * @return a map of field names -> character fields
     */
    @NotNull Map<String, IORPCharacterField> getFields();

    /**
     * Grabs this character's current skin, or null if the default Minecraft
     * one is currently applied.
     * @return This character's current skin, or null if none is set
     */
    @Nullable IORPCharacterSkin getCurrentSkin();

    /**
     * Grabs all skins which this character has saved, or an empty set if none.
     * @return All the skins this character has saved.
     */
    @NotNull Set<IORPCharacterSkin> getAllSkins();

    /**
     * //TODO: Add information
     * @throws IllegalStateException  //TODO: Add information
     */
    void setCurrentSkin() throws IllegalStateException;

    /**
     * //TODO: Add information
     * @throws IllegalArgumentException //TODO: Add information
     */
    void addNewSkin() throws IllegalArgumentException;

    /**
     * //TODO: Add information
     * @throws IllegalArgumentException //TODO: Add information
     */
    void removeSkin() throws IllegalArgumentException;

}
