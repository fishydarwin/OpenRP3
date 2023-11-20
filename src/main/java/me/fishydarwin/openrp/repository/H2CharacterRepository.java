package me.fishydarwin.openrp.repository;

import me.fishydarwin.openrp.OpenRP;
import me.fishydarwin.openrp.core.IORPCharacter;
import me.fishydarwin.openrp.core.ORPCharacter;
import me.fishydarwin.openrp.core.character.IORPCharacterField;
import me.fishydarwin.openrp.repository.exception.RepositoryException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The H2 implementation of ICharacterRepository.
 */
public class H2CharacterRepository implements ICharacterRepository {

    private final Map<UUID, IORPCharacter> cachedCharacters = HashMap.newHashMap(32);

    @Override
    public IORPCharacter get(UUID characterId) {

        CompletableFuture<IORPCharacter> characterCompletableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {
            try {

                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                Statement statement = connection.createStatement();
                ResultSet result =
                        statement.executeQuery("SELECT * FROM Characters WHERE character_id=" + characterId);

                if (result.next()) {

                    UUID playerId = UUID.fromString(result.getString("playerId"));

                    /*
                    TODO: population of fields
                    this requires that in code, using config.yml values, we store a set of
                    all possible field names (so the String part in the Map<>...) which will
                    then let us, for each element in the set, call result.getString(element)!
                     */
                    Map<String, IORPCharacterField> fields = new HashMap<>();

                    result.close();

                    IORPCharacter character = new ORPCharacter(characterId, playerId, fields);
                    characterCompletableFuture.complete(character);

                } else {
                    throw new RuntimeException("No character with ID " + characterId + " exists!");
                }

            } catch (SQLException exception) {
                throw new RuntimeException("Could not get character with ID " + characterId +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        try {
            return characterCompletableFuture.get();
        } catch (InterruptedException | ExecutionException exception) {
            throw new RuntimeException("Could not get character with ID " + characterId +
                    "(INTERRUPTED): " + exception.getMessage());
        }
    }

    @Override
    public Map<UUID, IORPCharacter> getByPlayer(UUID playerUUID) {
        return null;
    }

    @Override
    public Map<UUID, IORPCharacter> getByPlayer(OfflinePlayer player) {
        return null;
    }

    @Override
    public boolean add(IORPCharacter character) throws RepositoryException {
        return false;
    }

    @Override
    public boolean remove(IORPCharacter character) throws RepositoryException {
        return false;
    }

    @Override
    public boolean update(IORPCharacter character) throws RepositoryException {
        return false;
    }

}
