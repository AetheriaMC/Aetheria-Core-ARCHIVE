package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static java.util.Arrays.asList;
import static org.bukkit.ChatColor.*;

public class rules implements CommandExecutor {

	//method called on /aetheriacore
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		asList(BLUE + "" + BOLD + "===========RULES OF AETHERIA===========", RED + "" + BOLD + "[1]" + GOLD + "Do not grief or steal other's belongings or builds", RED + "" + BOLD + "[2]" + GOLD + "Keep the server PG, do not use inappropriate words, skins or names", RED + "" + BOLD + "[3]" + GOLD + "No alts if one of your accounts are banned AKA:Ban-Evading", RED + "" + BOLD + "[4]" + GOLD + "Do not hack or use any unapproved mods", RED + "" + BOLD + "[5]" + GOLD + "Do not abuse your permissions", RED + "" + BOLD + "[6]" + GOLD + "What the moderators say is final", RED + "" + BOLD + "[7]" + GOLD + "Do not harass or disrespect others", RED + "" + BOLD + "[8]" + GOLD + "Do not advertise your own server within the server unless you have permission", RED + "" + BOLD + "[9]" + GOLD + "Always listen to staff", RED + "" + BOLD + "[10]" + GOLD + "Do not advertise anything not related to server unless you have permission").forEach(player::sendMessage);
		return true;
	}
}