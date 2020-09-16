package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class discord implements CommandExecutor {

    AetheriaCore plugin;
    public discord(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    //method called on /discord
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission(permissionManager.discord)){
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Join Our Discord For Announcements");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "On Game Updates And Giveaways!");
            player.sendMessage(ChatColor.BLUE + "https://discord.gg/TFbnNvy" /*+ plugin.getConfig().get()*/ );
        }
        else{
            permissionManager.permissionMessage("discord");
        }
        return true;
    }
}