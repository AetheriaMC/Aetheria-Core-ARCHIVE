package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.staff.punish.PunishGUI;
import net.badbird5907.aetheriacore.spigot.commands.timevote.GUIListener;
import net.badbird5907.aetheriacore.spigot.commands.utils.Player_death_event;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;

import static org.bukkit.Bukkit.getServer;

public class SetupEvents {
	public static void registerEvents(AetheriaCore plugin) {
		if (plugin.getConfig().getBoolean("enablelegacyblacklistitems", true))
			getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), plugin);
		if (plugin.getConfig().getBoolean("enablechatfilter")) {

		}
		if (plugin.getConfig().getBoolean("disable-enderman-pickup", true))
			getServer().getPluginManager().registerEvents(new onEndermanPickup(plugin), plugin);
		getServer().getPluginManager().registerEvents(new onChat(plugin), plugin);
		getServer().getPluginManager().registerEvents(new OnVanish(), plugin);
		getServer().getPluginManager().registerEvents(new onarrowhit(), plugin);
		getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), plugin);
		getServer().getPluginManager().registerEvents(new BlockBreakEvent(), plugin);
		getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), plugin);
		getServer().getPluginManager().registerEvents(new ClickListener(), plugin);
		getServer().getPluginManager().registerEvents(new GuiListener(), plugin);
		getServer().getPluginManager().registerEvents(new Player_death_event(), plugin);
		getServer().getPluginManager().registerEvents(new NoteblockListener(), plugin);
		getServer().getPluginManager().registerEvents(new PunishGUI(), plugin);
		getServer().getPluginManager().registerEvents(new GUIListener(), plugin);
	}
}