package net.badbird5907.aetheriacore.spigot.util.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {
    itemmenuinv a;

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        if (e.getInventory() != a.inv1) return;
        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        final Player p = (Player) e.getWhoClicked();
        // Using slots click is a best option for your inventory click's
        if(clickedItem.getType() == Material.NETHERITE_SWORD) {
            p.sendMessage(ChatColor.GREEN + "Weapons");
        }
        if(clickedItem.getType() == Material.NETHERITE_CHESTPLATE) {
            p.sendMessage(ChatColor.GREEN + "Armour");
        }
        if(clickedItem.getType() == Material.COBBLESTONE) {
            p.sendMessage(ChatColor.GREEN + "Blocks");
        }
        if(clickedItem.getType() == Material.PIG_SPAWN_EGG) {
            p.sendMessage(ChatColor.GREEN + "Animals");
        }
        if(clickedItem.getType() == Material.NAME_TAG) {
            p.sendMessage(ChatColor.GREEN + "Search");
        }

    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == a.inv1) {
            e.setCancelled(true);
        }
    }
}