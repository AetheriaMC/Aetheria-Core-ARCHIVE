package net.badbird5907.aetheriacore.spigot.features.timevote;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class TimeMgr {
    public static int checks = 0;
    public static void checkVotes(){
        int voteyes = VoteMgr.voteyes.size();
        int voteno = VoteMgr.voteno.size();
        int halfonlineplayers = (int)Math.ceil((double) Bukkit.getOnlinePlayers().size()/2);
        checks++;
        if (voteno + voteyes < halfonlineplayers || voteno + voteyes == 0) {
            return;
        }
        else{
            checks = 0;
            if (voteno<voteyes){
                for (World w:Bukkit.getWorlds()){
                    w.setTime(6000L);
                }
            }
            else{
                for (World w:Bukkit.getWorlds()){
                    w.setTime(13000L);
                }
            }
        }
    }
    public static void check(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(AetheriaCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                checkVotes();
            }
        }, 0L, 20L); //1 second
    }
    public static void reset(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(AetheriaCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(checks > 1){
                    VoteMgr.voteno.clear();
                    VoteMgr.voteyes.clear();
                    VoteMgr.vote.clear();
                    checks = 0;
                }
            }
        }, 0L, 24000L); //20 mins
    }
    public static void start(){
        checkVotes();
        check();
        reset();
    }
}
