package net.badbird5907.aetheriacore.spigot.auth;

import net.badbird5907.aetheriacore.shared.utils.Constants;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.commands.CommandHandler;
import net.badbird5907.aetheriacore.spigot.auth.handlers.AuthHandler;
import net.badbird5907.aetheriacore.spigot.auth.handlers.BungeecordMessageHandler;
import net.badbird5907.aetheriacore.spigot.auth.handlers.ConfigHandler;
import net.badbird5907.aetheriacore.spigot.auth.handlers.MessageHandler;
import net.badbird5907.aetheriacore.spigot.auth.listeners.DisabledEvents;
import net.badbird5907.aetheriacore.spigot.auth.listeners.OnAuthStateChange;
import net.badbird5907.aetheriacore.spigot.auth.listeners.OnPlayerConnection;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class TwoFactorAuthentication {
    public  BungeecordMessageHandler pluginMessageListener;
    public  MessageHandler messageHandler;
    public  ConfigHandler configHandler;
    public  AuthHandler authHandler;
    public  CommandHandler commandHandler;
    public void enable(AetheriaCore i) {
        DebugLogger.DebugLog("Starting 2fa instance");
        setupAuth(i);
        registerListeners(i);
        for(Player pl : Bukkit.getOnlinePlayers())
            authHandler.playerJoin(pl.getUniqueId());
    }

    public void setupAuth(AetheriaCore inst) {
        pluginManager.log("Starting 2fa");
        TwoFactorAuthentication twoFactorAuthentication = new TwoFactorAuthentication();
        twoFactorAuthentication.messageHandler = new MessageHandler(inst);
        configHandler = new ConfigHandler(inst);
        authHandler = new AuthHandler(inst);

        if (commandHandler == null)
            commandHandler = new CommandHandler();
    }

    public void registerListeners(AetheriaCore i) {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new OnAuthStateChange(), i);
        pm.registerEvents(new OnPlayerConnection(i), i);
        pm.registerEvents(new DisabledEvents(i), i);

        pluginMessageListener = new BungeecordMessageHandler(i);
        i.getServer().getMessenger().registerOutgoingPluginChannel(i, Constants.channelName);
        i.getServer().getMessenger().registerIncomingPluginChannel(i, Constants.channelName, pluginMessageListener);
    }
    public BungeecordMessageHandler getPluginMessageListener() { return this.pluginMessageListener; }
    public MessageHandler getMessageHandler() { return this.messageHandler; }
    public ConfigHandler getConfigHandler() { return this.configHandler; }
    public AuthHandler getAuthHandler() { return this.authHandler; }
    private static TwoFactorAuthentication instance;
    public static TwoFactorAuthentication getInstance(){
        return instance;
    }
}