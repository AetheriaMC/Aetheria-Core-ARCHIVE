package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static org.bukkit.Bukkit.createInventory;

public class GuiUtils {
	public static Inventory fullGuiWithBorder(InventoryHolder holder, String name, ItemStack border) {
		Inventory gui = createInventory(null, 54, name);
		asList(range(0, 9), range(45, 53), of(18, 27, 36, 17, 26, 35, 45, 9)).forEach(intStream -> intStream.forEach(i -> gui.setItem(i, border)));
		return gui;
	}
}
