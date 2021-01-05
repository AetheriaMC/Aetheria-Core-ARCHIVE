package net.badbird5907.aetheriacore.spigot.commands.staff.punish;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.bukkit.ChatColor.translateAlternateColorCodes;
import static org.bukkit.Material.IRON_AXE;
import static org.bukkit.Material.WRITABLE_BOOK;

public class PunishGUI implements Listener {
	public Inventory gui;

	@EventHandler
	public static void ClickEvent(InventoryClickEvent event) {
		if (event.getClickedInventory() instanceof PunishGUI) event.setCancelled(true);
	}

	public void PunishGUI(Player sender, OfflinePlayer target) {
		//Create Itemstacks
		ItemStack punishButton = new ItemStack(IRON_AXE);
		ItemStack History = new ItemStack(WRITABLE_BOOK);
		//Create Inventory
		//TODO look into 27 size
		gui = Bukkit.createInventory(null, 27, "Punish " + target.getName());
		//Create Meta
		ItemMeta punishButtonMeta = punishButton.getItemMeta();
		ItemMeta historyMeta = History.getItemMeta();
		//History Meta
		requireNonNull(historyMeta).setDisplayName(translateAlternateColorCodes('&', "Player History"));
		List<String> historyLore = new ArrayList<>();
		historyLore.add(translateAlternateColorCodes('&', "&cNot in use ATM."));
		historyMeta.setLore(historyLore);
		History.setItemMeta(historyMeta);
		//Punish Meta
		requireNonNull(punishButtonMeta).setDisplayName(translateAlternateColorCodes('&', "&6&lPunish " + target.getName()));
		List<String> punishLore = new ArrayList<>();
		punishLore.add(translateAlternateColorCodes('&', "&8UUID: " + target.getUniqueId()));
		punishButton.setItemMeta(punishButtonMeta);
		gui.setItem(11, punishButton);
		gui.setItem(15, History);
		sender.openInventory(gui);
	}
}