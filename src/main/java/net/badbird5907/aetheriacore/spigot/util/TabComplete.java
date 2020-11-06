package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.commands.utils.item;
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
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> l = new ArrayList<String>();
        if (cmd.getName().equalsIgnoreCase("aetheriacore") && args.length >= 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 1) {
                    l.add("help");
                    l.add("debug");
                    if (player.hasPermission(permissionManager.reload)) {
                        l.add("reload");
                    }
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("debug")) {
                        l.add("on");
                        l.add("off");
                    }
                }

            } else {
                l.add("reload");
                l.add("help");
                l.add("debug");
            }
            return l;
        }
        if (cmd.getName().equalsIgnoreCase("item")) {
            if(args.length == 1){
                for(String a : itemtypes.allitems){
                    if(a.toUpperCase().startsWith(args[0].toUpperCase())){
                        l.add(a.toString());
                    }
                }
                return l;
            }
            /*
            if(args.length == 0 )
                return itemtypes.allitems;
             */

            if(args.length == 1)
                return itemtypes.allitems;

            if(args.length == 2){
                l.add("1");
                l.add("10");
                l.add("20");
                l.add("32");
                l.add("64");
                return l;
            }
            else
                return l;

            //return itemtypes.allitems;
        }

        return null;
    }
}

