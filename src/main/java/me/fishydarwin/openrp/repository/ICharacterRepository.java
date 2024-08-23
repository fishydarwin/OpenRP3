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

package me.fishydarwin.openrp.repository;

import me.fishydarwin.openrp.core.IORPCharacter;
import me.fishydarwin.openrp.repository.exception.RepositoryException;
import org.bukkit.OfflinePlayer;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Stores all characters required for normal operation. <br>
 * If a character is not present within the current cache, it will be <br>
 * fetched from the in-memory file or from the database.
 */
public interface ICharacterRepository {

    /**
     * Returns a character provided by the character ID.
     * @param characterId The id (UUID) if the character.
     * @return An implementation of IORPCharacter whose ID matches.
     */
    CompletableFuture<IORPCharacter> get(UUID characterId);

    /**
     * Returns all characters which belong to the given player.
     * @param playerUUID The unique ID of the player.
     * @return All characters (potentially none) of this player.
     */
    CompletableFuture<Map<UUID, IORPCharacter>> getByPlayer(UUID playerUUID);

    /**
     * Returns all characters which belong to the given player. <br>
     * Please note that internally, this will be done via the player's UUID.
     * @param player The player to get from.
     * @return All characters (potentially none) of this player.
     */
    CompletableFuture<Map<UUID, IORPCharacter>> getByPlayer(OfflinePlayer player);


    /**
     * Adds a new character to the repository.
     * @param character The character to add to the repository.
     * @return True if operation was successful, false otherwise.
     * @throws RepositoryException if an error occurred.
     */
    CompletableFuture<Boolean> add(IORPCharacter character) throws RepositoryException;

    /**
     * Removes an existing character from the repository.
     * @param character The character to remove from the repository.
     * @return True if operation was successful, false otherwise.
     * @throws RepositoryException if an error occurred.
     * @throws me.fishydarwin.openrp.repository.exception.MissingEntryException
     *          if the character being removed is not yet in the repository.
     */
    CompletableFuture<Boolean> remove(IORPCharacter character) throws RepositoryException;

    /**
     * Updates (that is, saves or puts back in the database) all of the <br>
     * fields associated to this character, effectively storing their data <br>
     * persistently.
     * @param character The character to update in the repository
     * @return True if operation was successful, false otherwise.
     * @throws RepositoryException if an error occurred
     * @throws me.fishydarwin.openrp.repository.exception.MissingEntryException
     *          if the character being updated is not yet in the repository.
     */
    CompletableFuture<Boolean> update(IORPCharacter character) throws RepositoryException;

    /**
     * Gets a player's currently active character ID by their UUID.
     * @param playerUUID The unique ID of the player.
     * @return The unique ID of the character currently used by this player.
     */
    CompletableFuture<UUID> getActiveCharacter(UUID playerUUID);

    /**
     * Gets a player's currently active character ID.
     * Please note that internally, this will be done via the player's UUID.
     * @param player The player to get from.
     * @return The unique ID of the character currently used by this player.
     */
    CompletableFuture<UUID> getActiveCharacter(OfflinePlayer player);

    /**
     * Sets a player's currently active character ID by their UUID.
     * @param playerUUID The unique ID of the player.
     * @param characterId The character ID to make active.
     * @return True if operation was successful, false otherwise.
     */
    CompletableFuture<Boolean> setActiveCharacter(UUID playerUUID, UUID characterId);

    /**
     * Sets a player's currently active character ID by their UUID.
     * Please note that internally, this will be done via the player's UUID.
     * @param player The player to get from.
     * @param characterId The character ID to make active.
     * @return True if operation was successful, false otherwise.
     */
    CompletableFuture<Boolean> setActiveCharacter(OfflinePlayer player, UUID characterId);

}
