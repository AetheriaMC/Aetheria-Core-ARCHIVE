package net.badbird5907.aetheriacore.spigot.commands.timevote;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.commands.timevote.TimeVoteGUI.mainname;
import static net.badbird5907.aetheriacore.spigot.commands.timevote.VoteMgr.countvote;
import static org.bukkit.ChatColor.stripColor;
import static org.bukkit.Material.AIR;

public class GUIListener implements Listener {
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equals(mainname)) {
			event.setCancelled(true);
			Player player = (Player) event.getWhoClicked();
			if (requireNonNull(event.getCurrentItem()).equals(AIR) || event.getCurrentItem() == null) {
			} else switch (stripColor(requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName())) {
				case "Close":
					player.closeInventory();
					return;
				case "Vote for day":
					countvote(player, true);
					return;
				case "Vote for night":
					countvote(player, false);
			}
		}
	}
}