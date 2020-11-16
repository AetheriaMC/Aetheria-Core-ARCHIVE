package net.badbird5907.aetheriacore.bungee.events;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class CommandListener implements Listener {
    @EventHandler
    public void onChat2(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        if (p.hasPermission(Permission.COMMAND_SPY_BYPASS.node))
            return;
        if (e.getMessage().startsWith("/")){
            for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                if (AetheriaCoreBungee.CommandSpyPlayers.contains(staff.getUniqueId())) {
                    //staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.cspy-format")).replaceAll("%server%", p.getServer().getInfo().getName()).replaceAll("%player%", p.getName()).replaceAll("%command%", e.getMessage())));
                    if(AetheriaCoreBungee.Hush.contains(staff.getUniqueId())){
                        break;
                    }
                    else
                        staff.sendMessage(new TextComponent(ChatColor.GREEN + "CommandSpy" + ChatColor.DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + ChatColor.BLUE + p.getName() + ChatColor.WHITE + ": " + e.getMessage()));
                }

            }
        }
    }
}
