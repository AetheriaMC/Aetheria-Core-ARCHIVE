package net.badbird5907.aetheriacore.bungee.discord.listeners;

import net.badbird5907.aetheriacore.bungee.api.SendAdminChatMessage;
import net.badbird5907.aetheriacore.bungee.api.SendStaffChatMessage;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.config.Configuration;

public class ACListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Configuration config = Config.getData("bungeeconfig");
        if(event.getChannel().getIdLong() == Long.parseLong(config.getString("Discord.adminchat").replaceAll("L", ""))){
            if (event.getAuthor().isBot()) return;
            Message message = event.getMessage();
            String content = message.getContentRaw();
            String name = event.getAuthor().getName();
            SendAdminChatMessage.DiscordSend(name, content);
            log.Log("Discord Admin Chat > " + name + ": " + content);
        }
        else return;
    }
}
