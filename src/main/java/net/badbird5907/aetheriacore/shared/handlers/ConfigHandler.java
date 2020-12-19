package net.badbird5907.aetheriacore.shared.handlers;

import net.badbird5907.aetheriacore.shared.storage.StorageType;

import java.util.ArrayList;
import java.util.List;

public abstract class ConfigHandler {

    private final String qrCodeURL = "https://www.google.com/chart?chs=128x128&cht=qr&chl=otpauth://totp/";
    protected String serverName;
    protected String hashType;
    protected StorageType storageType;
    protected boolean requireOnIPChange = true;
    protected boolean requireOnEveryLogin = false;
    protected boolean advice2FA = true;
    protected boolean disableCommands = true;
    protected List<String> whitelistedCommands = new ArrayList<>();
    protected List<String> blacklistedCommands = new ArrayList<>();

    public String getQrCodeURL() { return this.qrCodeURL; }
    public String getServerName() { return this.serverName; }
    public String getHashType() { return this.hashType; }
    public StorageType getStorageType() { return this.storageType; }
    public boolean isRequiredOnIPChange() {
        return this.requireOnIPChange;
    }
    public boolean isRequiredOnEveryLogin() {
        return this.requireOnEveryLogin;
    }
    public boolean is2FAAdvised() {
        return this.advice2FA;
    }
    public boolean isCommandsDisabled() {
        return this.disableCommands;
    }
    public List<String> getWhitelistedCommands() {
        return this.whitelistedCommands;
    }
    public List<String> getBlacklistedCommands() {
        return this.blacklistedCommands;
    }

    public abstract void reload();
}