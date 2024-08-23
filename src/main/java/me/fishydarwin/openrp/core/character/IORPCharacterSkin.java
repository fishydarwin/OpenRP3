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
