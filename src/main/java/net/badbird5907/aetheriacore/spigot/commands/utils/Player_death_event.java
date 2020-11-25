package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Player_death_event implements Listener {
    @EventHandler
    public static void DeathEvent(PlayerDeathEvent event){
        Player p = event.getEntity();
        int temp = (Math.random() <= 0.5) ? 1 : 2;
        if(temp == 1) {
            String song = "Astromania1.nbs";
            String play = PlayMusic.play(p.getName(), song);
            p.sendMessage(play);
        }
        else {
            String song = "Astromania2.nbs";
            String play = PlayMusic.play(p.getName(), song);
            p.sendMessage(play);
        }
    }
}
