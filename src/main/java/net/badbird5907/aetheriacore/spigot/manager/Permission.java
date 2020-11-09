package net.badbird5907.aetheriacore.spigot.manager;

public enum Permission {
    TELEPORT_PARTICLES("cosmetic.teleportparticles"),
    GMC("gamemode.creative"),
    GMS("gamemode.survival"),
    GMSP("gaememode.spectator"),
    GMA("gamemode.adventure"),
    GOD("god"),
    HEAL("heal"),
    FLY("fly"),

    BYPASS_ITEM_BLACKLIST("")
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
