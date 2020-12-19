package net.badbird5907.aetheriacore.bungee.auth.handlers;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigHandler extends net.badbird5907.aetheriacore.shared.handlers.ConfigHandler {

    private final String configName = "2faconfig.yml";

    private final AetheriaCoreBungee main;

    private boolean disableServerSwitch;

    public ConfigHandler(AetheriaCoreBungee main) {
        this.main = main;

        reload();
    }

    @Override
    public void reload() {
        if(!main.getDataFolder().exists())
            main.getDataFolder().mkdir();

        File file = new File(main.getDataFolder(), configName);

        if(!file.exists()) {
            try(InputStream in = main.getResourceAsStream("bungee2faconfig.yml")) {
                Files.copy(in, file.toPath());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            this.disableCommands = config.getBoolean("Disable Commands");
            this.disableServerSwitch = config.getBoolean("Disable Server Switch");
            this.whitelistedCommands = config.getStringList("Whitelisted Commands");
            this.blacklistedCommands = config.getStringList("Blacklisted Commands");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDisableServerSwitch() {
        return this.disableServerSwitch;
    }
}