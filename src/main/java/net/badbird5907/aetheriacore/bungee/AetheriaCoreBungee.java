package net.badbird5907.aetheriacore.bungee;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.badbird5907.aetheriacore.bungee.commands.staff.*;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalBroadcast;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.listeners.events;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AetheriaCoreBungee extends Plugin implements Listener {
    public static List<UUID> inSc = new ArrayList<>();
    public static List<UUID> inAc = new ArrayList<>();
    public static List<UUID> inCSpy = new ArrayList<>();
    public static List<UUID> Hush = new ArrayList<>();
    private static AetheriaCoreBungee instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        log.Log("Starting...");
        //getProxy().registerChannel("aetheriacore:messaging");
        createFile("bungeeconfig");
        log.Log( "Registering Commands...");
        getProxy().getInstance().getPluginManager().registerCommand(this, new Hub());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Beta());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Creative());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Survival());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Vanilla());

        getProxy().getInstance().getPluginManager().registerCommand(this, new AdminChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new CSpy());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalBroadcast());
        getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new staff());

        log.Log("Registering Events...");
        getProxy().getPluginManager().registerListener(this, new events());
        /*
        getProxy().getInstance().getPluginManager().registerListener(this, new OnLogin());
        getProxy().getInstance().getPluginManager().registerListener(this, new CommandListener());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnDisconnect());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnSwitch());
         */
        log.Log("Startup Finished!!!");

    }

/*
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
*/
    private void setupDatabase(){
        MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        //MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
        MongoDatabase database = mongoClient.getDatabase("users");
    }
    public static AetheriaCoreBungee getInstance() {
        return instance;
    }
    private void createFile(String fileName) {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (fileName.equals("bungeeconfig")) {
                    Configuration config = getConfig(fileName);
                    config.set("Config.enable-join-message", Boolean.valueOf(true));
                    config.set("Config.enable-leave-message", Boolean.valueOf(true));
                    config.set("Config.enable-switch-messages", Boolean.valueOf(true));
                    config.set("Messages.sc-enabled", "&8[&6AEC&8]  &8&7Staff chat is now &aenabled&7.");
                    config.set("Messages.sc-disabled", "&8[&6AEC&8] &8&7Staff chat is now &cdisabled&7.");
                    config.set("Messages.sc-format", "&6&lStaffChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
                    config.set("Messages.ac-enabled", "&8[&6AEC&8] &8&7Admin chat is now &aenabled&7.");
                    config.set("Messages.ac-disabled", "&8[&6AEC&8] &8&7Admin chat is now &cdisabled&7.");
                    config.set("Messages.ac-format", "&c&lAdminChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
                    config.set("Messages.cspy-enabled", "&8[&6AEC&8] &8&7Command spy is now &aenabled&7.");
                    config.set("Messages.cspy-disabled", "&8[&6AEC&8] &8&7Command spy is now &cdisabled&7.");
                    config.set("Messages.cspy-format", "&9&lCommandSpy &8» &7(%server%) &r%user%: %command%");
                    config.set("Messages.no-permission", "&cYou do not have permissions to do this!&8 &oNO_PERMISSIONS");
                    config.set("Messages.helpop-no-message", "&c&lUsage: /gethelp <message>");
                    config.set("Messages.clearchat-bypass", "&aChat was cleared by %player% but you're immune");
                    config.set("Messages.clearchat", "&aChat was cleared globally by %player%");
                    List<String> list = new ArrayList<>();
                    list.add("&7&m-----------------------------------");
                    list.add("&7----&r&9&lReport&r&7----");
                    list.add("&9Reporter: &e%reporter% &7(%reporter-server%)");
                    list.add("&9Reported: &e%reported% &7(%reported-server%)");
                    list.add("&9Reason: &e%reason%");
                    list.add("&7&m-----------------------------------");
                    config.set("Messages.report-to-staff-message", list);
                    config.set("Messages.report-to-player-message", "&8[&6AEC&8] &8&aYou have reported: &e%player%&a, &afor &athe &areason: &e%reason%&7.");
                    config.set("Messages.report-incorrect-format", "&8[&6AEC&8] &cUsage: /report <player> <reason>");
                    config.set("Messages.report-offline-player", "&8[&6AEC&8] &8&cThat player is currently offline.");
                    config.set("Messages.staff-join-network", "&aStaff &8» &f%player% &aConnected");
                    config.set("Messages.staff-leave-network", "&aStaff &8» &f%player% &cDisconnected");
                    config.set("Messages.staff-switch-server", "&aStaff &8 » &f%player% &aSwitched from %from% to %to%");
                    List<String> list2 = new ArrayList<>();
                    list2.add("&7&m-----------------------------------");
                    list2.add("&c&l----Help Requested----");
                    list2.add("&e%player% &7(%server%)");
                    list2.add("&eMessage: %msg%");
                    list2.add("&7&m-----------------------------------");
                    config.set("Messages.helpop-to-staff", list2);
                    config.set("Messages.helpop-to-player", "&8[&6AEC&8] &8&7Requested help from staff: &c%msg%7.");
                    config.set("Messages.in-cooldown", "&9You still in cooldown: &7%seconds% left.");
                    saveConfig(config, fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Configuration getConfig(String fileName) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveConfig(Configuration config, String fileName) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


