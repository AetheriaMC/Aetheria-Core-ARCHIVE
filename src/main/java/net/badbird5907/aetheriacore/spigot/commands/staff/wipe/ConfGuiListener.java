package net.badbird5907.aetheriacore.spigot.commands.staff.wipe;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import net.badbird5907.aetheriacore.utils.GuiHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConfGuiListener implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null)
            return;
        if(e.getClickedInventory().getHolder() instanceof GuiHolder) {
            //is wipe gui
            if(e.getCurrentItem() != null){
                if (e.getView().getTitle().contains("Confirm Wipe ")) {
                    e.setCancelled(true);
                    String target = e.getView().getTitle().replace("Confirm Wipe ", "");
                    if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("yes")) {
                        OfflinePlayer t = Bukkit.getOfflinePlayerIfCached(target);
                        String[] args = null;
                        if (e.getClickedInventory().getItem(18).getLore().get(0).contains("true")) {
                            DebugLogger.DebugLog("Force: true");
                            args = new String[]{"--force"};
                        } else {
                            args = new String[]{};
                        }
                        new Wipe().WipePlayer(e.getWhoClicked(), t, args);
                    }
                }
            }
        }
    }
}
