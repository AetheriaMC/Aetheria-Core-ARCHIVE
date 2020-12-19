package net.badbird5907.aetheriacore.spigot.auth.handlers;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import net.badbird5907.aetheriacore.shared.storage.StorageType;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler extends net.badbird5907.aetheriacore.shared.handlers.ConfigHandler {

    private final AetheriaCore main;
    public ConfigHandler(AetheriaCore main) {
        this.main = AetheriaCore.getInstance();
        reload();
    }

    @Override
    public void reload() {
        FileConfiguration config = AetheriaCore.getInstance().getConfig();

        this.serverName = config.getString("Server Name");
        this.hashType = config.getString("IP Hash");
        this.storageType = StorageType.valueOf(config.getString("Storage Type", "JSON").toUpperCase());
        this.requireOnIPChange = config.getBoolean("Require When.IP Changes");
        this.requireOnEveryLogin = config.getBoolean("Require When.Every Login");
        this.advice2FA = config.getBoolean("Advise");
        this.disableCommands = config.getBoolean("Disable Commands");
        this.whitelistedCommands = config.getStringList("Whitelisted Commands");
        this.blacklistedCommands = config.getStringList("Blacklisted Commands");
    }
}
