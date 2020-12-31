package net.badbird5907.aetheriacore.spigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static net.badbird5907.aetheriacore.spigot.util.itemtypes.alist;

public class test implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, String s, String[] args) {
        String a = Arrays.toString(alist.toArray());
        sender.sendMessage(a);
        return true;
    }
}
