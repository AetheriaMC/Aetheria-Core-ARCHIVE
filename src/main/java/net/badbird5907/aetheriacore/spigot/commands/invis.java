package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class invis implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("aetheriacore.invis")){
                player.sendMessage(ChatColor.GREEN + "You Now Have Invis For 8 Minutes!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9650, 1));
            }
            else{
                player.sendMessage(permissionManager.PermissionMessage);
            }
        }
        return true;
    }


}
