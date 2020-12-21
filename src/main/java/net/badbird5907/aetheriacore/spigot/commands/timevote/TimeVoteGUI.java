package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.util.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimeVoteGUI {
    public static String mainname = ChatColor.GREEN + "Vote for time";
    Inventory gui;
    public void open(Player player, String page){
        if(page.equals("main")){
            //ItemStack loading = new ItemBuilder().material(XMaterial.BEDROCK.parseMaterial()).name(ChatColor.RED + "Loading...").build();
            ItemStack loading = new ItemStackBuilder(XMaterial.BEDROCK.parseMaterial()).amount(1).name(ChatColor.RED + "Loading...").build();
            //ItemStack border = new ItemBuilder().material(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()).amount(1).name(ChatColor.GRAY + "").build();
            ItemStack border = new ItemStackBuilder(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial()).amount(1).name(ChatColor.GRAY + "").build();
            gui = GuiUtils.fullGuiWithBorder(null, mainname, border);
            gui.setItem(13, loading);
            gui.setItem(49, loading);
            gui.setItem(20, loading);
            gui.setItem(24, loading);
            //ItemStack currenttime = new ItemBuilder().material(XMaterial.CLOCK.parseMaterial()).amount(1).name(ChatColor.GREEN + "Current time:" + WorldUils.current12hTime(player)).build();
            ItemStack currenttime = new ItemStackBuilder(XMaterial.CLOCK.parseMaterial()).amount(1).name(ChatColor.GREEN + "Current time:" + WorldUils.current12hTime(player)).build();
            gui.setItem(13, currenttime);
            //ItemStack close = new ItemBuilder().material(XMaterial.BARRIER.parseMaterial()).amount(1).name(ChatColor.RED + "Close").build();
            ItemStack close = new ItemStackBuilder(XMaterial.BARRIER.parseMaterial()).amount(1).name(ChatColor.RED + "Close").build();
            gui.setItem(49, close);
            //ItemStack day = new ItemBuilder().material(XMaterial.YELLOW_TERRACOTTA.parseMaterial()).name(ChatColor.GREEN + "Vote for day").lore(ChatColor.GRAY + "This will make you vote for daytime").build();
            ItemStack day = new ItemStackBuilder(XMaterial.YELLOW_TERRACOTTA.parseMaterial()).name(ChatColor.GREEN + "Vote for day").lore(ChatColor.GRAY + "This will make you vote for daytime").build();
            gui.setItem(20, day);
            ItemStack night = new ItemStackBuilder(XMaterial.BLACK_TERRACOTTA.parseMaterial()).name(ChatColor.GREEN + "Vote for night").lore(ChatColor.GRAY + "This will make you vote for night").build();
            //ItemStack night = new ItemBuilder().material(XMaterial.BLACK_TERRACOTTA.parseMaterial()).name(ChatColor.GREEN + "Vote for night").lore(ChatColor.GRAY + "This will make you vote for night").build();
            gui.setItem(24, night);
        }
    }
}
