package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.staff.punish.PunishGUI;
import net.badbird5907.aetheriacore.spigot.commands.utils.Player_death_event;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
import org.bukkit.Bukkit;

public class SetupEvents {
    public static void registerEvents(AetheriaCore plugin){
        if (plugin.getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), AetheriaCore.getInstance());
        }
        if (plugin.getConfig().getBoolean("enablechatfilter")) {

        }
        if (plugin.getConfig().getBoolean("disable-enderman-pickup", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new onEndermanPickup(AetheriaCore.getInstance()), AetheriaCore.getInstance());
        }
        Bukkit.getServer().getPluginManager().registerEvents(new onChat(AetheriaCore.getInstance()), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new OnVanish(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new onarrowhit(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakEvent(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new GuiListener(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new Player_death_event(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new NoteblockListener(), AetheriaCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new PunishGUI(), AetheriaCore.getInstance());
    }
}
