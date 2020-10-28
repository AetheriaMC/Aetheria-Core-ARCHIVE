package net.badbird5907.aetheriacore.spigot.commands.management.luckperms;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.data.DataMutateResult;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class addPermission implements CommandExecutor {
    AetheriaCore plugin;
    public addPermission(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Please specify a player & a group!");
            return true;
        }
        if (args.length == 2) {
            String playerName = args[0];
            String groupName = args[1];
            String target = args[0];
        }

        return true;
    }
}
