package net.badbird5907.aetheriacore.spigot.features.timevote;

import net.badbird5907.aetheriacore.utils.GuiHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TimeVoteGUIListener implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(event.getClickedInventory() == null)
            return;
        if(event.getView().getTitle().equals(TimeVoteGUI.mainname)&& event.getInventory().getHolder() instanceof GuiHolder){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if(event.getCurrentItem() != null) {
                return;
            }
            else switch (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())){
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
