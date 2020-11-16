package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public enum Ranks {
    OWNER(ChatColor.DARK_RED + "" + ChatColor.BOLD +"[Owner]" + ChatColor.RESET + "" + ChatColor.DARK_RED + ""),
    ADMIN(ChatColor.RED + "" + ChatColor.BOLD + "[Admin]" + ChatColor.RESET + "" + ChatColor.RED + ""),
    MOD(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD +  "[Mod]" + ChatColor.RESET + "" + ChatColor.DARK_PURPLE + ""),
    HELPER(ChatColor.BLUE + "" + ChatColor.BOLD +"[Helper]" + ChatColor.RESET + "" + ChatColor.BLUE + ""),
    DEV(ChatColor.GOLD + "" + ChatColor.BOLD +"[Dev]" + ChatColor.RESET + "" + ChatColor.GOLD + ""),
    BUILDER(ChatColor.YELLOW + "" + ChatColor.BOLD + "[Builder]" + ChatColor.RESET + "" + ChatColor.YELLOW + ""),
    LM(ChatColor.YELLOW + "" + ChatColor.BOLD + "[Loremaster]" + ChatColor.RESET + "" + ChatColor.YELLOW + ""),
    TRIAL_DEV(ChatColor.GOLD + "" + ChatColor.BOLD +  "[Trial-Dev]" + ChatColor.RESET + "" + ChatColor.GOLD + ""),
    TRIAL_BUILDER(ChatColor.YELLOW + "" + ChatColor.BOLD + "[Trial-Builder]" + ChatColor.RESET + "" + ChatColor.YELLOW + ""),
    TRIAL_LM(ChatColor.YELLOW + "" + ChatColor.BOLD + "[Trial-LoreMaster]" + ChatColor.RESET + "" + ChatColor.YELLOW + "");
    //ChatColor.translateAlternateColorCodes('&', rank);
    private String rank;
    Ranks(String rank) {
        this.rank = rank;
    }
    public String getString() {
        return rank;
    }
    private String playerwithrank(ProxiedPlayer player) {
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
