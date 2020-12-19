package net.badbird5907.aetheriacore.spigot.auth.listeners;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import net.badbird5907.aetheriacore.shared.utils.Constants;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;

public class DisabledEvents implements Listener {

    private final AetheriaCore main;
    public DisabledEvents(AetheriaCore main) {
        this.main = main;
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if(TwoFactorAuthentication.getInstance() == null)
            DebugLogger.DebugLog("2fa instance is null");
        if(TwoFactorAuthentication.getInstance().getAuthHandler() == null)
            DebugLogger.DebugLog("AuthHandler is null");
        else if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            if(event.getTo() != null && (event.getTo().getBlockZ() != event.getFrom().getBlockZ() || event.getTo().getBlockX() != event.getFrom().getBlockX())) {
                event.setTo(event.getFrom());
                TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(event.getPlayer(), "&cPlease validate your account with two-factor authentication");
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(event.getPlayer(), "&cPlease validate your account with two-factor authentication");
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(event.getPlayer(), "&cPlease validate your account with two-factor authentication");
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(event.getPlayer(), "&cPlease validate your account with two-factor authentication");
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        } else if(TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getItemDrop().getItemStack())) {
            event.getItemDrop().remove();
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onItemPickup(PlayerPickupItemEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
        } else if(TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getItem().getItemStack())) {
            event.getItem().remove();
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(player.getUniqueId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();
        } else if(event.getCurrentItem() != null && event.getInventory().getType() != InventoryType.PLAYER &&
                (TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getCurrentItem()) || TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getCursor()))) {
            event.setCancelled(true);
        } else if(event.getHotbarButton() > -1 && TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getWhoClicked().getInventory().getItem(event.getHotbarButton()))) {
            event.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().needsToAuthenticate(event.getPlayer().getUniqueId())) {
            String[] args = event.getMessage().substring(1).split("\\s+");
            if(TwoFactorAuthentication.getInstance().getConfigHandler().isCommandsDisabled()) {
                if(args.length > 0) {
                    String command = args[0];
                    if(!TwoFactorAuthentication.getInstance().getConfigHandler().getWhitelistedCommands().contains(command) && !Constants.mainCommand.equalsIgnoreCase(command)) {
                        event.setCancelled(true);
                        TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(event.getPlayer(), "&cPlease validate your account with two-factor authentication");
                    }
                }
            } else {
                if(args.length > 0) {
                    String command = args[0];
                    if(TwoFactorAuthentication.getInstance().getConfigHandler().getBlacklistedCommands().contains(command)) {
                        event.setCancelled(true);
                        TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(event.getPlayer(), "&cPlease validate your account with two-factor authentication");
                    }
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onItemMove(InventoryMoveItemEvent event) {
        if(TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getItem()) && event.getDestination().getType() != InventoryType.PLAYER) {
            event.setCancelled(true);
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    @SuppressWarnings("deprecation")
    public void onItemFrameInteract(PlayerInteractEntityEvent event) {
        if(event.getRightClicked() instanceof ItemFrame) {
            if(TwoFactorAuthentication.getInstance().getAuthHandler().isQRCodeItem(event.getPlayer().getItemInHand())) {
                event.setCancelled(true);
            }
        }
    }
}