package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class QuickChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }

        String allArgs = sb.toString().trim();
        StaffChatMessage.sendmessage(sender.getName(), sb.toString().trim());
        return true;
    }
}
