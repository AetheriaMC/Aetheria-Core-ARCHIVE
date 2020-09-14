package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;


public class Fly implements CommandExecutor {

    permissionManager permM;
    public Fly(permissionManager permM) {
        this.permM = permM;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args[0].isEmpty()){
            Player target = Bukkit.getPlayerExact(args[0]);
            if (sender.hasPermission(new permissionManager().fly)){
                if(player.isFlying() == false){
                    player.setFlying(true);
                    player.sendMessage(ChatColor.GOLD + "Flight: Enabled");
                }
                else{
                    player.setFlying(false);
                    player.sendMessage(ChatColor.GOLD + "Flight: " + ChatColor.RED + "Disabled");
                }
            }
            else{
                permM.permissionMessage("[usesender]fly");
            }
            if(sender.hasPermission(new permissionManager().flyothers)) {
                if(target instanceof Player){

                }
                else{

                }
            }
            else{
                permM.permissionMessage2("fly.others");
            }
        }
        return true;
    }
}
