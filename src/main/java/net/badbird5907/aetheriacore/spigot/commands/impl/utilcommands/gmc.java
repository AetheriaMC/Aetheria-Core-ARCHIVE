package net.badbird5907.aetheriacore.spigot.commands.impl.utilcommands;

import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.util.Gamemode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class gmc implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0){
            if(sender.hasPermission(Permission.GMC.node)){
                if(sender instanceof Player){
                    Gamemode.setGameMode((Player) sender, GameMode.CREATIVE);
                    return true;
                }
            }else throw new NoPermsError((Player) sender, "gmc");
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
                    Gamemode.setGameMode(target, GameMode.CREATIVE);
                    sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Creative");
                    return true;
                }
            }else throw new NoPermsError((Player) sender, "gmc");
        }
        return true;
    }
}