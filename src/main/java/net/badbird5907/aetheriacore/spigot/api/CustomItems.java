package net.badbird5907.aetheriacore.spigot.api;

import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import org.bukkit.inventory.ItemStack;

import static java.util.Objects.requireNonNull;

public class CustomItems {
    /*
    public ItemStack get(String str){
        ItemStack item = GetCustomItems.get().get();
        return item;
    }
     */

	public void put(String str, ItemStack itemStack) {
		requireNonNull(itemtypes.CustomItems()).put(str, itemStack);
		itemtypes.allitems.add(str);
	}
}