package net.badbird5907.aetheriacore.spigot.commands.impl.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVision implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission(permissionManager.NightVision)){
                if(player.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    return true;
                }
                else{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2147483647, 1, true));
                    return true;
                }
            }

        }
        else{
            sender.sendMessage(PluginManager.prefix + ChatColor.RED +  "You Must be a player to do this!");
        }

        return true;
    }
}
