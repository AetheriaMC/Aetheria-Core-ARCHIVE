package net.badbird5907.aetheriacore.bungee.auth.listeners;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.shared.handlers.AuthHandler;
import net.badbird5907.aetheriacore.shared.utils.Constants;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisabledEvents implements Listener {

    private final AetheriaCoreBungee main;
    public DisabledEvents(AetheriaCoreBungee main) {
        this.main = main;
    }

    @EventHandler
    public void onMessage(ChatEvent event) {
        if(!event.getMessage().startsWith("/")) return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if(AetheriaCoreBungee.getInstance().getAuthHandler().getAuthState(player.getUniqueId()) == AuthHandler.AuthState.PENDING_LOGIN) {
            String[] args = event.getMessage().substring(1).split("\\s+");
            if(AetheriaCoreBungee.getInstance().getConfigHandler().isCommandsDisabled()) {
                if(args.length > 0) {
                    String command = args[0];
                    if(!AetheriaCoreBungee.getInstance().getConfigHandler().getWhitelistedCommands().contains(command) && !Constants.mainCommand.equalsIgnoreCase(command)) {
                        event.setCancelled(true);
                        AetheriaCoreBungee.getInstance().getMessageHandler().sendMessage(player, "&cPlease validate your account with two-factor authentication");
                    }
                }
            } else {
                if(args.length > 0) {
                    String command = args[0];
                    if(AetheriaCoreBungee.getInstance().getConfigHandler().getBlacklistedCommands().contains(command)) {
                        event.setCancelled(true);
                        AetheriaCoreBungee.getInstance().getMessageHandler().sendMessage(player, "&cPlease validate your account with two-factor authentication");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onServerSwitch(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if(AetheriaCoreBungee.getInstance().getAuthHandler().getAuthState(player.getUniqueId()) == AuthHandler.AuthState.PENDING_LOGIN) {
            if(AetheriaCoreBungee.getInstance().getConfigHandler().isDisableServerSwitch()) {
                event.setCancelled(true);
                AetheriaCoreBungee.getInstance().getMessageHandler().sendMessage(player, "&cPlease validate your account with two-factor authentication");
            }
        }
    }
}
