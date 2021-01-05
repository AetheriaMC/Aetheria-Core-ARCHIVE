package net.badbird5907.aetheriacore.bungee.util;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManagerReport {
	private final HashMap<UUID, Integer> cooldowns = new HashMap<>();

	public void setCooldown(UUID player, int time) {
		if (time < 1) this.cooldowns.remove(player);
		else this.cooldowns.put(player, time);
	}

	public int getCooldown(UUID player) {
		return this.cooldowns.getOrDefault(player, 0);
	}
}
