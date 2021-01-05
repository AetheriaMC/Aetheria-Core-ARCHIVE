package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import static net.badbird5907.aetheriacore.bungee.util.Permission.*;
import static net.md_5.bungee.api.ChatColor.*;

public enum Ranks {
	OWNER(DARK_RED + "" + BOLD + "[Owner]" + RESET + "" + DARK_RED + " "),
	ADMIN(RED + "" + BOLD + "[Admin]" + RESET + "" + RED + " "),
	MOD(DARK_PURPLE + "" + BOLD + "[Mod]" + RESET + "" + DARK_PURPLE + " "),
	HELPER(BLUE + "" + BOLD + "[Helper]" + RESET + "" + BLUE + " "),
	DEV(GOLD + "" + BOLD + "[Dev]" + RESET + "" + GOLD + " "),
	BUILDER(YELLOW + "" + BOLD + "[Builder]" + RESET + "" + YELLOW + " "),
	LM(YELLOW + "" + BOLD + "[LoreMaster]" + RESET + "" + YELLOW + " "),
	TRIAL_DEV(GOLD + "" + BOLD + "[Trial-Dev]" + RESET + "" + GOLD + " "),
	TRIAL_BUILDER(YELLOW + "" + BOLD + "[Trial-Builder]" + RESET + "" + YELLOW + " "),
	TRIAL_LM(YELLOW + "" + BOLD + "[Trial-LoreMaster]" + RESET + "" + YELLOW + " ");
	//ChatColor.translateAlternateColorCodes('&', rank);
	public String rank;

	Ranks(String rank) {
		this.rank = rank;
	}

	public String getString() {
		return rank;
	}

	private String playerwithrank(ProxiedPlayer player) {
		return player.hasPermission(STAFF_OWNER.node) ? (OWNER + player.getName()) : (player.hasPermission(STAFF_ADMIN.node) ? (ADMIN + player.getName()) : (player.hasPermission(STAFF_MOD.node) ? (MOD + player.getName()) : (player.hasPermission(STAFF_HELPER.node) ? (HELPER + player.getName()) : (player.hasPermission(STAFF_DEV.node) ? (DEV + player.getName()) : (player.hasPermission(STAFF_BUILDER.node) ? (BUILDER + player.getName()) : (player.hasPermission(STAFF_LM.node) ? (LM + player.getName()) : (player.hasPermission(STAFF_TRIAL_DEV.node) ? (TRIAL_DEV + player.getName()) : (player.hasPermission(STAFF_TRIAL_BUILDER.node) ? (TRIAL_BUILDER + player.getName()) : (player.hasPermission(STAFF_TRIAL_LM.node) ? (TRIAL_LM + player.getName()) : player.getName())))))))));
	}
}