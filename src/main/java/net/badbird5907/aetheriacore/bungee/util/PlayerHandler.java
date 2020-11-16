package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerHandler {
    public static String playerwithrank(ProxiedPlayer player) {
        if(player.hasPermission(Permission.STAFF_OWNER.node))
            return Ranks.OWNER + player.getName();
        if(player.hasPermission(Permission.STAFF_ADMIN.node))
            return Ranks.ADMIN + player.getName();
        if(player.hasPermission(Permission.STAFF_MOD.node))
            return Ranks.MOD + player.getName();
        if(player.hasPermission(Permission.STAFF_HELPER.node))
            return Ranks.HELPER + player.getName();
        if(player.hasPermission(Permission.STAFF_DEV.node))
            return Ranks.DEV + player.getName();
        if(player.hasPermission(Permission.STAFF_BUILDER.node))
            return Ranks.BUILDER + player.getName();
        if(player.hasPermission(Permission.STAFF_LM.node))
            return Ranks.LM + player.getName();
        if(player.hasPermission(Permission.STAFF_TRIAL_DEV.node))
            return Ranks.TRIAL_DEV + player.getName();
        if(player.hasPermission(Permission.STAFF_TRIAL_BUILDER.node))
            return Ranks.TRIAL_BUILDER + player.getName();
        if(player.hasPermission(Permission.STAFF_TRIAL_LM.node))
            return Ranks.TRIAL_LM + player.getName();
        else
            return player.getName();
    }
}
