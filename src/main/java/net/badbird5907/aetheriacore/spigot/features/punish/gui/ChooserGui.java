package net.badbird5907.aetheriacore.spigot.features.punish.gui;

import net.badbird5907.aetheriacore.utils.GuiUtils;
import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ChooserGui {
    public void open(Player p, String target){
        Inventory inv = GuiUtils.fullGuiWithBorder(ChatColor.RED + "Punish " + target, GuiUtils.Border, true);
        inv.setItem(11, new ItemStackBuilder(XMaterial.REDSTONE_BLOCK.parseMaterial()).amount(1).name(ChatColor.YELLOW + "Temp Ban " + target).build());
        inv.setItem(15, new ItemStackBuilder(XMaterial.BEDROCK.parseMaterial()).amount(1).name(ChatColor.RED + "Perm Ban " + target).build());
        inv.setItem(24, new ItemStackBuilder(XMaterial.RED_CONCRETE.parseMaterial()).amount(1).name(ChatColor.RED + "Perm Mute " + target).build());
        inv.setItem(20, new ItemStackBuilder(XMaterial.YELLOW_CONCRETE.parseMaterial()).amount(1).name(ChatColor.YELLOW + "Temp Mute " + target).build());
        inv.setItem(29, new ItemStackBuilder(XMaterial.YELLOW_CONCRETE.parseMaterial()).amount(1).name(ChatColor.YELLOW + "Temp Warn " + target).build());
        inv.setItem(33, new ItemStackBuilder(XMaterial.RED_CONCRETE.parseMaterial()).amount(1).name(ChatColor.RED + "Perm Warn " + target).build());
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "PunishTypeChooser");
        lore.add(ChatColor.GREEN + "Player: " + target);
        ItemStack info_item1 = new ItemStackBuilder(XMaterial.REDSTONE_TORCH.parseMaterial()).amount(1).name(ChatColor.GREEN +  "INFO").build();
        ItemMeta info_itemMeta = info_item1.getItemMeta();
        info_itemMeta.setLore(lore);
        info_item1.setItemMeta(info_itemMeta);
        inv.setItem(45, info_item1);
        p.openInventory(inv);
    }
}
