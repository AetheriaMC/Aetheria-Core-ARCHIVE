package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.util.GuiUtils;
import net.badbird5907.aetheriacore.spigot.util.ItemstackBuilder;
import net.badbird5907.aetheriacore.spigot.util.WorldUils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TimeVoteGUI {
    Inventory gui;
    public void open(Player player, String page){
        if(page.equals("main")){
            ItemStack border = new ItemstackBuilder().material(Material.GRAY_STAINED_GLASS_PANE).amount(1).name(ChatColor.GRAY + "").build();
            gui = GuiUtils.fullGuiWithBorder(null, ChatColor.GREEN + "Vote for time", border);
            ItemStack currenttime = new ItemstackBuilder().material(Material.CLOCK).amount(1).name(ChatColor.GREEN + "Current time: " + WorldUils.current12hTime(player)).build();
            gui.setItem(13, currenttime);
            ItemStack close = new ItemstackBuilder().material(XMaterial.BARRIER).amount(1).name(ChatColor.RED + "Close").build();
            gui.setItem(49, close);
            ItemStack day = new ItemstackBuilder()
                    
                    .build();
        }
    }
}
