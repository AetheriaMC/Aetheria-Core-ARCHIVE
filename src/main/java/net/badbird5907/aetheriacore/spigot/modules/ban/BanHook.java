package net.badbird5907.aetheriacore.spigot.modules.ban;

import me.leoko.advancedban.utils.Punishment;
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
    public abstract void ban(UUID player, String operator, String reason, long time, boolean silent);
    public abstract void warn(UUID player, String operator, String reason, long time, boolean silent);
    public abstract void mute(UUID player, String operator, String reason, long time, boolean silent);
    public abstract void kick(UUID player, String operator, String reason, boolean silent);
    public abstract boolean isBanned(UUID player);
    public abstract boolean isMuted(UUID player);
    public abstract String[] getNotes(UUID player);
    public abstract int getInfractions(UUID player);
    public abstract int getBans(UUID player);
    public abstract int getWarns(UUID player);
    public abstract int getMutes(UUID player);
    public abstract Punishment getCurrentBan(UUID player);
    public abstract Punishment getCurrentMute(UUID player);
}
