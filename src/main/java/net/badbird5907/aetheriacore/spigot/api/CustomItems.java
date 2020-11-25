package net.badbird5907.aetheriacore.spigot.api;

import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import net.badbird5907.aetheriaitems.api.GetCustomItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CustomItems {
    /*
    public ItemStack get(String str){
        ItemStack item = GetCustomItems.get().get();
        return item;
    }
     */

    public void put(String str, ItemStack itemStack){
        itemtypes.CustomItems().put(str, itemStack);
        itemtypes.allitems.add(str);
    }


}
