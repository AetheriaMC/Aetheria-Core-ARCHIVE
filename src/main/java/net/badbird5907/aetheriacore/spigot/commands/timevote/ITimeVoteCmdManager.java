package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ITimeVoteCmdManager {
    public static void Process(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args){
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is player only! use time set instead.");
        }
        if(args.length == 0) {
            new TimeVoteGUI().open((Player) sender, "main");
        }
        if(args.length < 0){
            if((TimeVoteCommandManager.commandstr.contains(args[0]))){
                String[] arg = args.toString().replaceFirst(args[0], "").split(" ");
                TimeVoteCommandManager.TimeVoteCommands.get(args[0]).execute((Player) sender, arg, page(args) ,AetheriaCore.getInstance());
            }
        }
    }
    public static String page(String[] args){
        if(args[0].equalsIgnoreCase("vote"))
            return "vote";
        if(args[0].equalsIgnoreCase("yes"))
            return "yes";
        if(args[0].equalsIgnoreCase("no"))
            return "no";
        if(args[0].equalsIgnoreCase("create"))
            return "create";
        else
            return "main";
    }
}
