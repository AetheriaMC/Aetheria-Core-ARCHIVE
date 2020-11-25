package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerHandler {
    public static String playerwithrank(ProxiedPlayer player) {
        if(player.hasPermission(Permission.STAFF_OWNER.node))
            return Ranks.OWNER.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_ADMIN.node))
            return Ranks.ADMIN.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_MOD.node))
            return Ranks.MOD.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_HELPER.node))
            return Ranks.HELPER.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_DEV.node))
            return Ranks.DEV.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_BUILDER.node))
            return Ranks.BUILDER.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_LM.node))
            return Ranks.LM.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_TRIAL_DEV.node))
            return Ranks.TRIAL_DEV.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_TRIAL_BUILDER.node))
            return Ranks.TRIAL_BUILDER.getString() + player.getName();
        if(player.hasPermission(Permission.STAFF_TRIAL_LM.node))
            return Ranks.TRIAL_LM.getString() + player.getName();
        else
            return player.getName();
    }
}
