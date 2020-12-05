package net.badbird5907.aetheriacore.spigot.commands.staff.punish;

import net.md_5.bungee.api.plugin.Listener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PunishGUI implements Listener {
    public Inventory gui;
    public void PunishGUI(Player sender, OfflinePlayer target){
        ItemStack punishButton = new ItemStack(Material.IRON_AXE);
        ItemStack History = new ItemStack(Material.WRITABLE_BOOK);
        gui = Bukkit.createInventory(null, 27, "Punish " + target.getName());
        ItemMeta punishButtonMeta = punishButton.getItemMeta();
        ItemMeta historyMeta = History.getItemMeta();
        historyMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Player History"));
        List<String> historyLore = new ArrayList<String>();
        historyLore.add(ChatColor.translateAlternateColorCodes('&', "&cNot in use ATM."));
        historyMeta.setLore(historyLore);
        punishButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lPunish " + target.getName()));
        gui.setItem(11, punishButton);
        gui.setItem(15, History);
        sender.openInventory(gui);
    }
}
