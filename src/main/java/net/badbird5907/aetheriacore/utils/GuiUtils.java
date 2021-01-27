package net.badbird5907.aetheriacore.utils;

import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import net.badbird5907.aetheriacore.utils.GuiHolder;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static org.bukkit.Bukkit.createInventory;

public class GuiUtils {
	public static Inventory fullGuiWithBorder(InventoryHolder holder, String name, ItemStack border) {
		Inventory gui;
		if(holder == null)
			gui = createInventory(new GuiHolder(), 54, name);
		else
			gui = createInventory(holder, 54, name);
		asList(range(0, 9), range(45, 53), of(18, 27, 36, 17, 26, 35, 45, 9)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		gui.setItem(44, border);
		gui.setItem(53, border);
		return gui;
	}
	public static Inventory halfGuiWithBorder(InventoryHolder holder, String name, ItemStack border){
		Inventory gui;
		if(holder == null)
			gui = createInventory(new GuiHolder(), 27, name);
		else
			gui = createInventory(holder, 26, name);
		asList(range(0, 9), range(18, 27), of(9,17)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		return gui;
	}
	public static Inventory fullGuiWithBorder( String name, ItemStack border) {
		Inventory gui = createInventory(new GuiHolder(), 54, name);
		asList(range(0, 9), range(45, 53), of(18, 27, 36, 17, 26, 35, 45, 9)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		gui.setItem(44, border);
		gui.setItem(53, border);
		return gui;
	}
	public static Inventory halfGuiWithBorder( String name, ItemStack border){
		Inventory gui = createInventory(new GuiHolder(), 27, name);
		asList(range(0, 9), range(18, 27), of(9,17)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		return gui;
	}

	public static Inventory fullGuiWithBorder(InventoryHolder holder, String name, ItemStack border, boolean close) {
		Inventory gui;
		if(holder == null)
			gui = createInventory(new GuiHolder(), 54, name);
		else
			gui = createInventory(holder, 54, name);
		if (close)		gui.setItem(49, Close);
		asList(range(0, 9), range(45, 53), of(18, 27, 36, 17, 26, 35, 45, 9)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		gui.setItem(44, border);
		gui.setItem(53, border);
		return gui;
	}
	public static Inventory halfGuiWithBorder(InventoryHolder holder, String name, ItemStack border, boolean close){
		Inventory gui;
		if(holder == null)
			gui = createInventory(new GuiHolder(), 27, name);
		else
			gui = createInventory(holder, 54, name);
		asList(range(0, 9), range(18, 27), of(9,17)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		if (close)		gui.setItem(22, Close);
		return gui;
	}
	public static Inventory fullGuiWithBorder( String name, ItemStack border, boolean close) {
		Inventory gui = createInventory(new GuiHolder(), 54, name);
		asList(range(0, 9), range(45, 53), of(18, 27, 36, 17, 26, 35, 45, 9)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		if (close)		gui.setItem(49, Close);
		gui.setItem(44, border);
		gui.setItem(53, border);
		return gui;
	}
	public static Inventory halfGuiWithBorder( String name, ItemStack border, boolean close){
		Inventory gui = createInventory(new GuiHolder(), 27, name);
		asList(range(0, 9), range(18, 27), of(9,17)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		if (close)		gui.setItem(22, Close);
		return gui;
	}
	public static ItemStack Close = new ItemStackBuilder(XMaterial.BARRIER.parseMaterial()).amount(1).name(ChatColor.RED + "Close").build();
	public static ItemStack Border = new ItemStackBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()).amount(1).name(ChatColor.RED + "").build();
}
