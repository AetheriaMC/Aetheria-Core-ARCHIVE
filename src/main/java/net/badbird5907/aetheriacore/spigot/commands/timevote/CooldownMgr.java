package net.badbird5907.aetheriacore.spigot.commands.timevote;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownMgr {
    public static HashMap<UUID, Long> cooldownTimes = new HashMap<UUID, Long>();
    private static int cooldown = 300;
    public static boolean canVote(Player player){
        if (cooldownTimes.containsKey(player.getUniqueId()) && (cooldownTimes.get(player.getUniqueId()) - cooldown*1000 > cooldown))
            return false;
        else
            return true;
    }
}
