package net.badbird5907.aetheriacore.spigot.commands.staff;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class QuickChat implements CommandExecutor {
    AetheriaCore plugin;
    public QuickChat(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        if(player.hasPermission(permissionManager.staffchat)){
            StaffChatMessage.sendMessage(player.getName(), allArgs);
        }
        else{
            player.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
