package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static net.badbird5907.aetheriacore.spigot.util.GuiUtils.fullGuiWithBorder;
import static net.badbird5907.aetheriacore.spigot.util.WorldUils.current12hTime;
import static net.badbird5907.aetheriacore.spigot.util.XMaterial.*;
import static org.bukkit.ChatColor.*;

public class TimeVoteGUI {
	public static ItemStack border = new ItemStackBuilder(GRAY_STAINED_GLASS_PANE.parseMaterial()).amount(1).name(GRAY + "").build();
	public static String mainname = "Vote for time";
	public Inventory gui;

	public void open(Player player, String page) {
		if (page.equals("main")) {
			ItemStack loading = new ItemStackBuilder(BEDROCK.parseMaterial()).amount(1).name(RED + "Loading...").build();
			//gui.setItem(13, loading);
			//gui.setItem(49, loading);
			//gui.setItem(20, loading);
			//gui.setItem(24, loading);
			gui = fullGuiWithBorder(null, mainname, border);
			ItemStack currenttime = new ItemStackBuilder(CLOCK.parseMaterial()).amount(1).name(GREEN + "Current time:" + current12hTime(player)).build();
			gui.setItem(13, currenttime);
			ItemStack close = new ItemStackBuilder(BARRIER.parseMaterial()).amount(1).name(RED + "Close").build();
			gui.setItem(49, close);
			ItemStack day = new ItemStackBuilder(YELLOW_TERRACOTTA.parseMaterial()).name(GREEN + "Vote for day").lore(GRAY + "This will make you vote for daytime").build();
			gui.setItem(20, day);
			ItemStack night = new ItemStackBuilder(BLACK_TERRACOTTA.parseMaterial()).name(GREEN + "Vote for night").lore(GRAY + "This will make you vote for night").build();
			gui.setItem(24, night);
			player.openInventory(gui);
		}
	}
}