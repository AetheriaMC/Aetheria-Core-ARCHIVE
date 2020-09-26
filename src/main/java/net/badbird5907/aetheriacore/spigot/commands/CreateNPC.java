package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.badbird5907.aetheriacore.spigot.util.NPC;

public class CreateNPC implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command command, String strings, String[] args) {
        if(command.getName().equalsIgnoreCase("createnpc")){
            if(!(sender instanceof Player)){
                return true;
            }
            Player player = (Player) sender;
            NPC.createNPC(player);
            player.sendMessage("NPC Created!");
        }
        return true;
    }
}
