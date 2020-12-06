package net.badbird5907.aetheriacore.bungee.discord.listeners;

import net.badbird5907.aetheriacore.bungee.api.SendStaffChatMessage;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.config.Configuration;

public class SCListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
//        log.Log("works");
        Configuration config = Config.getData("bungeeconfig");
        if(event.getChannel().getIdLong() == Long.parseLong(config.getString("Discord.staffchat").replaceAll("L", ""))){
            if (event.getAuthor().isBot()) return;
            Message message = event.getMessage();
            String content = message.getContentRaw();
            String name = event.getAuthor().getName();
            SendStaffChatMessage.DiscordSend(name, content);
            log.Log("Discord Staff Chat > " + name + ": " + content);
        }
        else{
//            log.Log("Not channel " + config.getString("Discord.staffchat").replaceAll("L", "") + " Current channel is: " + event.getChannel().getIdLong());
            return;
        }
    }
}
