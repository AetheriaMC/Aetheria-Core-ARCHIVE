package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.stream.IntStream;

public class GuiUtils {
    public static Inventory fullGuiWithBorder(InventoryHolder holder, String name, ItemStack border){
        Inventory gui = Bukkit.createInventory(null, 54, name);
        IntStream.range(0, 9).forEach(i -> gui.setItem(i, border));
        IntStream.range(45, 53).forEach(i -> gui.setItem(i, border));
        gui.setItem(18, border);
        gui.setItem(27, border);
        gui.setItem(36, border);
        gui.setItem(17, border);
        gui.setItem(26, border);
        gui.setItem(35, border);
        gui.setItem(45, border);
        gui.setItem(9, border);
        //gui.setItem(54, border);
        return gui;
    }
}
