package me.fishydarwin.openrp.repository;

import me.fishydarwin.openrp.core.IORPCharacter;
import me.fishydarwin.openrp.repository.exception.RepositoryException;
import org.bukkit.OfflinePlayer;

import java.util.Map;
import java.util.UUID;

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
    IORPCharacter get(UUID characterId);

    /**
     * Returns all characters which belong to the given player.
     * @param playerUUID The unique ID of the player.
     * @return All characters (potentially none) of this player.
     */
    Map<UUID, IORPCharacter> getByPlayer(UUID playerUUID);

    /**
     * Returns all characters which belong to the given player. <br>
     * Please note that internally, this will be done via the player's UUID.
     * @param player The player to get from.
     * @return All characters (potentially none) of this player.
     */
    Map<UUID, IORPCharacter> getByPlayer(OfflinePlayer player);


    /**
     * Adds a new character to the repository.
     * @param character The character to add to the repository.
     * @return True if operation was successful, false otherwise.
     * @throws RepositoryException if an error occurred.
     */
    boolean add(IORPCharacter character) throws RepositoryException;

    /**
     * Removes an existing character from the repository.
     * @param character The character to remove from the repository.
     * @return True if operation was successful, false otherwise.
     * @throws RepositoryException if an error occurred.
     * @throws me.fishydarwin.openrp.repository.exception.MissingEntryException
     *          if the character being removed is not yet in the repository.
     */
    boolean remove(IORPCharacter character) throws RepositoryException;

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
    boolean update(IORPCharacter character) throws RepositoryException;

}
