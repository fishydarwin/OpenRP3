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

import me.fishydarwin.openrp.game.OpenRP;
import me.fishydarwin.openrp.core.IORPCharacter;
import me.fishydarwin.openrp.core.ORPCharacter;
import me.fishydarwin.openrp.core.character.ORPCharacterField;
import me.fishydarwin.openrp.registry.CharacterFieldRegistry;
import me.fishydarwin.openrp.repository.exception.RepositoryException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The H2 implementation of ICharacterRepository.
 */
public class H2CharacterRepository implements ICharacterRepository {

    private final Map<UUID, IORPCharacter> cachedCharacters = new ConcurrentHashMap<>(32);
    private final Map<UUID, UUID> cachedActiveCharacters = new ConcurrentHashMap<>(32);

    @Override
    public CompletableFuture<IORPCharacter> get(UUID characterId) {

        CompletableFuture<IORPCharacter> characterCompletableFuture = new CompletableFuture<>();

        if (cachedCharacters.containsKey(characterId)) {
            characterCompletableFuture.complete(cachedCharacters.get(characterId));
            return characterCompletableFuture;
        }

        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {

            try {

                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                Statement characterStatement = connection.createStatement();
                ResultSet characterResult =
                        characterStatement.executeQuery(
                                "SELECT * FROM Characters LIMIT 1 WHERE characterId=" + characterId
                        );

                if (characterResult.next()) {

                    UUID playerId = UUID.fromString(characterResult.getString("playerId"));
                    characterResult.close();

                    Statement fieldStatement = connection.createStatement();
                    ResultSet fieldResult =
                            fieldStatement.executeQuery(
                                    "SELECT * FROM CharacterFields WHERE characterId=" + characterId
                            );

                    Map<String, ORPCharacterField> fields = CharacterFieldRegistry.makeFields(characterId);
                    while (fieldResult.next()) {
                        ORPCharacterField field = fields.get(fieldResult.getString("fieldName"));
                        field.setFieldValue(fieldResult.getString("fieldValue"));
                        fields.put(field.getFieldName(), field);
                    }

                    fieldResult.close();

                    IORPCharacter character = new ORPCharacter(characterId, playerId, fields);
                    characterCompletableFuture.complete(character);

                    cachedCharacters.put(characterId, character);

                } else {
                    characterResult.close();
                    throw new RuntimeException("No character with ID " + characterId + " exists!");
                }

            } catch (SQLException exception) {
                throw new RuntimeException("Could not get character with ID " + characterId +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return characterCompletableFuture;
    }

    @Override
    public CompletableFuture<Map<UUID, IORPCharacter>> getByPlayer(UUID playerUUID) {

        CompletableFuture<Map<UUID, IORPCharacter>> charactersCompletableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {

            try {

                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                Statement characterStatement = connection.createStatement();
                ResultSet characterResult =
                        characterStatement.executeQuery(
                                "SELECT * FROM Characters WHERE playerId=" + playerUUID
                        );

                Map<UUID, IORPCharacter> characters = new HashMap<>();

                while (characterResult.next()) {

                    UUID characterId = UUID.fromString(characterResult.getString("playerId"));

                    Statement fieldStatement = connection.createStatement();
                    ResultSet fieldResult =
                            fieldStatement.executeQuery(
                                    "SELECT * FROM CharacterFields WHERE characterId=" + characterId
                            );

                    Map<String, ORPCharacterField> fields = CharacterFieldRegistry.makeFields(characterId);
                    while (fieldResult.next()) {
                        ORPCharacterField field = fields.get(fieldResult.getString("fieldName"));
                        field.setFieldValue(fieldResult.getString("fieldValue"));
                        fields.put(field.getFieldName(), field);
                    }

                    fieldResult.close();

                    IORPCharacter character = new ORPCharacter(characterId, playerUUID, fields);
                    characters.put(characterId, character);
                }
                characterResult.close();

                charactersCompletableFuture.complete(characters);

            } catch (SQLException exception) {
                throw new RuntimeException("Could not get characters for player " + playerUUID +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return charactersCompletableFuture;
    }

    @Override
    public CompletableFuture<Map<UUID, IORPCharacter>> getByPlayer(OfflinePlayer player) {
        return getByPlayer(player.getUniqueId());
    }

    @Override
    public CompletableFuture<Boolean> add(IORPCharacter character) {

        CompletableFuture<Boolean> resultCompletableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {
            try {

                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                PreparedStatement addCharacterStatement = connection.prepareStatement(
                        "INSERT INTO Characters (characterId, playerId) VALUES (?, ?)"
                );
                addCharacterStatement.setString(1, character.getCharacterUUID().toString());
                addCharacterStatement.setString(2, character.getPlayerUUID().toString());
                addCharacterStatement.executeUpdate();

                for (ORPCharacterField field : character.getFields().values()) {
                    PreparedStatement addFieldStatement = connection.prepareStatement(
                            "INSERT INTO CharacterFields (characterId, fieldName, fieldValue) VALUES (?, ?, ?)"
                    );
                    addFieldStatement.setString(1, character.getCharacterUUID().toString());
                    addFieldStatement.setString(2, field.getFieldName());
                    addFieldStatement.setString(3, field.getFieldValue());
                    addFieldStatement.executeUpdate();
                }

                cachedCharacters.put(character.getCharacterUUID(), character);

                resultCompletableFuture.complete(true);

            } catch (SQLException exception) {
                resultCompletableFuture.complete(false);
                throw new RuntimeException("Could not add character " + character.getCharacterUUID() +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return resultCompletableFuture;
    }

    @Override
    public CompletableFuture<Boolean> remove(IORPCharacter character) throws RepositoryException {

        CompletableFuture<Boolean> resultCompletableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {
            try {

                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                Statement deleteCharacterStatement = connection.createStatement();
                deleteCharacterStatement.executeUpdate(
                        "DELETE FROM Characters WHERE characterId=" + character.getCharacterUUID()
                );

                Statement deleteFieldStatement = connection.createStatement();
                deleteFieldStatement.executeUpdate(
                        "DELETE FROM CharacterFields WHERE characterId=" + character.getCharacterUUID()
                );

                //TODO: change player's currently selected character?

                cachedCharacters.remove(character.getCharacterUUID());

                resultCompletableFuture.complete(true);

            } catch (SQLException exception) {
                resultCompletableFuture.complete(false);
                throw new RuntimeException("Could not remove character " + character.getCharacterUUID() +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return resultCompletableFuture;
    }

    @Override
    public CompletableFuture<Boolean> update(IORPCharacter character) {

        CompletableFuture<Boolean> resultCompletableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {
            try {
                Connection connection = OpenRP.getInstance().getDatabase().getConnection();

                for (ORPCharacterField field : character.getFields().values()) {
                    PreparedStatement addFieldStatement = connection.prepareStatement(
                            "UPDATE CharacterFields SET fieldValue=? WHERE characterId=? AND fieldName=?"
                    );
                    addFieldStatement.setString(1, field.getFieldValue());
                    addFieldStatement.setString(2, character.getCharacterUUID().toString());
                    addFieldStatement.setString(3, field.getFieldName());
                    addFieldStatement.executeUpdate();
                }

                cachedCharacters.put(character.getCharacterUUID(), character);

                resultCompletableFuture.complete(true);

            } catch (SQLException exception) {
                resultCompletableFuture.complete(false);
                throw new RuntimeException("Could not update character " + character.getCharacterUUID() +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return resultCompletableFuture;
    }

    @Override
    public CompletableFuture<UUID> getActiveCharacter(UUID playerUUID) {
        CompletableFuture<UUID> activeCompletableFuture = new CompletableFuture<>();

        if (cachedActiveCharacters.containsKey(playerUUID)) {
            activeCompletableFuture.complete(cachedActiveCharacters.get(playerUUID));
            return activeCompletableFuture;
        }

        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {

            try {

                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                Statement characterStatement = connection.createStatement();
                ResultSet characterResult =
                        characterStatement.executeQuery(
                                "SELECT characterId FROM ActiveCharacters LIMIT 1 WHERE playerId=" + playerUUID
                        );

                if (characterResult.next()) {

                    UUID characterId = UUID.fromString(characterResult.getString("characterId"));
                    characterResult.close();

                    activeCompletableFuture.complete(characterId);
                    cachedActiveCharacters.put(characterId, characterId);

                } else {
                    characterResult.close();
                    throw new RuntimeException("No active character for player " + playerUUID + " exists!");
                }

            } catch (SQLException exception) {
                throw new RuntimeException("Could not find active character for player " + playerUUID +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return activeCompletableFuture;
    }

    @Override
    public CompletableFuture<UUID> getActiveCharacter(OfflinePlayer player) {
        return getActiveCharacter(player.getUniqueId());
    }

    @Override
    public CompletableFuture<Boolean> setActiveCharacter(UUID playerUUID, UUID characterId) {

        CompletableFuture<Boolean> resultCompletableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(OpenRP.getInstance(), () -> {
            try {
                Connection connection = OpenRP.getInstance().getDatabase().getConnection();
                Statement existsStatement = connection.createStatement();
                ResultSet existsResult =
                        existsStatement.executeQuery(
                                "SELECT * FROM ActiveCharacters WHERE playerId=" + playerUUID
                        );

                if (existsResult.next()) {

                    Statement updateStatement = connection.createStatement();
                    updateStatement.executeQuery(
                            "UPDATE ActiveCharacters SET characterId=" + characterId +
                                    " WHERE playerId=" + playerUUID
                    );

                } else {

                    Statement insertStatement = connection.createStatement();
                    insertStatement.executeQuery(
                            "INSERT INTO ActiveCharacters (playerId, characterId)" +
                                    "VALUES (" +
                                    playerUUID + ", " +
                                    characterId + ")"
                    );

                }
                existsResult.close();

                cachedActiveCharacters.put(playerUUID, characterId);
                resultCompletableFuture.complete(true);

            } catch (SQLException exception) {
                resultCompletableFuture.complete(false);
                throw new RuntimeException("Could not set active character for player " + playerUUID +
                        "(H2 ERROR): " + exception.getMessage());
            }
        });

        return resultCompletableFuture;
    }

    @Override
    public CompletableFuture<Boolean> setActiveCharacter(OfflinePlayer player, UUID characterId) {
        return setActiveCharacter(player.getUniqueId(), characterId);
    }

}
