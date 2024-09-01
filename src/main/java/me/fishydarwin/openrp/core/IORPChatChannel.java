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

package me.fishydarwin.openrp.core;

import net.kyori.adventure.audience.Audience;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Formatter;
import java.util.Set;

/**
 * Represents an OpenRP Chat Channel.
 */
public interface IORPChatChannel {

    /**
     * Gets the name of the channel.
     * @return The name of the channel.
     */
    @NotNull String getName();

    /**
     * // TODO: darwin said to add this. pretty sure it's for formatting? unsure.
     * @return // TODO
     */
    @NotNull Set<Formatter> getFormatters();

    /**
     * // TODO: will return a set of every viewer that can view this channel based on the player
     * @param player The player in question.
     * @return // TODO
     */
    @NotNull Set<Audience> getViewers(@NotNull IORPPlayer player);

    /**
     * // TODO: this method will check if the player has a cooldown or not
     * @param player The player in question.
     * @return // TODO
     */
    boolean canSend(@NotNull IORPPlayer player);

    /**
     * // TODO: will see if the player can speak in the channel with a use perm
     * @param player The player in question.
     * @return // TODO
     */
    boolean hasPermission(@NotNull IORPPlayer player);

    /**
     * // TODO: this will register the /(channel) <message> command
     */
    void registerCommand();

}
