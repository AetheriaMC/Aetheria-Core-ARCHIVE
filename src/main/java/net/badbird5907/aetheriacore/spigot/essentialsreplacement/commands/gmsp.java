package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;

public class gmsp implements CommandExecutor {
    permissionManager permM;
    public gmsp(permissionManager permM) {
        this.permM = permM;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission(permissionManager.gmsp)){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(args.length == 0){
                player.setGameMode(GameMode.SPECTATOR);
            }
            else{
                if (target instanceof Player){
                    player.setGameMode(GameMode.SPECTATOR);

                }
                else {
                    player.sendMessage(ChatColor.RED + "ERROR: Usage: /gms <Player>");
                }
            }
        }
        else{
            permM.permissionMessage("gamemode.spectator");
        }
        return true;
    }
}
