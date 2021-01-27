package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import net.badbird5907.aetheriacore.utils.GuiHolder;
import net.badbird5907.aetheriacore.utils.GuiUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CloseButtonListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null)
            return;
        if(e.getInventory().getHolder() instanceof GuiHolder){
            if(e.getCurrentItem() != null){
                if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("close")) {
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                }
                if(e.getCurrentItem().equals(GuiUtils.Border))
                    e.setCancelled(true);
            }
        }
    }
}
