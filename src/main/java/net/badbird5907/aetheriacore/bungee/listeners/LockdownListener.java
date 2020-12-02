package net.badbird5907.aetheriacore.bungee.listeners;

import net.badbird5907.aetheriacore.bungee.util.DataFile;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class LockdownListener implements Listener {
    @EventHandler
    public void Check(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        Configuration datafile = DataFile.getData("bungeedata");
        if(e.getMessage().equalsIgnoreCase("/lockdown HIGH")){
            if(p.hasPermission(Permission.GLOBAL_LOCKDOWN.node)){
                if(datafile.getBoolean("Lockdown.enabled")){
                    datafile.set("Lockdown.enabled", false);
                    DataFile.saveData(datafile, "bungeedata");
                }
                else{
                    p.sendMessage(new TextComponent(

                            ));
                }
            }
        }
    }
}
