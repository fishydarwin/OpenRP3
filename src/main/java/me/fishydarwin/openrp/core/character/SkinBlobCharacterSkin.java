package me.fishydarwin.openrp.core.character;

import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SkinBlobCharacterSkin implements IORPCharacterSkin {
    private final UUID characterUUID;
    private final PlayerTextures playerTextures;
    private final int skinID;

    public SkinBlobCharacterSkin(UUID characterUUID, PlayerTextures playerTextures, int skinID) {
        this.characterUUID = characterUUID;
        this.playerTextures = playerTextures;
        this.skinID = skinID;
    }

    @Override
    public @NotNull UUID getCharacterUUID() {
        return characterUUID;
    }

    @Override
    public int getSkinID() {
        return skinID;
    }

    @Override
    public @NotNull PlayerTextures getPlayerTextures() {
        return playerTextures;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SkinBlobCharacterSkin)) return false;
        SkinBlobCharacterSkin otherSkin = (SkinBlobCharacterSkin) other;
        if (otherSkin.characterUUID != this.characterUUID) return false;
        return otherSkin.skinID == this.skinID;
    }
}
