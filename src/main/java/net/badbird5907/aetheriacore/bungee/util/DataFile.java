package net.badbird5907.aetheriacore.bungee.util;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {
    public static void createFile(String fileName) {
        if (!AetheriaCoreBungee.getInstance().getDataFolder().exists())
            AetheriaCoreBungee.getInstance().getDataFolder().mkdir();
        File file = new File(AetheriaCoreBungee.getInstance().getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (fileName.equals("bungeedata")) {
                    Configuration config = getData(fileName);
                    config.set("Lockdown.enabled", false);
                    saveData(config, fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getData(String fileName) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(AetheriaCoreBungee.getInstance().getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveData(Configuration config, String fileName) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(AetheriaCoreBungee.getInstance().getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
