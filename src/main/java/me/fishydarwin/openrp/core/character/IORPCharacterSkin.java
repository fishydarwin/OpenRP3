package me.fishydarwin.openrp.core.character;

import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Represents a skin that this character can have.
 */
public interface IORPCharacterSkin {

    @NotNull UUID getCharacterUUID();
    int getSkinID();

    /**
     * Returns the PlayerTextures object describing this specific skin.
     * @return The player textures of this skin
     */
    @NotNull PlayerTextures getPlayerTextures();

}
