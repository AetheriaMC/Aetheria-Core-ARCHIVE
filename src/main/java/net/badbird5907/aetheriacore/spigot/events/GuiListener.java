package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.SoundManager.error;
import static net.badbird5907.aetheriacore.spigot.manager.SoundManager.high_ping;
import static org.bukkit.Material.*;

public class GuiListener implements Listener {
	@EventHandler
	public void OnInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory playerInventory = player.getInventory();
		if (e.getView().getTitle().equals("§6Shopkeeper Menu")) {
//			this gets the inventory test
			e.setCancelled(true);
//			this makes it so players cannot take the item from the inventory with the display name "test"
			String debug = String.valueOf(e.getSlot());
			DebugLog(debug);
			switch (e.getSlot()) {
				//OAK LOGS
				case 10:
					ItemStack emerald64 = new ItemStack(EMERALD, 64);
					ItemStack oaklog64 = new ItemStack(OAK_LOG, 64);
					for (int i = 0; i < 65; i++) {
						int price = 5;
						ItemStack target = new ItemStack(EMERALD, i);
						if (playerInventory.contains(target)) {
							if (i > price) {
								playerInventory.removeItem(target);
								ItemStack refund = new ItemStack(EMERALD, i - price);
								playerInventory.addItem(refund);
								playerInventory.addItem(oaklog64);
								high_ping(player, 10);
								return;
							}
							if (i == price) {
								playerInventory.removeItem(target);
								playerInventory.addItem(oaklog64);
								high_ping(player, 10);
							} else error(player, 10);
							return;
						}
					}
				case 11:
					for (int i = 0; i < 65; i++) {
						int price = 2;
						if (method(player, playerInventory, i, price, COOKED_CHICKEN)) return;
					}
				case 12:
					for (int i = 0; i < 65; i++) {
						int price = 3;
						if (method(player, playerInventory, i, price, COOKED_PORKCHOP)) return;
					}
				case 13:
					for (int i = 0; i < 65; i++) {
						int price = 3;
						if (method(player, playerInventory, i, price, COOKED_MUTTON)) return;
					}
				case 14: //cod
					for (int i = 0; i < 65; i++) {
						int price = 3;
						if (method(player, playerInventory, i, price, COOKED_COD)) return;
					}
				case 15:
					//SALMON
					for (int i = 0; i < 65; i++) {
						int price = 3;
						if (method(player, playerInventory, i, price, COOKED_SALMON)) return;
					}
				case 16:
					for (int i = 0; i < 65; i++) {
						int price = 4;
						if (method(player, playerInventory, i, price, COOKED_RABBIT)) return;
					}
				case 19:
					for (int i = 0; i < 65; i++) {
						int price = 3;
						if (method(player, playerInventory, i, price, COOKED_BEEF)) return;
					}
					//Add whatever here you want to execute when player clicks item.
			}
		}
	}

	private boolean method(Player player, Inventory playerInventory, int i, int price, Material cookedChicken) {
		ItemStack buyitem = new ItemStack(cookedChicken, 1);
		ItemStack target = new ItemStack(EMERALD, i);
		if (playerInventory.contains(target)) {
			if (i > price) {
				playerInventory.removeItem(target);
				ItemStack refund = new ItemStack(EMERALD, i - price);
				playerInventory.addItem(refund);
				playerInventory.addItem(buyitem);
				high_ping(player, 10);
				return true;
			}
			if (i == price) {
				playerInventory.removeItem(target);
				playerInventory.addItem(buyitem);
				high_ping(player, 10);
			} else error(player, 10);
			return true;
		}
		return false;
	}
}