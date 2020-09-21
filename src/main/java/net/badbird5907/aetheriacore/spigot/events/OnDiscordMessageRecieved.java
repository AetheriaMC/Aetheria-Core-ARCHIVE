package net.badbird5907.aetheriacore.spigot.events;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessagePreProcessEvent;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class OnDiscordMessageRecieved implements @NotNull Listener {
    AetheriaCore plugin;
    public OnDiscordMessageRecieved(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onDiscordChat(DiscordGuildMessagePreProcessEvent event) {
        if(event.getChannel().equals(plugin.getConfig().getString("StaffChat-Channel"))){
            StaffChatMessage.sendmessage(event.getAuthor().toString(), event.getMessage().toString());
        }
        else {
            return;
        }
    }
}
