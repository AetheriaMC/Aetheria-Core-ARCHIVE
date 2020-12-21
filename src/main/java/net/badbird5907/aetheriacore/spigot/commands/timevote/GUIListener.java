package net.badbird5907.aetheriacore.spigot.commands.timevote;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(event.getClickedInventory().getHolder().equals(null)){
            if(event.getView().getTitle().equals(TimeVoteGUI.mainname)){
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())){
                    case "Close":
                        player.closeInventory();
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
}
