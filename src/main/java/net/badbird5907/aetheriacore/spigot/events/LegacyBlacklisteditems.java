package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LegacyBlacklisteditems implements Listener {
    AetheriaCore plugin;

    public LegacyBlacklisteditems(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        /**
         * @Deceprecated Do not use, causes insane lag as it loops through all items and for each player.
         */
        Material[] bannedItems = { Material.COMMAND_BLOCK, Material.COMMAND_BLOCK_MINECART, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.BEDROCK, Material.BARRIER,
                Material.STRUCTURE_BLOCK, Material.SPAWNER, Material.DEBUG_STICK, Material.JIGSAW, Material.BAT_SPAWN_EGG, Material.BEE_SPAWN_EGG, Material.BLAZE_SPAWN_EGG, Material.CAT_SPAWN_EGG, Material.CAVE_SPIDER_SPAWN_EGG,
                Material.CHICKEN_SPAWN_EGG, Material.COD_SPAWN_EGG, Material.COW_SPAWN_EGG, Material.CREEPER_SPAWN_EGG, Material.DOLPHIN_SPAWN_EGG, Material.DONKEY_SPAWN_EGG, Material.DROWNED_SPAWN_EGG,
                Material.ELDER_GUARDIAN_SPAWN_EGG, Material.ENDERMAN_SPAWN_EGG, Material.ENDERMITE_SPAWN_EGG, Material.EVOKER_SPAWN_EGG, Material.FOX_SPAWN_EGG, Material.GHAST_SPAWN_EGG, Material.GUARDIAN_SPAWN_EGG, Material.HOGLIN_SPAWN_EGG,
                Material.HORSE_SPAWN_EGG, Material.HUSK_SPAWN_EGG, Material.LLAMA_SPAWN_EGG, Material.MAGMA_CUBE_SPAWN_EGG, Material.MOOSHROOM_SPAWN_EGG, Material.MULE_SPAWN_EGG, Material.OCELOT_SPAWN_EGG, Material.PANDA_SPAWN_EGG, Material.PARROT_SPAWN_EGG,
                Material.PARROT_SPAWN_EGG, Material.PHANTOM_SPAWN_EGG, Material.PIG_SPAWN_EGG, Material.PIGLIN_SPAWN_EGG, Material.PILLAGER_SPAWN_EGG, Material.POLAR_BEAR_SPAWN_EGG, Material.PUFFERFISH, Material.RABBIT_SPAWN_EGG, Material.RAVAGER_SPAWN_EGG,
                Material.SALMON_SPAWN_EGG, Material.SHEEP_SPAWN_EGG, Material.SHULKER_SPAWN_EGG, Material.SILVERFISH_SPAWN_EGG, Material.SKELETON_SPAWN_EGG, Material.SKELETON_HORSE_SPAWN_EGG, Material.SLIME_SPAWN_EGG, Material.SPIDER_SPAWN_EGG, Material.SQUID_SPAWN_EGG,
                Material.STRAY_SPAWN_EGG, Material.STRIDER_SPAWN_EGG, Material.TRADER_LLAMA_SPAWN_EGG, Material.TROPICAL_FISH_SPAWN_EGG, Material.TURTLE_SPAWN_EGG, Material.VEX_SPAWN_EGG, Material.VILLAGER_SPAWN_EGG, Material.VINDICATOR_SPAWN_EGG, Material.WANDERING_TRADER_SPAWN_EGG,
                Material.WITCH_SPAWN_EGG, Material.WITHER_SKELETON_SPAWN_EGG, Material.WOLF_SPAWN_EGG, Material.ZOMBIE_SPAWN_EGG, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, Material.ZOMBIE_HORSE_SPAWN_EGG, Material.ZOMBIE_VILLAGER_SPAWN_EGG, Material.ZOGLIN_SPAWN_EGG,

        };
        Player player = (Player) event.getWhoClicked();

        for(Material m : bannedItems) { //Loop thru all elements
            if(player.getInventory().contains(m, 1)){ //Check if in inventory
                //Check for perms; if no, remove
                if(player.hasPermission(Permission.BYPASS_ITEM_BLACKLIST.node)){
                    break;
                }
                else {
                    if (plugin.getConfig().getBoolean("enableblacklistitems")){
                        player.getInventory().remove(m);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
                        player.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------");
                        player.sendMessage(ChatColor.RED + "You had blacklisted items in your inventory! We had to remove it.");
                        player.sendMessage(ChatColor.RED + "Please contact a staff member if you believe this is a mistake.");
                        player.sendMessage(ChatColor.RED + "ITEM REMOVED: " + m);
                        player.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------");
                        Bukkit.getLogger().warning("Blacklisted item (" + m + ") detected & deleted in " + player.getDisplayName() + "'s inventory");
                        for (Player loopplayer : Bukkit.getOnlinePlayers()) { //loop through all players and see if they are staff
                            if(loopplayer.hasPermission("aetheriacore.blacklistitems.viewlogs")){
                                player.sendMessage(ChatColor.RED + "Blacklisted item " + ChatColor.BOLD + "(" + m + ")" + ChatColor.RESET + " " + ChatColor.RED + "detected & deleted in " + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + "" + ChatColor.RED + "'s inventory");
                            }

                        }

                    }
                    else{
                        break;
                    }


                }
            }
        }
    }
}
