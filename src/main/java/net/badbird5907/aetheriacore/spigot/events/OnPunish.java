package net.badbird5907.aetheriacore.spigot.events;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class OnPunish implements Listener {
    TextChannel textChannel = DiscordSRV.getPlugin().getDestinationTextChannelForGameChannelName("aec-logs");
    @EventHandler(priority =  EventPriority.MONITOR)
    public void onpunish(PunishmentEvent event){
        // null if the channel isn't specified in the config.yml
        if (textChannel != null) {
            textChannel.sendMessage("Player " + event.getPunishment().getType() + " IGN:" + event.getPunishment().getName());
        } else {
            pluginManager.warn("Channel called \"aec-logs\" could not be found in the DiscordSRV configuration");
        }
        //pluginManager.log("works");

    }
}
