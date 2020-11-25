package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriaitems.api.GetCustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class itemtypes {
    public static List<String> blocks = new ArrayList<>();
    public static List<String> allitems = new ArrayList<>();
    public static List<String> items = new ArrayList<>();
    public static List<Material> blacklisted_items = new ArrayList<>();
    public static HashMap CustomItems(){
        if(Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")){
            return GetCustomItems.get();
        }
        else
            return null;
    }
    public static List<String> alist = new ArrayList<>();
    public static void addToAllItems() {
        if(Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")){
            Set<String> keys = GetCustomItems.get().keySet();
            int n = keys.size();
            for(String x : keys){
                allitems.add(x);
                alist.add(x);
            }
            for (String x : alist)
                DebugLogger.DebugLog(x);
        }
    }
}
