package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.utils.Player_death_event;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
import org.bukkit.Bukkit;

public class SetupEvents {
    public static void registerEvents(AetheriaCore plugin){
        if (plugin.getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), AetheriaCore.instance);
        }
        if (plugin.getConfig().getBoolean("enablechatfilter")) {

        }
        if (plugin.getConfig().getBoolean("disable-enderman-pickup", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new onEndermanPickup(AetheriaCore.instance), AetheriaCore.instance);
        }
        Bukkit.getServer().getPluginManager().registerEvents(new onChat(AetheriaCore.instance), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new OnVanish(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new OnPunish(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new onarrowhit(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakEvent(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new GuiListener(), AetheriaCore.instance);
        Bukkit.getServer().getPluginManager().registerEvents(new Player_death_event(), AetheriaCore.instance);
    }
}
