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
