package net.badbird5907.aetheriacore.bungee.discord;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class sendmsg{
    private static JDA jda;
    public static void sendmsg(String message, String channelID){
        long id = Long.parseLong(channelID.replaceAll("L", ""));
        jda = AetheriaCoreBungee.getJDA();
        TextChannel channel = jda.getTextChannelById(id);
        channel.sendMessage(message).queue();
    }
    public static void sendmsg(MessageEmbed eb, String channelid){
        long id = Long.parseLong(channelid.replaceAll("L", ""));
        jda = AetheriaCoreBungee.getJDA();
        TextChannel channel = jda.getTextChannelById(id);
        channel.sendMessage(eb);
    }

}
