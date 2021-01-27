package net.badbird5907.aetheriacore.spigot.features.punish;

import net.badbird5907.aetheriacore.spigot.features.punish.gui.ChooserGui;
import net.badbird5907.aetheriacore.spigot.features.punish.listener.ChooserGuiListener;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.SoundManager;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import net.badbird5907.aetheriacore.utils.GuiHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class PunishGuiListener implements Listener {
    @EventHandler
    public void clickListener(InventoryClickEvent event){
        if(event.getClickedInventory() == null)
            return;
        if(event.getInventory().getHolder() instanceof GuiHolder){
            if(event.getCurrentItem() != null){
                String title = ChatColor.stripColor(event.getView().getTitle()).toLowerCase();
                //punish player gui
                if(title.startsWith("punish") && event.getInventory().getSize() != 54){
                    event.setCancelled(true);
                    String item = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase();
                    if(item.contains("history")){
                        //history item
                        SoundManager.error((Player) event.getWhoClicked(), 10);
                        event.getWhoClicked().sendMessage(ChatColor.RED + "This is a WIP!");
                    } else if (title.startsWith("punish")) {
                        String target = ChatColor.stripColor(event.getClickedInventory().getItem(11).getItemMeta().getDisplayName()).replace("Punish ", "");
                        new ChooserGui().open((Player) event.getWhoClicked(), target);
                    }
                }
                //chooser gui
                if(event.getInventory().getSize() == 54){
                    String a = ChatColor.stripColor(event.getInventory().getItem(45).getLore().get(1)).toLowerCase();
                    DebugLogger.DebugLog(a);
                    switch (ChatColor.stripColor(event.getInventory().getItem(45).getLore().get(1)).toLowerCase()){
                        case "punishtypechooser":
                            event.setCancelled(true);
                            DebugLogger.DebugLog("punishtypechooser");
                            String target = ChatColor.stripColor(event.getView().getTitle().replace("player: ", ""));
                            String t = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase();
                            ChooserGuiListener.process(target, (Player) event.getWhoClicked(), type(t));
                            DebugLogger.DebugLog(target + " " + ((Player) event.getWhoClicked()).getDisplayName() + " " + t );
                    }
                }
            /*
            String title = ChatColor.stripColor(event.getView().getTitle());
            String[] title1 = StringUtils.stringToArray(title, " ");
            DebugLogger.DebugLog(title);
            DebugLogger.DebugLog(Arrays.toString(title1));
            String a = title.toLowerCase();
            if(a.startsWith("punish")) {
                String targetPlayer = title1[1];
                String targetItem = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                if(targetItem.startsWith("Punish ")) {
                    String target = event.getClickedInventory().getItem(54).getLore().get(2).replace("Player: ", "");
                    new ChooserGui().open((Player) event.getWhoClicked(), target);
                }
                else if(targetItem.startsWith("Player History")) {
                    SoundManager.error((Player) event.getWhoClicked(), 10);
                    event.getWhoClicked().sendMessage(ChatColor.RED + "This is a WIP!");
                }
            }
             */
            }
        }
    }
    public static PunishType type(String input){
        switch (input){
            case "perm ban":
                return PunishType.PERM_BAN;
            case "perm warn":
                return PunishType.PERM_WARN;
            case "perm mute":
                return PunishType.PERM_MUTE;
            case "temp ban":
                return PunishType.TEMP_BAN;
            case "temp warn":
                return PunishType.TEMP_WARN;
            case "temp mute":
                return PunishType.PERM_MUTE;
        }
        return null;
    }
}
