package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
        List<String> l = new ArrayList<String>();
        if(cmd.getName().equalsIgnoreCase("aetheriacore") && args.length >= 0){
            if(sender instanceof Player){
                Player player = (Player) sender;
                l.add("help");
                l.add("debug");
                if(player.hasPermission(permissionManager.reload)) {
                    l.add("reload");
                }
            }
            else {
                l.add("reload");
                l.add("help");
                l.add("debug");
            }
            return l;
        }
        return null;
    }
}
