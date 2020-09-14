package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class onarrowhit implements Listener {
    @EventHandler
    public void onEntityDamageByEntity( EntityDamageByEntityEvent event ) {
        // Is target a player, if not stop
        if ( !(event.getEntity() instanceof Player) )
            return;

        Player target = (Player) event.getEntity();

        // Is damager an arrow, if not stop
        if ( !(event.getDamager() instanceof Arrow) )
            return;

        // Is shooter a player, if not stop
        ProjectileSource src = ((Arrow) event.getDamager()).getShooter();
        if ( !(src instanceof Player) )
            return;

        Player shooter = (Player) src;
        shooter.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + "Is At" + ChatColor.BOLD + "" + ChatColor.RED + target.getHealth() + ChatColor.RESET + "" + ChatColor.GOLD + "Hp" );
        target.sendMessage(ChatColor.RED + "You were shot by " + shooter.getName() );
        shooter.playSound(shooter.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP	, 7, 1);
    }
}
