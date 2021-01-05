package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import static org.bukkit.ChatColor.*;
import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

public class onarrowhit implements Listener {
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		// Is target a player, if not stop
		if (!(event.getEntity() instanceof Player)) return;
		Player target = (Player) event.getEntity();
		// Is damager an arrow, if not stop
		if (!(event.getDamager() instanceof Arrow)) return;
		// Is shooter a player, if not stop
		ProjectileSource src = ((Arrow) event.getDamager()).getShooter();
		if (!(src instanceof Player)) return;
		Player shooter = (Player) src;
		shooter.sendMessage(RED + target.getName() + GOLD + " Is At " + BOLD + "" + GREEN + target.getHealth() + RESET + " " + GOLD + "Hp");
		target.sendMessage(RED + "You were shot by " + shooter.getName());
		shooter.playSound(shooter.getLocation(), ENTITY_EXPERIENCE_ORB_PICKUP, 7, 1);
	}
}