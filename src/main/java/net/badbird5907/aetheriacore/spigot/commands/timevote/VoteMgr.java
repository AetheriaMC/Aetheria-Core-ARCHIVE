package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.manager.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VoteMgr {
    public static HashMap<Player, Boolean> vote = new HashMap<>();
    public static void countvote(Player player, boolean day){
        if(vote.containsKey(player)) {
            SoundManager.error(player, 10);
            player.sendMessage(ChatColor.RED + "You already voted!");
        }
        else{
            vote.put(player, day);
            SoundManager.ping(player, 10);
            player.sendMessage(ChatColor.GREEN + "Success!");
        }
    }

}
