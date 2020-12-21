package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class VoteMgr {
    public static HashMap<Player, Boolean> vote = new HashMap<>();
    public static ArrayList<Player> voteyes = new ArrayList<>();
    public static ArrayList<Player> voteno = new ArrayList<>();
    public static HashMap<Player, Long> cooldown = new HashMap<>();
    //public static World first;
    static int cooldownTimeSeconds = 300;
    public static void countvote(Player player, boolean day){
        if(canVote(player)){
            cooldown.put(player, System.currentTimeMillis());
            if(vote.containsKey(player)) {
                SoundManager.error(player, 10);
                player.sendMessage(ChatColor.RED + "You already voted!");
            }
            else{
                vote.put(player, day);
                if(day) voteyes.add(player);
                else voteno.add(player);
                SoundManager.ping(player, 10);
                player.sendMessage(ChatColor.GREEN + "Success!");
            }
        }
        else{
            long seconds = TimeUnit.MILLISECONDS.toSeconds(cooldown.get(player) - System.currentTimeMillis());
            long secondsleft = (cooldown.get(player) - System.currentTimeMillis() * 1000);
            player.sendMessage(ChatColor.RED + "Voting is on cooldown! You may vote only every 5 minutes.");
        }
    }
    public static boolean canVote(Player player){
        if (cooldown.containsKey(player)) {
            DebugLogger.DebugLog(String.valueOf(cooldown.get(player) - System.currentTimeMillis() * 1000));
            if(cooldown.get(player) - System.currentTimeMillis() * 1000 < cooldownTimeSeconds)
                return false;
            else {
                cooldown.remove(player);
                return true;
            }
        }
        return true;
    }
}
