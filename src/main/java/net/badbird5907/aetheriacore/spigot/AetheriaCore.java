package net.badbird5907.aetheriacore.spigot;

import com.google.gson.Gson;
import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import lombok.Getter;
import net.badbird5907.aetheriacore.commons.LuckPermsManager;
import net.badbird5907.aetheriacore.spigot.bungeeutil.MessageRecieved;
import net.badbird5907.aetheriacore.spigot.commands.CommandFramework;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Placeholders;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import net.badbird5907.aetheriacore.spigot.modules.ban.AdvancedBanHook;
import net.badbird5907.aetheriacore.spigot.modules.ban.BanHook;
import net.badbird5907.aetheriacore.spigot.modules.ban.LiteBansHook;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import net.badbird5907.aetheriacore.spigot.setup.SetupCommands;
import net.badbird5907.aetheriacore.spigot.setup.SetupEvents;
import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import net.badbird5907.aetheriacore.utils.spigui.SpiGUI;
import net.dv8tion.jda.api.JDA;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public final class AetheriaCore extends JavaPlugin implements Listener {
    Gson gson = new Gson();
    public static AetheriaCore instance;
    public static List<String> SUPPORTED_VERSIONS = new ArrayList<String>();
    private static AetheriaCore plugin;
    public static BanHook banHook;
    public File customConfigFile;
    public FileConfiguration customConfig;
    public Consumer<Player> stopVanillaMusic = null;
    //sql
    private Connection connection;
    private String host, database, username, password;
    private int port;
    private final HashMap<Plugin, Boolean> dependentPlugins = new HashMap<>();
    public JDA jda;
    //music-end
    @Getter
    CommandFramework commandFramework;
    public AetheriaCore() {
        instance = this;
    }

    public static AetheriaCore getInstance() {
        return instance;
    }

    public static Class<?> getVersionedClass(String packageName, String className) throws ClassNotFoundException {
        return Class.forName(packageName + "." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + className);
    }
    public static SpiGUI spiGUI;
    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();
        if (getConfig().getBoolean("enable")) {
            spiGUI = new SpiGUI(this);
            PluginManager.initLogger();
            if (!PluginManager.is16()) warn("SERVER IS VERSION: " + Bukkit.getServer().getVersion() + "ONLY " + SUPPORTED_VERSIONS.toString() + " IS SUPPORTED.");
            else log("Server is version " + Bukkit.getServer().getVersion() + " is supported!");
            plugin = this;
            warn("Startup: Starting...");
            commandFramework = new CommandFramework(this);
            doStuff();
            getServer().getMessenger().registerIncomingPluginChannel( this, "aec:1", new MessageRecieved());
            getServer().getMessenger().registerOutgoingPluginChannel( this, "aec:2");
            log("Attempting write");
            //register commands
            log("Startup: initializing Commands");
            //this.setupCommands();
            SetupCommands.setupCommands(this);
            //register events
            log("Startup: Registering Events...");
            //this.setupEvents();
            new SetupEvents().onEnable(this);
            log("All Events Registered!");
            //get config
            log("Startup: Loading Config...");
            Noteblock.DataFile();
                this.setupConfig();
            log("Startup: Config Loaded!!");
            SetupDatabase();
            log("Setting Up Dependencies");
            LuckPermsManager.init();
            setupDependencies();
            log("Hooking ban plugins");
            banHook = hookBans();
            //FIXME hook bans
            //banHook.enable();
            log("Starting jukebox...");
            if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) Placeholders.registerPlaceholders();
            getLogger().info("This JukeBox version requires NoteBlockAPI version 1.5.0 or more. Please ensure you have the right version before using JukeBox (you are using NBAPI ver. " + getPlugin(NoteBlockAPI.class).getDescription().getVersion() + ")");
            saveDefaultConfig();
            Noteblock.waitThenRun();
            //finished startup
            warn("Startup Finished! Took " + (System.currentTimeMillis() - start) + "ms." );
        } else {
            warn("Plugin Disabled because disabled in config.yml");
            warn("Enable plugin by changing enable: false to enable: true");
        }

    }

    @Override
    public void onDisable() {
        if (!Noteblock.disable) disableAll();
        getServer().getScheduler().cancelTasks(this);
        //DiscordSRV.api.unsubscribe(discordsrvListener);
        log("Killing All Custom Hostile Mobs. (as Colbite wanted)");
        // Iterate through every world on the server
        int removed_entities = 0;
        for (World w : Bukkit.getWorlds()) {
            // Iterate through every entity in that world
            for (Entity e : w.getEntities()) {
                //If Entity has custom Hostile AI as defined by MetaData, remove
                if (e.hasMetadata("Hostile_AI")) {
                    removed_entities++;
                    e.remove();
                }
            }
        }
        log(removed_entities + " Custom Hostile Entites Removed.");
        log("Plugin Disabled.");
        warn("Baiwoo!!!");
    }

    private void setupConfig() {
        FileConfiguration config = getConfig();
        config.addDefault("enable", true);
        config.addDefault("enablelegacyblacklistitems", false);
        config.addDefault("enablechatfilter", true);
        config.addDefault("disable-enderman-pickup", true);
        config.addDefault("enableDatabase", true);
        config.addDefault("Database-Username", ""); //AetheriaCorePlugin
        config.addDefault("Database-Password", ""); //AetheriaCorePlugin
        config.addDefault("Database-Url", "");
        config.addDefault("Database-port", "");
        config.addDefault("Custom-DB-port", false);
        config.addDefault("Database-Name", "");
//        config.addDefault("discord-link", "");
        config.addDefault("StaffChat-Channel", "");
        config.addDefault("Server-Type", "NOT-SET");
        config.addDefault("pvp", true);
        config.addDefault("version", 1.0);
        config.addDefault("Console-Debug-Default", true);
        /*
        config.addDefault("check-for-updates", true);
        config.addDefault("version", 2.0);
         */
        config.options().copyDefaults();
        saveDefaultConfig();

        //FileConfiguration data = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "vars.yml"));

    }

    private void log(final String string) {
        Bukkit.getLogger().info(PluginManager.prefix + string);
    }

    private void warn(final String string) {
        Bukkit.getLogger().warning(PluginManager.prefix + string);
    }

    private void setupDependencies() {
        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish")) {
            log("SuperVanish Detected! Hooking into it.");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
            log("PremiumVanish Detected! Hooking into it.");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("AetheriaMinigames")) {
            log("AetheriaMinigames Is Running On This Server!");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("AetheriaCheat")) {
            log("AetheriaAntiCheat Is Running On This Server!");
        }
    }

    public void DB() {
        /*
        MongoClient mongoClient = MongoClients.create("mongodb+srv://" + getConfig().getString("Database-Username") + ":" + getConfig().getString("Database-Password") + "@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        //MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
        MongoDatabase database = mongoClient.getDatabase("users");
         */
    }

    private void UpdateCheck() throws IOException {
        if (getConfig().getBoolean("check-for-updates")) {
            String versionServer = getText("https://badbird5907.net/api/aetheriacore/version");
            if (versionServer == getConfig().getString("version")) {
                log("Version Up to date.");

            } else {
                log("Please Update. Server responded with: " + versionServer);
            }
        }
    }

    String getText(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        //add headers to the connection, or check the status if desired..

        // handle error response code it occurs
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);
        in.close();
        return response.toString();
    }

    public FileConfiguration getDataFile() {
        return this.customConfig;
    }

    public void SetupDatabase() {
        if (plugin.getConfig().getBoolean("enableDatabase", true)) {
            PluginManager.log("Setting Up Database");
            if (plugin.getConfig().getBoolean("Custom-DB-port"))
                port = plugin.getConfig().getInt("Database-port");
            else
                port = 3306;

            host = plugin.getConfig().getString("Database-Url");
            database = plugin.getConfig().getString("Database-Name");
            username = plugin.getConfig().getString("Database-Username");
            password = plugin.getConfig().getString("Database-Password");
            try {
                openConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            warn("Database is not enabled. Plugin may not work as expected");
        }
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
        }
    }

    private void doStuff() {
        itemtypes.addToAllItems();
        for (Material material : Material.values()) {
            itemtypes.allitems.add(material.name());
            if (material.isBlock())
                itemtypes.blocks.add(material.name());
            if(PluginManager.is16()){
                if (material.isItem())
                    itemtypes.items.add(material.name());
            }
            else itemtypes.items.add(material.name());
            if (material.toString().contains("SPAWN_EGG"))
                itemtypes.blacklisted_items.add(material);
        }
        for (Plugin pl : getServer().getPluginManager().getPlugins()) {
            if (pl.getDescription().getDepend().contains("AetheriaCore") || pl.getDescription().getSoftDepend().contains("AetheriaCore")) {
                dependentPlugins.put(pl, false);
            }
        }
        //this.getServer().getMessenger().registerOutgoingPluginChannel(this, "aec");
    }

    public void disableAll() {
        if (Noteblock.radio != null) {
            Noteblock.radio.stop();
            Noteblock.radio = null;
        }
        if (Noteblock.datas != null) {
            if (Noteblock.savePlayerDatas && Noteblock.db == null)
                Noteblock.players.set("players", Noteblock.datas.getSerializedList());
            Noteblock.players.set("item", (Noteblock.jukeboxItem == null) ? null : Noteblock.jukeboxItem.serialize());
            try {
                Noteblock.players.save(Noteblock.playersFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Noteblock.vanillaMusicTask != null) Noteblock.vanillaMusicTask.cancel();
        HandlerList.unregisterAll((JavaPlugin) this);
    }
    private BanHook hookBans(){
        if(Bukkit.getPluginManager().isPluginEnabled("AdvancedBan")){
            log("Hooked into AdvancedBan!");
            return new AdvancedBanHook(this);
        }else if(Bukkit.getPluginManager().isPluginEnabled("LiteBans")){
            log("Hooked into LiteBans!");
            return new LiteBansHook(this);
        }
        return null;
    }

}