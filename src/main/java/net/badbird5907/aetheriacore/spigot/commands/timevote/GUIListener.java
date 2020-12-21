package net.badbird5907.aetheriacore.spigot.commands.timevote;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(event.getView().getTitle().equals(TimeVoteGUI.mainname)){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if(event.getCurrentItem().equals(Material.AIR) || event.getCurrentItem().equals(null))
                return;
            else switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())){
                case "Close":
                    player.closeInventory();
                    return;
                case "Vote for day":
                    VoteMgr.countvote(player, true);
                    return;
                case "Vote for night":
                    VoteMgr.countvote(player, false);
                    return;
            }
        }
    }
}
