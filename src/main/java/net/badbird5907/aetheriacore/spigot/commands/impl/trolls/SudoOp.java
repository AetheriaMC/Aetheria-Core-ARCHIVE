package net.badbird5907.aetheriacore.spigot.commands.impl.trolls;

import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SudoOp implements CommandExecutor {
    public static List<String> SudoOp = new ArrayList<String>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = Bukkit.getPlayerExact(args[0]);
        //Player player = (Player) sender;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        if(args.length > 2){
            sender.sendMessage(PluginManager.prefix + ChatColor.RED + "Usage: /sudoop <Player> <Command>");
            return true;
        }
        if(args.length < 2){
            if(SudoOp.contains(sender.getName())){
                if(target instanceof Player){
                    Bukkit.dispatchCommand(target, allArgs);
                    sender.sendMessage(PluginManager.prefix + ChatColor.WHITE + allArgs + " Will be run by " + target.getDisplayName());
                    return true;
                }
                else {
                    sender.sendMessage(PluginManager.prefix + ChatColor.RED + "Usage: /sudoop <Player> <Command>");
                    return true;
                }

            }
            else{
                sender.sendMessage(PluginManager.prefix + ChatColor.RED + "You are not whitelisted to run this command.");
                return true;
            }
        }
        else{
            sender.sendMessage(PluginManager.prefix + ChatColor.RED +"Usage: /sudoop <Player> <Command>");
            return true;
        }

    }
}
