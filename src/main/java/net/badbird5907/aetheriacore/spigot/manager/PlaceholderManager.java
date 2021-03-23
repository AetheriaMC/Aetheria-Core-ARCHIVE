package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.md_5.bungee.api.ChatColor;

public class PlaceholderManager implements Manager{
    @Override
    public void onEnable(AetheriaCore plugin) {

    }

    @Override
    public void onDisable(AetheriaCore plugin) {

    }
    //TODO placeholders
    public static String replacePlaceholders(String s){
        return translate(s);
    }
    public static String translate(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
