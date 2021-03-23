package net.badbird5907.aetheriacore.spigot.commands.impl.trolls;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class opme implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        player.sendMessage(ChatColor.RED + "You are no longer op.");
        return true;
    }
}
