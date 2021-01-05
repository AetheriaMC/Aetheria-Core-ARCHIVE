package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriaitems.api.GetCustomItems;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.util.IsInt.Check;
import static net.badbird5907.aetheriacore.spigot.util.itemtypes.allitems;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.Material.matchMaterial;

public class item implements CommandExecutor {
	public static void giveitem(Player sender, String item, String ammount, Player player) {
		String item1 = item.toUpperCase();
		DebugLog("Checking if " + item1 + " is an valid item");
		if (allitems.contains(item1)) {
			DebugLog(item1 + " is a valid item. Checking if custom");
			if (iscustom(item1)) {
				DebugLog(item1 + " is an custom item. ");
				if (Check(ammount)) {
					sender.sendMessage(GREEN + "Gave " + player.getName() + " " + ammount + " of " + item1);
					for (int i = parseInt(ammount); i > 0; i--)
						player.getInventory().addItem(GetCustomItems.getItem(item1));
				}
			} else {
				DebugLog(item1 + " is not an custom item. Or AetheriaItems is not installed");
				if (Check(ammount)) {
					int amount = parseInt(ammount);
					Player player1 = getPlayerExact(sender.getName());
					Material m = matchMaterial(item1);
					ItemStack itemStack = new ItemStack(m, amount);
					player1.getInventory().addItem(itemStack);
					sender.sendMessage(GREEN + "Gave " + player.getName() + " " + amount + " of " + item1);
				} else
					sender.sendMessage(RED + ammount + " is not a integer.");
			}

		} else
			sender.sendMessage(RED + item + " is not a valid itemstack.");
	}

	public static boolean iscustom(String item) {
		if (getPluginManager().isPluginEnabled("AetheriaItems")) return GetCustomItems.get().containsKey(item);
		else return false;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		if (sender.hasPermission(Permission.ITEM.node)) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (getPluginManager().isPluginEnabled("AetheriaItems"))
					player.chat("/aetheriaitems:item " + Arrays.toString(args));
				else {
					if (args.length == 0)
						sender.sendMessage(RED + "USAGE: /item <ITEM> <AMMOUNT> \n " + GREEN + "You can also do /itemmenu");
					if (args.length == 1) giveitem(player, args[0], "1", player);
					if (args.length == 2) giveitem(player, args[0], args[1], player);
				}
			} else sender.sendMessage(RED + "You must be a player to execute this!");
		}
		return true;
	}
}