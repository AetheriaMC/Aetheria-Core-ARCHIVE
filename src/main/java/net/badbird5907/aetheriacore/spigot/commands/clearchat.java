package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.loggingManager;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class clearchat implements CommandExecutor {

    AetheriaCore plugin;
    public clearchat(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    loggingManager logger;
    public clearchat(loggingManager logger) {
        this.logger = logger;
    }

    permissionManager permM;
    public clearchat(permissionManager permM) {
        this.permM = permM;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("clearchat")) {
                if (args.length == 0) {
                    if (sender.hasPermission("aetheriacore.clearchat")) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission("aetheriacore.bypass.clearchat")) {
                                player.sendMessage(ChatColor.GREEN + "Chat was cleared but you are immune!");
                                player.sendMessage(ChatColor.GREEN + sender.getName() + " Was the player who cleared the chat.");
                            } else {
                                for (int x = 0; x < 300; x++) {
                                    player.sendMessage(ChatColor.RED + "     ");
                                }
                                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "-------------------------------------------------------");
                                player.sendMessage(ChatColor.GREEN + "Chat Was Cleared By" + ChatColor.RED + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "!");
                                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "-------------------------------------------------------");
                            }
                            //clearchat
                        }
                    }
                    else{
                        permM.permissionMessage("clearchat");
                        logger.log(sender.getName() + " Was denied access to the command.");
                    }
                }
                else{
                    if (sender.hasPermission("aetheriacore.clearchat")) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission("aetheriacore.bypass.clearchat")) {
                                player.sendMessage(ChatColor.GREEN + "Chat was cleared but you are immune!");
                                player.sendMessage(ChatColor.GREEN + sender.getName() + " Was the player who cleared the chat.");
                            } else {
                                for (int x = 0; x < 300; x++) {
                                    player.sendMessage(ChatColor.RED + "     ");
                                }
                                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "-------------------------------------------------------");
                                player.sendMessage(ChatColor.GREEN + "Chat Was Cleared By" + ChatColor.RED + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "!");
                                player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "-------------------------------------------------------");
                            }
                            //clearchat
                        }
                    }

                }

            }

        } else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("aetheriacore.bypass.clearchat")) {
                    player.sendMessage(ChatColor.GREEN + "Chat was cleared but you are immune!");
                } else {
                    for (int x = 0; x < 300; x++) {
                        player.sendMessage(ChatColor.DARK_GRAY + "");
                    }
                    player.sendMessage(ChatColor.GREEN + "Chat Was Cleared By " + ChatColor.RED + ChatColor.BOLD + "Console" + ChatColor.GREEN + "!");
                }
            }

        }
        return true;
    }
}
