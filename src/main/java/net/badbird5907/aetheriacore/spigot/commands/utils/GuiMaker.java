package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Material.*;
import static org.bukkit.inventory.ItemFlag.HIDE_POTION_EFFECTS;

public class GuiMaker implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("shopkeeper")) {
				Inventory gui = getServer().createInventory(p, 54, "§6Shopkeeper Menu");
				//create filler1
				ItemStack filler1 = new ItemStack(BLUE_STAINED_GLASS_PANE);
				ItemMeta metafiller1 = filler1.getItemMeta();
				requireNonNull(metafiller1).addItemFlags(HIDE_POTION_EFFECTS);
				filler1.setItemMeta(metafiller1);
				ArrayList<String> lorefiller1 = new ArrayList<>();
				metafiller1.setLore(lorefiller1);
				metafiller1.setDisplayName(" ");
				filler1.setItemMeta(metafiller1);
				//create filler2
				ItemStack filler2 = new ItemStack(LIME_STAINED_GLASS_PANE);
				ItemMeta metafiller2 = filler2.getItemMeta();
				requireNonNull(metafiller2).addItemFlags(HIDE_POTION_EFFECTS);
				filler2.setItemMeta(metafiller2);
				ArrayList<String> lorefiller2 = new ArrayList<>();
				metafiller2.setLore(lorefiller1);
				metafiller2.setDisplayName(" ");
				filler2.setItemMeta(metafiller2);
				//make oak logs
				range(0, 1).mapToObj(i -> new ItemStack(OAK_LOG, 64)).forEach(oaklog64 -> {
					ItemMeta metaoaklog64 = oaklog64.getItemMeta();
					requireNonNull(metaoaklog64).addItemFlags(HIDE_POTION_EFFECTS);
					oaklog64.setItemMeta(metaoaklog64);
					ArrayList<String> loreoaklog64 = new ArrayList<>(asList(" ", "§fCost: §25 Emeralds"));
					loreoaklog64.addAll(asList(" ", "§aClick to purchase!"));
					metaoaklog64.setDisplayName("§fOak Wood §7(x64)");
					metaoaklog64.setLore(loreoaklog64);
					oaklog64.setItemMeta(metaoaklog64);
					gui.setItem(10, oaklog64);
				});//2 EMS
				//make chicken slot 11
				range(0, 1).mapToObj(i -> new ItemStack(COOKED_CHICKEN, 1)).forEach(newitem -> {
					ItemMeta itemmeta = newitem.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					newitem.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §22 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Chicken §7(x1)");
					itemmeta.setLore(itemlore);
					newitem.setItemMeta(itemmeta);
					gui.setItem(11, newitem);
				});//3 EMS
				//make porkchop slot 12
				range(0, 1).mapToObj(i -> new ItemStack(COOKED_PORKCHOP, 1)).forEach(newitem -> {
					ItemMeta itemmeta = newitem.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					newitem.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §23 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Porkchop §7(x1)");
					itemmeta.setLore(itemlore);
					newitem.setItemMeta(itemmeta);
					gui.setItem(12, newitem);
				});//3 EMS
				//make mutton slot 13
				range(0, 1).mapToObj(i1 -> new ItemStack(COOKED_MUTTON, 1)).forEach(itemStack -> {
					ItemMeta itemmeta = itemStack.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					itemStack.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §23 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Mutton §7(x1)");
					itemmeta.setLore(itemlore);
					itemStack.setItemMeta(itemmeta);
					gui.setItem(13, itemStack);
				});//3 EMS
				//make cod slot 14
				range(0, 1).mapToObj(i -> new ItemStack(COOKED_COD, 1)).forEach(newitem -> {
					ItemMeta itemmeta = newitem.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					newitem.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §23 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Cod §7(x1)");
					itemmeta.setLore(itemlore);
					newitem.setItemMeta(itemmeta);
					gui.setItem(14, newitem);
				});//3 EMS
				//make salmon slot 15
				range(0, 1).mapToObj(i -> new ItemStack(COOKED_SALMON, 1)).forEach(newitem -> {
					ItemMeta itemmeta = newitem.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					newitem.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §23 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Salmon §7(x1)");
					itemmeta.setLore(itemlore);
					newitem.setItemMeta(itemmeta);
					gui.setItem(15, newitem);
				});//4 EMS
				//make rabbit slot 16
				range(0, 1).mapToObj(i -> new ItemStack(COOKED_RABBIT, 1)).forEach(newitem -> {
					ItemMeta itemmeta = newitem.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					newitem.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §24 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Porkchop §7(x1)");
					itemmeta.setLore(itemlore);
					newitem.setItemMeta(itemmeta);
					gui.setItem(16, newitem);
				});//5 EMS
				//make steak slot 17
				range(0, 1).mapToObj(i -> new ItemStack(COOKED_BEEF, 1)).forEach(newitem -> {
					ItemMeta itemmeta = newitem.getItemMeta();
					requireNonNull(itemmeta).addItemFlags(HIDE_POTION_EFFECTS);
					newitem.setItemMeta(itemmeta);
					ArrayList<String> itemlore = new ArrayList<>(asList(" ", "§fCost: §25 Emeralds"));
					itemlore.addAll(asList(" ", "§aClick to purchase!"));
					itemmeta.setDisplayName("§fCooked Porkchop §7(x1)");
					itemmeta.setLore(itemlore);
					newitem.setItemMeta(itemmeta);
					gui.setItem(19, newitem);
				});
				//make outline - blue
				of(0, 2, 4, 6, 8, 26, 44, 52, 50, 48, 46, 36, 18).forEach(i -> gui.setItem(i, filler1));
				//make outline - green
				of(1, 3, 5, 7, 17, 35, 53, 51, 49, 47, 45, 27, 9).forEach(i -> gui.setItem(i, filler2));
				//Sets item in slot, in this case slot 11
				p.openInventory(gui);
				return true;
//Opens the Inventory
			}
			return true;
		}
		return false;
	}
}