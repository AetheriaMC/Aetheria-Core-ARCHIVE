package net.badbird5907.aetheriacore.spigot.util.staffmenu.gui;

import net.badbird5907.aetheriacore.spigot.util.GuiUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class AllPlayersGui {
    public void open(){
        Inventory inv = GuiUtils.fullGuiWithBorder(null, ChatColor.GOLD + "" + ChatColor.BOLD + "All Players", GuiUtils.Border);
    }
}
