package net.badbird5907.aetheriacore.spigot.commands;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffMode implements CommandExecutor {
    public static List<String> StaffModeToggle = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission(permissionManager.StaffMode)){
            //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + player.getName());
            staffchat.staffchatToggle.add(player.getName());
            hush.hush.remove(player.getName());
            VanishAPI.hidePlayer((Player) player);
        }
        return true;
    }
}
