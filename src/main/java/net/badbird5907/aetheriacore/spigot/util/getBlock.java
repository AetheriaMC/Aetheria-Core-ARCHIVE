package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import static org.bukkit.Material.AIR;

public class getBlock {
	public static Location getTargetBlock(Player player, int range) {
		BlockIterator iter = new BlockIterator(player, range);
		Block lastBlock = iter.next();
		while (iter.hasNext()) {
			lastBlock = iter.next();
			if (lastBlock.getType() == AIR) continue;
			break;
		}
		return lastBlock.getLocation();
	}
}