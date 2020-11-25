package net.badbird5907.aetheriacore.spigot.manager;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {
    public static void ping(Player player, int volume){
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, volume, 1);
    }
    public static void high_ping(Player player, int volume){
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, volume, 2);
    }
    public static void click(Player player, int volume){
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, volume, 1);
    }
    public static void error(Player player, int volume){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, volume, 1);
    }
}
