package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.badbird5907.aetheriacore.spigot.util.NPC;

public class CreateNPC implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String strings, String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        if(command.getName().equalsIgnoreCase("createnpc")){
            if(!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            if(args.length == 1){
                String allArgsColors = allArgs.replace("&", "§");
                NPC.createNPC(player, allArgsColors);
                player.sendMessage("NPC Created!");
            }
            else{
                player.sendMessage(pluginManager.prefix + "Must use a name!");
            }
        }
        return true;
    }
}