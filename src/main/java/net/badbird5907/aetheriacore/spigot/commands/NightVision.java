package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
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
        Player player = (Player) sender;
        if(sender instanceof Player){
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
            player.sendMessage(pluginManager.prefix + ChatColor.RED +  "You Must be a player to do this!");
        }

        return true;
    }
}
