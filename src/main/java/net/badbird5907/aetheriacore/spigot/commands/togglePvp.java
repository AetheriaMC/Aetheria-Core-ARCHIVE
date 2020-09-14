package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class togglePvp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission("aetheriacore.togglepvp")){

        }
        return true;
    }
}
