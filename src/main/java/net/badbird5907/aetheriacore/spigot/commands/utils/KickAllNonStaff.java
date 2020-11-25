package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KickAllNonStaff implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender.hasPermission(Permission.KICK_ALL.node)) {
            if (args.length < 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++){
                    sb.append(args[i]).append(" ");
                }
                String allArgs = sb.toString().trim();
                net.badbird5907.aetheriacore.spigot.util.KickAllNonStaff.KickAll(allArgs);
            }
        }
        return true;
    }
}
