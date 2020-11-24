package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiMaker implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {

            Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("shopkeeper")) {

                Inventory gui = Bukkit.getServer().createInventory(p, 54, "§6Shopkeeper Menu");

                //create filler1
                ItemStack filler1 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                ItemMeta metafiller1 = filler1.getItemMeta();

                metafiller1.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                filler1.setItemMeta(metafiller1);

                ArrayList<String> lorefiller1 = new ArrayList<String>();

                metafiller1.setLore(lorefiller1);
                metafiller1.setDisplayName(" ");
                filler1.setItemMeta(metafiller1);

                //create filler2
                ItemStack filler2 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                ItemMeta metafiller2 = filler2.getItemMeta();

                metafiller2.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                filler2.setItemMeta(metafiller2);

                ArrayList<String> lorefiller2 = new ArrayList<String>();

                metafiller2.setLore(lorefiller1);
                metafiller2.setDisplayName(" ");
                filler2.setItemMeta(metafiller2);

                //make oak logs
                for (int i = 0; i < 1; i++) {
                    ItemStack oaklog64 = new ItemStack(Material.OAK_LOG, 64);
                    ItemMeta metaoaklog64 = oaklog64.getItemMeta();
                    metaoaklog64.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    oaklog64.setItemMeta(metaoaklog64);
                    ArrayList<String> loreoaklog64 = new ArrayList<String>();

                    loreoaklog64.add(" ");
                    loreoaklog64.add("§fCost: §25 Emeralds");
                    loreoaklog64.add(" ");
                    loreoaklog64.add("§aClick to purchase!");

                    metaoaklog64.setDisplayName("§fOak Wood §7(x64)");

                    metaoaklog64.setLore(loreoaklog64);
                    oaklog64.setItemMeta(metaoaklog64);

                    gui.setItem(10, oaklog64);
                }
                //make chicken slot 11
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_CHICKEN, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §22 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Chicken §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(11, newitem);
                } //2 EMS
                //make porkchop slot 12
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_PORKCHOP, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §23 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Porkchop §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(12, newitem);
                } //3 EMS
                //make mutton slot 13
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_MUTTON, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §23 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Mutton §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(13, newitem);
                } //3 EMS
                //make cod slot 14
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_COD, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §23 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Cod §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(14, newitem);
                } //3 EMS
                //make salmon slot 15
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_SALMON, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §23 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Salmon §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(15, newitem);
                } //3 EMS
                //make rabbit slot 16
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_RABBIT, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §24 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Porkchop §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(16, newitem);
                } //4 EMS
                //make steak slot 17
                for (int i = 0; i < 1; i++) {
                    ItemStack newitem = new ItemStack(Material.COOKED_BEEF, 1);
                    ItemMeta itemmeta = newitem.getItemMeta();
                    itemmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    newitem.setItemMeta(itemmeta);
                    ArrayList<String> itemlore = new ArrayList<String>();

                    itemlore.add(" ");
                    itemlore.add("§fCost: §25 Emeralds");
                    itemlore.add(" ");
                    itemlore.add("§aClick to purchase!");

                    itemmeta.setDisplayName("§fCooked Porkchop §7(x1)");

                    itemmeta.setLore(itemlore);
                    newitem.setItemMeta(itemmeta);

                    gui.setItem(19, newitem);
                } //5 EMS
                //make outline - blue
                gui.setItem(0, filler1);
                gui.setItem(2, filler1);
                gui.setItem(4, filler1);
                gui.setItem(6, filler1);
                gui.setItem(8, filler1);
                gui.setItem(26, filler1);
                gui.setItem(44, filler1);
                gui.setItem(52, filler1);
                gui.setItem(50, filler1);
                gui.setItem(48, filler1);
                gui.setItem(46, filler1);
                gui.setItem(36, filler1);
                gui.setItem(18, filler1);
                //make outline - green
                gui.setItem(1, filler2);
                gui.setItem(3, filler2);
                gui.setItem(5, filler2);
                gui.setItem(7, filler2);
                gui.setItem(17, filler2);
                gui.setItem(35, filler2);
                gui.setItem(53, filler2);
                gui.setItem(51, filler2);
                gui.setItem(49, filler2);
                gui.setItem(47, filler2);
                gui.setItem(45, filler2);
                gui.setItem(27, filler2);
                gui.setItem(9, filler2);

//Sets item in slot, in this case slot 11

                p.openInventory(gui);

                return true;

//Opens the Inventory

            }
            return true;
        }
        return false;
    }
}