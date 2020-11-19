package net.badbird5907.aetheriacore.spigot.events;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessageReceivedEvent;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;

public class OnDiscordMessageRecieved{
    AetheriaCore plugin;
    public OnDiscordMessageRecieved(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onDiscordChat(DiscordGuildMessageReceivedEvent event) {
        if(event.getChannel().equals(plugin.getConfig().getString("StaffChat-Channel"))){
            StaffChatMessage.sendMessage(event.getAuthor().toString(), event.getMessage().toString());
        }
        else {
            return;
        }
    }
}
