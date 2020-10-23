package net.badbird5907.aetheriacore.spigot.commands.trolls;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MassSay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        if(sender.hasPermission(permissionManager.MassSay)){
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(VanishAPI.isInvisible(player)){
                    break;
                }
                else{
                    player.chat(allArgs);
                }
            }
        }
        return true;
    }
}
