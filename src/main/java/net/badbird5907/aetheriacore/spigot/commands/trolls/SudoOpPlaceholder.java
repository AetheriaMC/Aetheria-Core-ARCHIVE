package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SudoOpPlaceholder implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(PluginManager.prefix + ChatColor.RED + "This command still has some bugs and may crash the server.");
        sender.sendMessage(ChatColor.RED + "So it is disabled.");
        return true;
    }
}
