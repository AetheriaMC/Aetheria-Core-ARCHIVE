package net.badbird5907.aetheriacore.spigot.manager;

public enum Permission {
    //super staff role (all staff should have)
    STAFF("staff"),
    //staff roles
    STAFF_TRIAL_BUILDER("staff.trial.builder"),
    STAFF_BUILDER("staff.builder"),
    STAFF_TRIAL_LM("staff.trial.lm"),
    STAFF_LM("staff.lm"),
    STAFF_TRIAL_DEV("staff.trial.dev"),
    STAFF_DEV("staff.dev"),
    STAFF_HELPER("staff.helper"),
    STAFF_MOD("staff.mod"),
    STAFF_ADMIN("staff.admin"),
    STAFF_OWNER("staff.owner"),

    //Other staff roles
    STAFF_LEAD_BUILDER("staff.lead.builder"),
    STAFF_LEAD_DEV("staff.lead.dev"),
    STAFF_LEAD_LM("staff.lead.lm"),


    TELEPORT_PARTICLES("cosmetic.teleportparticles"),
    GMC("gamemode.creative"),
    GMS("gamemode.survival"),
    GMSP("gaememode.spectator"),
    GMA("gamemode.adventure"),
    GOD("god"),
    HEAL("heal"),
    FLY("fly"),
    LOCKDOWN("lockdown"),
    BROADCAST("broadcast"),
    MUTE_CHAT("mutechat"),
    KICK_ALL("kickall"),
    LOOP("loop"),
    MUSIC_BROADCAST("music.broadcast"),
    PING_WARS("pingwars"),
    PING("ping"),

    BYPASS_ITEM_BLACKLIST("bypass.itemblacklist")
    ;

    public final String node;

    Permission(final String node) {
        this.node = "aetheriacore." + node;
    }

    /*
    public boolean has(CommandSender sender, boolean informSenderIfNot) {
        return Permission.has(sender, this.node, informSenderIfNot);
    }

    public boolean has(CommandSender sender) {
        return has(sender, false);
    }
    public boolean has(CommandSender me, String perm1, boolean informSenderIfNot) {
        if (has(me, perm1))
            return true;
        else if (informSenderIfNot && me != null)
            me.sendMessage(permissionManager.PermissionMessage);
        return false;
    }
     */

}
