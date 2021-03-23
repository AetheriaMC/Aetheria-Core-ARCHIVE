package net.badbird5907.aetheriacore.spigot.commands.impl.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class rules implements CommandExecutor {

    //method called on /aetheriacore
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "===========RULES OF AETHERIA===========");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[1]" + ChatColor.GOLD + "Do not grief or steal other's belongings or builds");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[2]" + ChatColor.GOLD + "Keep the server PG, do not use inappropriate words, skins or names");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[3]" + ChatColor.GOLD + "No alts if one of your accounts are banned AKA:Ban-Evading");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[4]" + ChatColor.GOLD + "Do not hack or use any unapproved mods");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[5]" + ChatColor.GOLD + "Do not abuse your permissions");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[6]" + ChatColor.GOLD + "What the moderators say is final");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[7]" + ChatColor.GOLD + "Do not harass or disrespect others");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[8]" + ChatColor.GOLD + "Do not advertise your own server within the server unless you have permission");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[9]" + ChatColor.GOLD + "Always listen to staff");
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[10]"+ ChatColor.GOLD + "Do not advertise anything not related to server unless you have permission");
        return true;
    }
}
