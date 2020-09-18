package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;

public class god implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission(permissionManager.god)){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(args.length == 0){
                if (player.isInvulnerable()){
                    player.setInvulnerable(false);
                    player.sendMessage(ChatColor.GOLD + "God Mode:" + ChatColor.RED + " DISABLED");

                }
                else {
                    player.setInvulnerable(true);
                    player.sendMessage(ChatColor.GOLD + "God Mode:" + ChatColor.GREEN + " DISABLED");
                }

            }
            else{
                if (target instanceof Player){
                    target.setInvulnerable(true);
                }
                else {
                    player.sendMessage(ChatColor.RED + "ERROR: Usage: /god <Player>");
                }
            }
        }
        else{
            player.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
