package net.badbird5907.aetheriacore.spigot.modules.ban;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.modules.AbstractModule;

import java.util.UUID;

public abstract class BanHook extends AbstractModule {
    public BanHook(AetheriaCore plugin) {
        super(plugin);
    }
    @Override
    public void enable() { }
    @Override
    public void disable() { }
    public void ban(UUID player) {
        ban(player, null);
    }
    public void ban(UUID player, String reason) {
        ban(player, reason, -1);
    }
    public void warn(UUID player, String reason, String sender, long time, boolean silent){internalwarn(player, reason, sender, time, silent);}

//    public abstract void internalwarn(UUID player, String reason, String sender, long time, boolean silent);

    public abstract void unban(UUID player, String reason);
    public abstract void ban(UUID player, String reason, long time);
    public abstract int bans(UUID player);
    public abstract int warns(UUID player);
}
