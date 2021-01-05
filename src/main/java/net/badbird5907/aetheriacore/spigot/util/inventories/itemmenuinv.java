package net.badbird5907.aetheriacore.spigot.util.inventories;

import net.badbird5907.aetheriacore.spigot.util.GuiUtils;
import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.is16;
import static net.badbird5907.aetheriacore.spigot.util.XMaterial.*;
import static org.bukkit.Material.NAME_TAG;
import static org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES;

public class itemmenuinv {
	private static final ItemStack border = new ItemStackBuilder(BLACK_STAINED_GLASS_PANE.parseMaterial()).amount(1).name("").build();
	public static final Inventory inv1 = GuiUtils.fullGuiWithBorder(null, "Item Menu", border);

	public void open(Player p) {
		ItemStack ref1 = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta metaref1 = ref1.getItemMeta();
		ArrayList<String> lore1 = new ArrayList<>();
		lore1.add("");
		requireNonNull(metaref1).setDisplayName("§r§aCategory:§b Weapons");
		ref1.getItemMeta().addItemFlags(HIDE_ATTRIBUTES);
		ref1.setItemMeta(metaref1);
		inv1.setItem(10, ref1);
		if (is16()) {
			ItemStack ref2 = new ItemStack(requireNonNull(NETHERITE_CHESTPLATE.parseMaterial()));
			ItemMeta metaref2 = ref2.getItemMeta();
			ArrayList<String> lore2 = new ArrayList<>();
			lore2.add("");
			requireNonNull(metaref2).setDisplayName("§r§aCategory:§b Armour");
			ref2.getItemMeta().addItemFlags(HIDE_ATTRIBUTES);
			ref2.setItemMeta(metaref2);
			inv1.setItem(12, ref2);
		} else {
			ItemStack ref2 = new ItemStack(requireNonNull(DIAMOND_CHESTPLATE.parseMaterial()));
			ItemMeta metaref2 = ref2.getItemMeta();
			ArrayList<String> lore2 = new ArrayList<>();
			lore2.add("");
			requireNonNull(metaref2).setDisplayName("§r§aCategory:§b Armour");
			ref2.getItemMeta().addItemFlags(HIDE_ATTRIBUTES);
			ref2.setItemMeta(metaref2);
			inv1.setItem(12, ref2);
		}
		ItemStack ref3 = new ItemStack(requireNonNull(COBBLESTONE.parseMaterial()));
		ItemMeta metaref3 = ref3.getItemMeta();
		ArrayList<String> lore3 = new ArrayList<>();
		lore3.add("");
		requireNonNull(metaref3).setDisplayName("§r§aCategory:§b Blocks");
		ref3.getItemMeta().addItemFlags(HIDE_ATTRIBUTES);
		ref3.setItemMeta(metaref3);
		inv1.setItem(14, ref3);
		ItemStack ref4 = new ItemStack(requireNonNull(PIG_SPAWN_EGG.parseMaterial()));
		ItemMeta metaref4 = ref4.getItemMeta();
		ArrayList<String> lore4 = new ArrayList<>();
		lore4.add("");
		requireNonNull(metaref4).setDisplayName("§r§aCategory:§b Spawn Eggs");
		ref4.getItemMeta().addItemFlags(HIDE_ATTRIBUTES);
		ref4.setItemMeta(metaref4);
		inv1.setItem(16, ref4);
		ItemStack ref6 = new ItemStack(NAME_TAG);
		ItemMeta metaref6 = ref6.getItemMeta();
		ArrayList<String> lore6 = new ArrayList<>();
		lore6.add("");
		requireNonNull(metaref6).setDisplayName("§r§b§lSearch");
		ref6.getItemMeta().addItemFlags(HIDE_ATTRIBUTES);
		ref6.setItemMeta(metaref6);
		inv1.setItem(40, ref6);
		p.openInventory(inv1);
	}
}