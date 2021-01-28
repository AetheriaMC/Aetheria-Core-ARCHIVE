package net.badbird5907.aetheriacore.spigot.modules.ban;

import litebans.api.Database;
import me.leoko.advancedban.utils.Punishment;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class LiteBansHook extends BanHook{
    public LiteBansHook(AetheriaCore plugin) {
        super(plugin);
    }

    @Override
    public void ban(UUID player, String operator, String reason, long seconds, boolean silent) {
        Player sender = Bukkit.getPlayer(operator);
        OfflinePlayer target = Bukkit.getOfflinePlayer(player);
        StringBuilder command = new StringBuilder("/litebans:ban ");
        if(reason == null)
            reason = "Unspecified";
        if(silent)
            command.append("-s ");
        command.append(target.getName());
        if(seconds == -1)
            command.append(reason);
        else{
            command.append(seconds + "s ");
            command.append(reason);
        }
        //litebans:ban -s Badbird5907 lol | perm
        //litebans:ban -s Badbird5907 1000s lol | temp
        sender.chat(command.toString());
    }

    @Override
    public void warn(UUID player, String operator, String reason, long time, boolean silent) {
        Player sender = Bukkit.getPlayer(operator);
        OfflinePlayer target = Bukkit.getOfflinePlayer(player);
        StringBuilder command = new StringBuilder("/litebans:warn ");
        if(reason == null)
            reason = "Unspecified";
        if(silent)
            command.append("-s ");
        command.append(target.getName());
        command.append(reason);
        //litebans:warn -s Badbird5907 lol
        sender.chat(command.toString());
    }

    @Override
    public void mute(UUID player, String operator, String reason, long time, boolean silent) {
        Player sender = Bukkit.getPlayer(operator);
        OfflinePlayer target = Bukkit.getOfflinePlayer(player);
        StringBuilder command = new StringBuilder("/litebans:mute ");
        if(reason == null)
            reason = "Unspecified";
        if(silent)
            command.append("-s ");
        command.append(target.getName());
        command.append(reason);
        //litebans:warn -s Badbird5907 lol
        sender.chat(command.toString());
    }

    @Override
    public void kick(UUID player, String operator, String reason, boolean silent) {

    }

    @Override
    public boolean isBanned(UUID player) {
        AtomicBoolean banned = new AtomicBoolean(false);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()-> {
            banned.set(Database.get().isPlayerBanned(player, null));
        });
        return banned.get();
    }

    @Override
    public boolean isMuted(UUID player) {
        AtomicBoolean banned = new AtomicBoolean(false);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()-> {
            banned.set(Database.get().isPlayerMuted(player, null));
        });
        return banned.get();
    }

    @Override
    public String[] getNotes(UUID player) {
        return new String[0];
    }

    @Override
    public int getInfractions(UUID player) {
        return 0;
    }

    @Override
    public int getBans(UUID player) {
        return 0;
    }

    @Override
    public int getWarns(UUID player) {
        return 0;
    }

    @Override
    public int getMutes(UUID player) {
        return 0;
    }

    @Override
    public Punishment getCurrentBan(UUID player) {
        return null;
    }

    @Override
    public Punishment getCurrentMute(UUID player) {
        return null;
    }
}
