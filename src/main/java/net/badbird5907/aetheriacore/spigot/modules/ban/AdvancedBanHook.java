package net.badbird5907.aetheriacore.spigot.modules.ban;

import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;

import java.util.UUID;

public class AdvancedBanHook extends BanHook{
    public AdvancedBanHook(AetheriaCore plugin) {
        super(plugin);
    }

    @Override
    public void ban(UUID player, String operator, String reason, long time, boolean silent) {
        if(reason == null)
            reason = "Unspecified";
        final String r = reason;
        String name = Bukkit.getOfflinePlayer(player).getName();
        PunishmentType type = (time == -1) ? PunishmentType.BAN : PunishmentType.TEMP_BAN;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Punishment.create(name, player.toString(), r, operator, type, System.currentTimeMillis() + time, null, silent);
        });
    }

    @Override
    public void warn(UUID player, String operator, String reason, long time, boolean silent) {
        if(reason == null)
            reason = "Unspecified";
        final String r = reason;
        String name = Bukkit.getOfflinePlayer(player).getName();
        PunishmentType type = (time == -1) ? PunishmentType.WARNING : PunishmentType.TEMP_WARNING;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Punishment.create(name, player.toString(), r, operator, type, System.currentTimeMillis() + time, null, silent);
        });
    }

    @Override
    public void mute(UUID player, String operator, String reason, long time, boolean silent) {
        if(reason == null)
            reason = "Unspecified";
        final String r = reason;
        String name = Bukkit.getOfflinePlayer(player).getName();
        PunishmentType type = (time == -1) ? PunishmentType.MUTE : PunishmentType.TEMP_MUTE;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () ->{
            Punishment.create(name, player.toString(), r,operator,type,System.currentTimeMillis() + time,null,silent);
        });
    }

    @Override
    public void kick(UUID player, String operator, String reason, boolean silent) {
        if(reason == null)
            reason="Unspecified";
        final String r=reason;
        String name = Bukkit.getOfflinePlayer(player).getName();
        PunishmentType type = PunishmentType.KICK;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()->{
            //FIXME make sure -1l works
            Punishment.create(name,player.toString(), r,operator,type, -1l,null,silent);
        });
    }

    @Override
    public boolean isBanned(UUID player) {
        return PunishmentManager.get().isBanned(player.toString());
    }

    @Override
    public boolean isMuted(UUID player) {
        return PunishmentManager.get().isMuted(player.toString());
    }

    @Override
    public String[] getNotes(UUID player) {
        return null;
    }

    @Override
    public int getInfractions(UUID player) {
        return PunishmentManager.get().getPunishments(player.toString(),null,false).size();
    }

    @Override
    public int getBans(UUID player) {
        return PunishmentManager.get().getPunishments(player.toString(),PunishmentType.BAN,false).size();
    }

    @Override
    public int getWarns(UUID player) {
        return PunishmentManager.get().getPunishments(player.toString(), PunishmentType.WARNING, false).size();
    }

    @Override
    public int getMutes(UUID player) {
        return PunishmentManager.get().getPunishments(player.toString(),PunishmentType.MUTE,false).size();
    }

    @Override
    public Punishment getCurrentBan(UUID player) {
        if(PunishmentManager.get().isBanned(player.toString()))
            return PunishmentManager.get().getBan(player.toString());
        else return null;
    }

    @Override
    public Punishment getCurrentMute(UUID player) {
        if(PunishmentManager.get().isMuted(player.toString()))
            return PunishmentManager.get().getMute(player.toString());
        else return null;
    }
}
