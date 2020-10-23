package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DupeThis implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission(permissionManager.dupethis)){
            ItemStack item = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();
            player.getInventory().addItem(item);
        }
        else{
            player.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
