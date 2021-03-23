package net.badbird5907.aetheriacore.spigot.commands.impl.management.luckperms;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class addPermission implements CommandExecutor {
    AetheriaCore plugin;
    public addPermission(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Please specify a player & a group!");
            return true;
        }
        if (args.length == 2) {
            String playerName = args[0];
            String groupName = args[1];
            String target = args[0];
        }

        return true;
    }
}
