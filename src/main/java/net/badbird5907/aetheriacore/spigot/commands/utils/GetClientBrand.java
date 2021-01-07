package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetClientBrand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1){
            Player target = null;
            Boolean check;
            try {
                 target = Bukkit.getPlayerExact(args[0]);
                 check =true;
            } catch (Exception e) {
                check = false;
                sender.sendMessage(ChatColor.RED + args[0] + " Is not a player!");
            }
            if(check){
                if(sender.hasPermission(permissionManager.getclientbrand))
                    sender.sendMessage(target.getDisplayName() + "'s client brand is " + target.getClientBrandName());
                else
                    throw new NoPermsError((Player)sender, "getclientbrand");
            }
            return true;
        }
        else{
            sender.sendMessage(ChatColor.RED + "USAGE: /getclientbrand <player>");
            return true;
        }

    }
}
