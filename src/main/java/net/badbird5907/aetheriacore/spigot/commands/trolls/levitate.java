package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


import static org.bukkit.Bukkit.getServer;

public class levitate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ConsoleCommandSender console = getServer().getConsoleSender();

        if (sender.hasPermission("aetheriacore.levitate")){
            Player target = Bukkit.getPlayerExact(args[0]);

            if(args[0].length() == 0){
                sender.sendMessage(ChatColor.RED + "Usage: /levitate <Player>");
            }
            else{
                if(target instanceof Player){
                    Bukkit.dispatchCommand(console, "effect give " + target.getDisplayName() + " minecraft:levitation 999999 127");
                }
                else{
                    sender.sendMessage(ChatColor.RED + "Error: " + target.getDisplayName() + " Is Not A Player!");
                }
            }
        }
        else{
            sender.sendMessage(ChatColor.RED + "You don't have the required permission node 'aetheriacore.levitate' to execute this command.");
        }
        return true;
    }
}
