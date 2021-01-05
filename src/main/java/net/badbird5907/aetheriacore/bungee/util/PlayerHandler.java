package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import static net.badbird5907.aetheriacore.bungee.util.Permission.*;
import static net.badbird5907.aetheriacore.bungee.util.Ranks.*;

public class PlayerHandler {
	public static String playerwithrank(ProxiedPlayer player) {
		return player.hasPermission(STAFF_OWNER.node) ? (OWNER.getString() + player.getName()) : (player.hasPermission(STAFF_ADMIN.node) ? (ADMIN.getString() + player.getName()) : (player.hasPermission(STAFF_MOD.node) ? (MOD.getString() + player.getName()) : (player.hasPermission(STAFF_HELPER.node) ? (HELPER.getString() + player.getName()) : (player.hasPermission(STAFF_DEV.node) ? (DEV.getString() + player.getName()) : (player.hasPermission(STAFF_BUILDER.node) ? (BUILDER.getString() + player.getName()) : (player.hasPermission(STAFF_LM.node) ? (LM.getString() + player.getName()) : (player.hasPermission(STAFF_TRIAL_DEV.node) ? (TRIAL_DEV.getString() + player.getName()) : (player.hasPermission(STAFF_TRIAL_BUILDER.node) ? (TRIAL_BUILDER.getString() + player.getName()) : (player.hasPermission(STAFF_TRIAL_LM.node) ? (TRIAL_LM.getString() + player.getName()) : player.getName())))))))));
	}

	public static String RankName(ProxiedPlayer player) {
		return player.hasPermission(STAFF_OWNER.node) ? "OWNER" : (player.hasPermission(STAFF_ADMIN.node) ? "ADMIN" : (player.hasPermission(STAFF_MOD.node) ? "MOD" : (player.hasPermission(STAFF_HELPER.node) ? "HELPER" : (player.hasPermission(STAFF_DEV.node) ? "DEV" : (player.hasPermission(STAFF_BUILDER.node) ? "BUILDER" : (player.hasPermission(STAFF_LM.node) ? "LOREMASTER" : (player.hasPermission(STAFF_TRIAL_DEV.node) ? "TRIAL_DEV" : (player.hasPermission(STAFF_TRIAL_BUILDER.node) ? "TRIAL_BUILDER" : (player.hasPermission(STAFF_TRIAL_LM.node) ? "TRIAL_LOREMASTER" : "No rank")))))))));
	}
}
