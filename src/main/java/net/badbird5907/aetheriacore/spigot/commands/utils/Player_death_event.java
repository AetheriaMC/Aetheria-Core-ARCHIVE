package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static java.lang.Math.random;
import static net.badbird5907.aetheriacore.spigot.commands.utils.PlayMusic.play;

public class Player_death_event implements Listener {
	@EventHandler
	public static void DeathEvent(PlayerDeathEvent event) {
		Player p = event.getEntity();
		boolean temp = random() <= 0.5;
		if (temp) {
			String song = "Astromania1.nbs";
			String play = play(p.getName(), song);
			p.sendMessage(play);
		} else {
			String song = "Astromania2.nbs";
			String play = play(p.getName(), song);
			p.sendMessage(play);
		}
	}
}