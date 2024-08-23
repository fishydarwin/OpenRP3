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
