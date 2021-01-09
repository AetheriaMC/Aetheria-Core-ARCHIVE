package net.badbird5907.aetheriacore.spigot.commands.staff.punish;

import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class PunishGUI implements Listener {
    public Inventory gui;
    public void PunishGUI(Player sender, OfflinePlayer target){
        //Create Itemstacks
        ItemStack punishButton = new ItemStackBuilder(XMaterial.IRON_AXE.parseMaterial()).amount(1).lore(ChatColor.DARK_GRAY + "UUID: " + target.getUniqueId()).name(ChatColor.GOLD + "" + ChatColor.BOLD + target.getName()).build();
        ItemStack History = new ItemStackBuilder(XMaterial.WRITABLE_BOOK.parseMaterial()).amount(1).lore(ChatColor.RED + "NOT IN USE ATM.").name(ChatColor.GREEN + "Player History").build();
        ItemStack Close = new ItemStackBuilder(XMaterial.BARRIER.parseMaterial()).amount(1).name(ChatColor.RED + "Close").build();
        ItemStack Border = new ItemStackBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()).amount(1).name(ChatColor.BLUE + "").lore("").build();
        //Create Inventory
        //TODO look into 27 size
        gui = Bukkit.createInventory(null, 27, "Punish " + target.getName());
        //Create Meta
        ItemMeta punishButtonMeta = punishButton.getItemMeta();
        //Punish Meta
        punishButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lPunish " + target.getName()));
        List<String> punishLore = new ArrayList<>();
        punishLore.add(ChatColor.translateAlternateColorCodes('&', "&8UUID: " + target.getUniqueId()));
        punishButton.setItemMeta(punishButtonMeta);

        gui.setItem(11, punishButton);
        gui.setItem(15, History);
        sender.openInventory(gui);
    }
    @EventHandler
    public static void ClickEvent(InventoryClickEvent event){
        if(event.getClickedInventory() instanceof PunishGUI){
            event.setCancelled(true);
        }
    }
}
