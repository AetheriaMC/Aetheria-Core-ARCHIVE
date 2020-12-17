package net.badbird5907.aetheriacore.spigot.discord;

import net.badbird5907.aetheriacore.bungee.discord.listeners.ACListener;
import net.badbird5907.aetheriacore.bungee.discord.listeners.SCListener;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.config.Configuration;

import javax.security.auth.login.LoginException;

public class Discord {
    public static void init(JDA jda){
        Configuration conf1 = Config.getData("bungeeconfig");
        try{
            jda = JDABuilder
                    .createDefault(conf1.getString("Discord.token"))
                    .addEventListeners(new SCListener())
                    .addEventListeners(new ACListener())
                    .build();
        }catch (LoginException e){
            e.printStackTrace();
        }
    }
    public static JDA getJDA() {
        return AetheriaCore.getInstance().jda;
    }
}
