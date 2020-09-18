package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;


import static org.bukkit.Bukkit.getServer;

public class levitate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ConsoleCommandSender console = getServer().getConsoleSender();

        if (sender.hasPermission(permissionManager.Levitate)){
            Player target = Bukkit.getPlayerExact(args[0]);

            if(args.length == 0){
                sender.sendMessage(ChatColor.RED + "Usage: /levitate <Player>");
            }
            else{
                if(target instanceof Player){
                    target.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 1));
                }
                else{
                    sender.sendMessage(ChatColor.RED + "Error: " + target.getDisplayName() + " Is Not A Player!");
                }
            }
        }
        else{
            sender.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
