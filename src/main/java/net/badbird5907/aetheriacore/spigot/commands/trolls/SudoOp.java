package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        //Player player = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);
        if(args.length < 2){
            if(SudoOp.contains(sender.getName())){
                if(target instanceof Player){
                    Bukkit.dispatchCommand(target, allArgs);
                    sender.sendMessage(pluginManager.prefix + ChatColor.WHITE + allArgs + " Will be run by " + target.getDisplayName());
                }
                else {
                    sender.sendMessage(pluginManager.prefix + ChatColor.RED + "Usage: /sudoop <Player> <Command>");
                    return true;
                }

            }
            else{
                sender.sendMessage(pluginManager.prefix + ChatColor.RED + "You are not whitelisted to run this command.");
            }
            return true;
        }
        else{
            sender.sendMessage(pluginManager.prefix + ChatColor.RED +"Usage: /sudoop <Player> <Command>");
            return true;
        }

    }
}
