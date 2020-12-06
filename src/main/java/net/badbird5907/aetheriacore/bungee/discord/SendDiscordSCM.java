package net.badbird5907.aetheriacore.bungee.discord;

import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import org.bukkit.entity.Player;

public class SendDiscordSCM {
    public static void send(ProxiedPlayer player ,String message){
        Configuration config = Config.getData("bungeeconfig");
        String pname = player.getDisplayName();
        String rank = PlayerHandler.RankName(player);
        String sentmessage = "```css\n[" + rank + "] " + pname + " (" + player.getServer().getInfo().getName() + ")" +": " + message + "\n```";
        sendmsg.sendmsg(sentmessage, config.getString("Discord.staffchat"));
    }
}
