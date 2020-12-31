package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.reload;
import static net.badbird5907.aetheriacore.spigot.util.itemtypes.allitems;

public class TabComplete implements TabCompleter {
	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
		List<String> l = new ArrayList<>();
		if (cmd.getName().equalsIgnoreCase("aetheriacore")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 1) {
					l.addAll(asList("help", "debug"));
					if (player.hasPermission(reload)) l.add("reload");
				}
				if ((args.length == 2) && args[0].equalsIgnoreCase("debug")) l.addAll(asList("on", "off"));

			} else l.addAll(asList("reload", "help", "debug"));
			return l;
		}
		if (cmd.getName().equalsIgnoreCase("item")) {
			if (args.length == 1) {
				l = allitems.stream().filter(a -> a.toUpperCase().startsWith(args[0].toUpperCase())).collect(Collectors.toList());
				return l;
			}
			if (args.length == 1) return allitems;
			if (args.length == 2) l.addAll(asList("1", "10", "20", "32", "64"));
			return l;

			//return itemtypes.allitems;
		}
		if (cmd.getName().equalsIgnoreCase("adminmusic")) {
			if (args.length == 0) {
				l.addAll(asList("reload", "player", "play", "stop", "setItem", "download", "shuffle", "volume", "random", "next"));
				return l;
			}
            /*
            if(args.length < 1){
                if(args[0].equalsIgnoreCase("play")){
                    if(args[1].isEmpty()){
                        for(Player p:Bukkit.getOnlinePlayers()){
                            l.add(p.getDisplayName());
                            return l;
                        }
                    }
                }
                if(args[0].equalsIgnoreCase("")){

                }
            }
             */
		}
		return null;
	}
}
