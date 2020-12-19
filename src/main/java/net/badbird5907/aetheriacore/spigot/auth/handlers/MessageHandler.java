package net.badbird5907.aetheriacore.spigot.auth.handlers;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MessageHandler extends net.badbird5907.aetheriacore.shared.handlers.MessageHandler {

    private final AetheriaCore main;
    private YamlConfiguration messagesConfig;

    public MessageHandler(AetheriaCore main) {
        this.main = main;
        loadConfiguration();
    }

    public String getPrefix() {
        String prefix = super.getPrefix();
        if(messagesConfig.isSet("Prefix"))
            prefix = messagesConfig.getString("Prefix") + "&r ";

        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    public String getMessage(String message) {
        message = messagesConfig.getString(message, message);
        return getPrefix() + ChatColor.translateAlternateColorCodes('&', message);
    }

    public void sendMessage(Player player, String message) {
        message = getMessage(message);
        player.sendMessage(message);
    }

    public void loadConfiguration() {
        if(!main.getDataFolder().exists()) {
            main.getDataFolder().mkdirs();
        }
        File messagesFile = new File(main.getDataFolder(), "messages.yml");
        if(!messagesFile.exists()) {
            try {
                messagesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

        if(!messagesConfig.isSet("Prefix")) {
            messagesConfig.set("Prefix", "&7[&b2FA&7]");
        }

        for(String message : getDefaults()) {
            if(!messagesConfig.isSet(message)) {
                messagesConfig.set(message, message);
            }
        }

        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}