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

package me.fishydarwin.openrp.commands;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Enforces several command functions so that the programmer can account for
 * all the potential cases of an OpenRP command being run.
 */
public interface IORPCommand {

    /**
     * Enforces execution as Console.
     * @param sender the Console
     * @param command the command being run
     * @param label the label of the command
     * @param args the args given to the command
     * @return True or False, as defined in execute() of org.bukkit.command.CommandExecutor
     */
    boolean executeAsConsole(ConsoleCommandSender sender, Command command, String label, String[] args);

    /**
     * Enforces execution as a player.
     * @param sender the player
     * @param command the command being run
     * @param label the label of the command
     * @param args the args given to the command
     * @return True or False, as defined in execute() of org.bukkit.command.CommandExecutor
     */
    boolean executeAsPlayer(Player sender, Command command, String label, String[] args);

    /**
     * Enforces execution as a command block
     * @param sender the command block
     * @param command the command being run
     * @param label the label of the command
     * @param args the args given to the command
     * @return True or False, as defined in execute() of org.bukkit.command.CommandExecutor
     */
    boolean executeAsCommandBlock(BlockCommandSender sender, Command command, String label, String[] args);

}
