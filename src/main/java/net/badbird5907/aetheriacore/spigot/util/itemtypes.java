package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class itemtypes {
    public static List<String> blocks = new ArrayList<>();
    public static List<String> allitems = new ArrayList<>();
    public static List<String> items = new ArrayList<>();
    public static List<Material> blacklisted_items = new ArrayList<>();
    public static HashMap<String, ItemStack> customitems = new HashMap<>();
}
