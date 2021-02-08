package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.utils.IsInt;
import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import net.badbird5907.aetheriacore.utils.StringUtils;
import net.badbird5907.aetheriaitems.api.GetCustomItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class item implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender.hasPermission(Permission.ITEM.node)) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")) {
                    player.chat("/aetheriaitems:item " + StringUtils.arraytoString(args));
                } else {
                    if (args.length == 0)
                        sender.sendMessage(ChatColor.RED + "USAGE: /item <ITEM> <AMMOUNT> \n " + ChatColor.GREEN + "You can also do /itemmenu");

                    if (args.length == 1)
                        giveitem(player, args[0], "1", player);

                    if (args.length == 2)
                        giveitem(player, args[0], args[1], player);

                }
            } else
                sender.sendMessage(ChatColor.RED + "You must be a player to execute this!");
        }
        return true;
    }

    public static void giveitem(Player sender, String item, String ammount, Player player) {
        String item1 = item.toUpperCase();
        DebugLogger.DebugLog("Checking if " + item1 + " is an valid item");
        if (itemtypes.allitems.contains(item1)) {
            DebugLogger.DebugLog(item1 + " is a valid item. Checking if custom");
            if (iscustom(item1)) {
                DebugLogger.DebugLog(item1 + " is an custom item. ");
                if (IsInt.Check(ammount)) {
                    int a1 = Integer.parseInt(ammount);
                    sender.sendMessage(ChatColor.GREEN + "Gave " + player.getName() + " " + ammount + " of " + item1);
                    for (int i = a1; i > 0; i--) {
                        player.getInventory().addItem(GetCustomItems.getItem(item1));
                    }
                }
            } else {
                DebugLogger.DebugLog(item1 + " is not an custom item. Or AetheriaItems is not installed");
                if (IsInt.Check(ammount)) {
                    int amount = Integer.parseInt(ammount);
                    Player player1 = Bukkit.getPlayerExact(sender.getName());
                    Material m = Material.matchMaterial(item1);
                    ItemStack itemStack = new ItemStack(m, amount);
                    player1.getInventory().addItem(itemStack);
                    sender.sendMessage(ChatColor.GREEN + "Gave " + player.getName() + " " + ammount + " of " + item1);
                } else
                    sender.sendMessage(ChatColor.RED + ammount + " is not a integer.");
            }

        } else
            sender.sendMessage(ChatColor.RED + item + " is not a valid itemstack.");
    }

    public static boolean iscustom(String item) {
        if (Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")) {
            if (GetCustomItems.get().containsKey(item)) {
                return true;
            } else
                return false;
        } else
            return false;
    }
}
