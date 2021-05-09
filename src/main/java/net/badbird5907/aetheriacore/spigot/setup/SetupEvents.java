package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.impl.staff.wipe.ConfGuiListener;
import net.badbird5907.aetheriacore.spigot.commands.impl.utils.Player_death_event;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.features.timevote.TimeVoteGUIListener;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class SetupEvents implements Setup{
    private static Listener[] listeners = new Listener[]{new onChat(AetheriaCore.getInstance()),new OnVanish(),new onarrowhit(),new PlayerMoveEvent(),new BlockBreakEvent(),new BlockPlaceEvent(),
            new ClickListener(),new Player_death_event(),new NoteblockListener(),new PlayerLeaveEvent(),new TimeVoteGUIListener(),new CloseButtonListener(),new ConfGuiListener()
    };
    public static void registerEvents(AetheriaCore plugin){
        if (plugin.getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), plugin);
        }
        if (plugin.getConfig().getBoolean("enablechatfilter")) {

        }
        if (plugin.getConfig().getBoolean("disable-enderman-pickup", true)) {
            Bukkit.getServer().getPluginManager().registerEvents(new onEndermanPickup(plugin), plugin);
        }
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener,plugin);
        }
    }

    @Override
    public void onEnable(AetheriaCore plugin) {
        registerEvents(plugin);
    }

    @Override
    public void onDisable(AetheriaCore plugin) {

    }
}
