package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Gamemode {
    public static void setGameMode(Player player, GameMode gm){
        player.sendMessage(ChatColor.GREEN + "Set your game mode to:" + ChatColor.AQUA + " " + gm.name());
        player.setGameMode(gm);
    }
}
