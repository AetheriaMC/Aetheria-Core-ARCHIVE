package net.badbird5907.aetheriacore.spigot.features.jukebox.utils;

import org.bukkit.entity.Player;

import java.util.Random;

import static org.bukkit.Particle.NOTE;

public class Particles {
	private static final Random ran = new Random();

	public static void sendParticles(Player p) {
		p.spawnParticle(NOTE, p.getEyeLocation().add(p.getLocation().getDirection().multiply(2)), 1, 0, 0.5, 0.1, 1, null);
	}
}