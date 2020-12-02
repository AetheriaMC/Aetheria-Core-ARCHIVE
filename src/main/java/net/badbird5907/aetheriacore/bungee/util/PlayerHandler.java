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
    public static String RankName(ProxiedPlayer player){
        if(player.hasPermission(Permission.STAFF_OWNER.node))
            return "OWNER";
        if(player.hasPermission(Permission.STAFF_ADMIN.node))
            return "ADMIN";
        if(player.hasPermission(Permission.STAFF_MOD.node))
            return "MOD";
        if(player.hasPermission(Permission.STAFF_HELPER.node))
            return "HELPER";
        if(player.hasPermission(Permission.STAFF_DEV.node))
            return "DEV";
        if(player.hasPermission(Permission.STAFF_BUILDER.node))
            return "BUILDER";
        if(player.hasPermission(Permission.STAFF_LM.node))
            return "LOREMASTER";
        if(player.hasPermission(Permission.STAFF_TRIAL_DEV.node))
            return "TRIAL_DEV";
        if(player.hasPermission(Permission.STAFF_TRIAL_BUILDER.node))
            return "TRIAL_BUILDER";
        if(player.hasPermission(Permission.STAFF_TRIAL_LM.node))
            return "TRIAL_LOREMASTER";
        else
            return "No rank";
    }
}
