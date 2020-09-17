package net.badbird5907.aetheriacore.spigot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.badbird5907.aetheriacore.spigot.commands.*;
import net.badbird5907.aetheriacore.spigot.commands.trolls.levitate;
import net.badbird5907.aetheriacore.spigot.commands.trolls.opme;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.other.Lag;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class AetheriaCore extends JavaPlugin {

    public double togglepvp;

    private static AetheriaCore plugin;

    @Override
    public void onEnable() {
        if (getConfig().getBoolean("enable")) {
            plugin = this;
            // Plugin startup logic
            warn("Startup: Starting...");

            //register commands
            log("Startup: initializing Commands");
            this.setupCommands();

            //register events
            log("Startup: Registering Events...");
            this.setupEvents();
            log("All Events Registered!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);

            //get config
            log("Startup: Loading Config...");
            this.setupConfig();
            log("Startup: Config Loaded!!");

            //load mongodb
            if (plugin.getConfig().getBoolean("enableDatabase", true)) {
                log("Connecting to mongodb database...");
                DB();
                log("Connected! Expect some logs below");
                log("Configuring Database...");
                //Document document1 = new Document("test", "pickle").append("test", "test123");
                //collection.insertOne(document1);
                log("Done!");
            }
            else {
                warn("MongoDB is set to off in config. Plugin may not work correctly.");
            }

            //log("Setting Up Dependencys");
            //setupDependencies();
            //log("done!");

            //finished startup
            warn("Startup Finished!");
            log("INFO: do /AEC-debug for plugin info");
            log("INFO: do /AEC-reload to reload plugin");
            log("INFO: do /performance to show server performance");
        } else {
            warn("Plugin Disabled because disabled in config.yml");
            warn("Enable plugin by changing enable: false to enable: true");
        }

    }

    @Override
    public void onDisable() {
        //Plugin disable logic
        getServer().getScheduler().cancelTasks((Plugin) this);
        log("Plugin Disabled.");
        warn("Baiwoo!!!");
    }

    private void setupCommands() {
        getCommand("aetheriacore").setExecutor(new aetheriacore(this));
        getCommand("discord").setExecutor(new discord(this));
        getCommand("invis").setExecutor(new invis());
        getCommand("clearchat").setExecutor(new clearchat(this));
        getCommand("rules").setExecutor(new rules());
        getCommand("performance").setExecutor(new performance());
        getCommand("itemblacklist").setExecutor(new itemblacklist());
        getCommand("queuerestart").setExecutor(new queuerestart(this));
        getCommand("levitate").setExecutor(new levitate());
        getCommand("opme").setExecutor(new opme());
        getCommand("getUUID").setExecutor(new getUUID());
        getCommand("staffchat").setExecutor(new staffchat());
        getCommand("staffmode").setExecutor(new StaffMode());
        getCommand("hush").setExecutor(new hush());
/*        if(getConfig().getBoolean("Essentials-Replacement", true)){
            getCommand("fly").setExecutor(new Fly());
            getCommand("gma").setExecutor(new gma());
            getCommand("").setExecutor(new gmc());


        }
        */
        getCommand("Permtest").setExecutor(new Permtest());

    }

    private void setupEvents() {
        if (getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), this);
        }
        if (getConfig().getBoolean("enablechatfilter")) {

        }
        if (getConfig().getBoolean("disable-enderman-pickup", true)) {
            getServer().getPluginManager().registerEvents(new onEndermanPickup(), this);
        }
        getServer().getPluginManager().registerEvents(new joinListener(this), this);
        getServer().getPluginManager().registerEvents(new onChat(), this);
        getServer().getPluginManager().registerEvents(new OnVanish(), this);
    }

    private void setupConfig() {
        getConfig().addDefault("enable", true);
        getConfig().addDefault("enablelegacyblacklistitems", false);
        getConfig().addDefault("enablechatfilter", true);
        getConfig().addDefault("disable-enderman-pickup", true);
        getConfig().addDefault("togglepvp", true);
        getConfig().addDefault("enableDatabase", true);
        getConfig().addDefault("Database-Username", ""); //AetheriaCorePlugin
        getConfig().addDefault("Database-Password", ""); //AetheriaCorePlugin
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //FileConfiguration data = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "vars.yml"));

    }

    private void log(final String string) {
        Bukkit.getLogger().info(string);
    }

    private void warn(final String string) {
        Bukkit.getLogger().warning(string);
    }

    private void setupDependencies() {
        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish")) {
            log("SuperVanish Detected! Hooking into it.");
        }
        else{
            warn("AAAAAA SUPERVANISH DOSENT WORK");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
            log("PremiumVanish Detected! Hooking into it.");

        }

    }

    public void DB() {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://" + getConfig().getString("Database-Username") + ":" + getConfig().getString("Database-Password") + "@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        //MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
        MongoDatabase database = mongoClient.getDatabase("users");
    }
}
