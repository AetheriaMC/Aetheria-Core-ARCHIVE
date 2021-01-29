package net.badbird5907.aetheriacore.spigot.features.msg;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Msg implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender);
            if(player.hasPermission(Permission.MSG.node)){
                if(!AetheriaCore.banHook.isMuted(player.getUniqueId())){

                }
            }
        }
        return true;
    }
}
