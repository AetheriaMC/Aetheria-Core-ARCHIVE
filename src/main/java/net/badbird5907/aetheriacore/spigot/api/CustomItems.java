package net.badbird5907.aetheriacore.spigot.api;

import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import org.bukkit.inventory.ItemStack;

public class CustomItems {
    public ItemStack get(String str){
        ItemStack item = itemtypes.customitems.get(str);
        return item;
    }
    public void put(String str, ItemStack itemStack){
        itemtypes.customitems.put(str, itemStack);
        itemtypes.allitems.add(str);
    }
}
