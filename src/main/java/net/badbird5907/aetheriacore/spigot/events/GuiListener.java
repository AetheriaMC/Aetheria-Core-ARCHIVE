package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        //play = player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 10, 1);
        Inventory playerInventory = player.getInventory();
        if (e.getView().getTitle().equals("§6Shopkeeper Menu")) {

//this gets the inventory test

            e.setCancelled(true);

//this makes it so players cannot take the item from the inventory with the display name "test"

            switch (e.getSlot()) {

                //OAK LOGS
                case 10:
                    ItemStack emerald64 = new ItemStack(Material.EMERALD, 63);

                    ItemStack oaklog64 = new ItemStack(Material.OAK_LOG, 64);

                    for (int i = 0; i < 65; i++) {
                        int price = 5;
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(oaklog64);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(oaklog64);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 11:
                    for (int i = 0; i < 65; i++) {
                        int price = 2;
                        ItemStack buyitem = new ItemStack(Material.COOKED_CHICKEN, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 12:
                    for (int i = 0; i < 65; i++) {
                        int price = 3;
                        ItemStack buyitem = new ItemStack(Material.COOKED_PORKCHOP, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 13:
                    for (int i = 0; i < 65; i++) {
                        int price = 3;
                        ItemStack buyitem = new ItemStack(Material.COOKED_MUTTON, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 14: //cod
                    for (int i = 0; i < 65; i++) {
                        int price = 3;
                        ItemStack buyitem = new ItemStack(Material.COOKED_COD, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 15://SALMON
                    for (int i = 0; i < 65; i++) {
                        int price = 3;
                        ItemStack buyitem = new ItemStack(Material.COOKED_SALMON, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 16:
                    for (int i = 0; i < 65; i++) {
                        int price = 4;
                        ItemStack buyitem = new ItemStack(Material.COOKED_RABBIT, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

                case 17:
                    for (int i = 0; i < 65; i++) {
                        int price = 3;
                        ItemStack buyitem = new ItemStack(Material.COOKED_BEEF, 1);
                        ItemStack target = new ItemStack(Material.EMERALD, i);
                        if (playerInventory.contains(target)){
                            if (i > price){
                                playerInventory.removeItem(target);
                                ItemStack refund = new ItemStack(Material.EMERALD, i-price);
                                playerInventory.addItem(refund);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            if (i == price){
                                playerInventory.removeItem(target);
                                playerInventory.addItem(buyitem);
                                i = i + 100;
                                continue;
                            }
                            else{
                                continue;
                            }
                        }
                    }

//case 11 = slot 11

                    //Add whatever here you want to execute when player clicks item.

            }
        }
    }
}
