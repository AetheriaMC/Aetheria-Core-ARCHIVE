package net.badbird5907.aetheriacore.spigot.commands.utilcommands;

import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.util.Gamemode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class gmsp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0){
            if(sender.hasPermission(Permission.GMC.node)){
                if(sender instanceof Player){
                    Gamemode.setGameMode((Player) sender, GameMode.SPECTATOR);
                    return true;
                }
            }else throw new NoPermsError((Player) sender, "gmsp");
        }else if(args.length == 1){
            if(sender.hasPermission(Permission.GMC.node)){
                Player target = null;
                boolean pass;
                try{
                    target = Bukkit.getPlayerExact(args[0]);
                    pass = true;
                } catch (Exception e) {
                    pass = false;
                }
                if(pass){
                    Gamemode.setGameMode(target, GameMode.SPECTATOR);
                    sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Spectator");
                    return true;
                }
            }else throw new NoPermsError((Player) sender, "gmsp");
        }
        return true;
    }
}
