package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;

public class ChatFilter implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
    }

    public static void main( String args[]){
        StaffChatMessage.sendmessage("a", "a");
    }

}
