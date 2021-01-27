package net.badbird5907.aetheriacore.spigot.modules.ban;

import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;

import java.util.UUID;

public class AdvancedBanHook extends BanHook{

    public AdvancedBanHook(AetheriaCore plugin) {
        super(plugin);
    }
    //TODO finish this

    @Override
    public void warn(UUID player, String reason, String sender, long time, boolean silent) {
        String name = Bukkit.getOfflinePlayer(player).getName();
        PunishmentType type = (time == -1) ? PunishmentType.WARNING : PunishmentType.TEMP_WARNING;
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Punishment.create(name, player.toString(), reason, sender, type, System.currentTimeMillis() + time, null, silent);
        });
    }
    @Override
    public void unban(UUID player, String reason){
        if(PunishmentManager.get().isBanned(player.toString()))
            PunishmentManager.get().getBan(player.toString()).delete();
        else return;
    }

    @Override
    public void ban(UUID player, String reason, long time) {

    }

    @Override
    public int bans(UUID player) {
        return PunishmentManager.get().getPunishments(player.toString(), PunishmentType.BAN, false).size();
    }

    @Override
    public int warns(UUID player) {
        int i = PunishmentManager.get().getPunishments(player.toString(), PunishmentType.WARNING, false).size();
        int b = PunishmentManager.get().getPunishments(player.toString(), PunishmentType.TEMP_WARNING, false).size();
        return b+i;
    }
}
