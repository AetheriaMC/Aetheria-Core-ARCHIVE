package net.badbird5907.aetheriacore.spigot.util.staffmenu.gui;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.util.GuiUtils;
import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainGui {
    public void open(Player p){
        Inventory inv = GuiUtils.halfGuiWithBorder(null, ChatColor.GREEN + "",GuiUtils.Border);
        ItemStack allPlayers = new ItemStackBuilder(XMaterial.PLAYER_HEAD.parseMaterial()).amount(1).name(ChatColor.GREEN + "All Players Online").build();
        ItemStack serverControl = new ItemStackBuilder(XMaterial.COMMAND_BLOCK.parseMaterial()).amount(1).name(ChatColor.GREEN + "Server Control Panel").build();
        ItemStack staffControl = new ItemStackBuilder(XMaterial.STRUCTURE_BLOCK.parseMaterial()).amount(1).name(ChatColor.GREEN + "Staff Control Panel").build();
        ItemStack noperms = new ItemStackBuilder(XMaterial.BEDROCK.parseMaterial()).amount(1).name(ChatColor.RED + "Well. This is awkward...").lore(ChatColor.RESET + "Well this is weird...\nYou don't have any perms to view any buttons... :/").build();
        if(p.hasPermission(Permission.STAFF_MENU_STAFF_CONTROL.node))
            inv.setItem(10, staffControl);
        if(p.hasPermission(Permission.STAFF_MENU_SERVER_CONTROL.node))
            inv.setItem(13, serverControl);
        if(p.hasPermission(Permission.STAFF_MENU_ONLINE_PLAYERS.node))
            inv.setItem(16, allPlayers);
        if(!p.hasPermission(Permission.STAFF_MENU_SERVER_CONTROL.node) && !p.hasPermission(Permission.STAFF_MENU_STAFF_CONTROL.node) && !p.hasPermission(Permission.STAFF_MENU_ONLINE_PLAYERS.node))
            inv.setItem(13, noperms);
        inv.setItem(22, GuiUtils.Close);
        p.openInventory(inv);
    }
}
