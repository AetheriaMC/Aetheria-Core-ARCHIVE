package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.staff.wipe.ConfGuiListener;
import net.badbird5907.aetheriacore.spigot.commands.utils.Player_death_event;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.features.timevote.TimeVoteGUIListener;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
import org.bukkit.Bukkit;

public class SetupEvents {
    public static void registerEvents(AetheriaCore plugin){
        if (plugin.getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), plugin);
        }
        if (plugin.getConfig().getBoolean("enablechatfilter")) {

        }
        if (plugin.getConfig().getBoolean("disable-enderman-pickup", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new onEndermanPickup(plugin), plugin);
        }
        Bukkit.getServer().getPluginManager().registerEvents(new onChat(plugin), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new OnVanish(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new onarrowhit(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakEvent(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new GuiListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new Player_death_event(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new NoteblockListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new TimeVoteGUIListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new CloseButtonListener(), plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new ConfGuiListener(), plugin);
    }
}
