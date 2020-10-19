package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.commands.freezePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerMoveEvent implements Listener {

    @EventHandler
    public void playerFrozen(org.bukkit.event.player.PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (freezePlayer.frozen.contains(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are currently frozen by a staff member.");
        }
        /*
        Material[] bannedItems = {Material.COMMAND_BLOCK, Material.COMMAND_BLOCK_MINECART, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.BEDROCK, Material.BARRIER, Material.STRUCTURE_BLOCK, Material.SPAWNER, Material.DEBUG_STICK, Material.JIGSAW};
        for (Material m : bannedItems) { //Loop thru all elements
            if (player.getInventory().contains(m, 1)) { //Check if in inventory
                //Check for perms; if no, remove
                if (player.hasPermission(permissionManager.BypassItemBlacklist)) {
                    break;
                } else {
                    player.getInventory().remove(m);
                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
                    player.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------");
                    player.sendMessage(ChatColor.RED + "You had blacklisted items in your inventory! We had to remove it.");
                    player.sendMessage(ChatColor.RED + "Please contact a staff member if you believe this is a mistake");
                    player.sendMessage(ChatColor.RED + "MISSING PERMISSION NODE: aetheriacore.bypass.blacklistitems");
                    player.sendMessage(ChatColor.RED + "ITEM REMOVED: " + m);
                    player.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------");
                    Bukkit.getLogger().warning("Blacklisted item (" + m + ") detected & deleted in " + player + "'s inventory");
                    StaffChatMessage.sendmessage("ItemBlacklist", "Blacklisted item (" + m + ") detected & deleted in " + player + "'s inventory");
                    return;
                }
            }
        }

         */
    }
}
