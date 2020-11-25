package net.badbird5907.aetheriacore.spigot;

import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class test implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        String a = Arrays.toString(itemtypes.alist.toArray());
        sender.sendMessage(a);
        return true;
    }
}
