package net.badbird5907.aetheriacore.spigot;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
//import github.scarsz.discordsrv.DiscordSRV;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Placeholders;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import net.badbird5907.aetheriacore.spigot.other.Lag;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import net.badbird5907.aetheriacore.spigot.setup.SetupCommands;
import net.badbird5907.aetheriacore.spigot.setup.SetupEvents;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
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

//import net.badbird5907.aetheriacore.spigot.util.SignGUI;

public final class AetheriaCore extends JavaPlugin implements Listener {
    public static AetheriaCore instance;
    public static List<String> SUPPORTED_VERSIONS = new ArrayList<String>();
    private static AetheriaCore plugin;
    public File customConfigFile;
    public FileConfiguration customConfig;
    public Consumer<Player> stopVanillaMusic = null;
    private LuckPerms luckPerms;
    //private final OnDiscordMessageRecieved discordsrvListener = new OnDiscordMessageRecieved(this);
    //sql
    private Connection connection;
    private String host, database, username, password;
    private int port;
    private final HashMap<Plugin, Boolean> dependentPlugins = new HashMap<>();
    public JDA jda;
    //music-end

    //protocolib
    //private ProtocolManager protocolManager;
    //SignGUI signGui;
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
            //signGui = new SignGUI(this);
            spiGUI = new SpiGUI(this);
            boolean mc1164 = Bukkit.getServer().getClass().getPackage().getName().contains("1.16.4");
            if (!mc1164)
                warn("SERVER IS VERSION: " + Bukkit.getServer().getVersion() + "ONLY " + SUPPORTED_VERSIONS.toString() + " IS SUPPORTED.");
            else
                log("Server is version " + Bukkit.getServer().getVersion() + " is supported!");
            //DiscordSRV.api.subscribe(discordsrvListener);
            plugin = this;

            warn("Startup: Starting...");
            doStuff();
            //register commands
            //TwoFactorAuthentication auth = new TwoFactorAuthentication();
            //TwoFactorAuthentication.getInstance().enable(this);
            log("Startup: initializing Commands");
            //this.setupCommands();
            SetupCommands.setupCommands(this);

            //register events
            log("Startup: Registering Events...");
            //this.setupEvents();
            SetupEvents.registerEvents(this);
            log("All Events Registered!");
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
            //get config
            log("Startup: Loading Config...");
            Noteblock.DataFile();
            this.setupConfig();
            log("Startup: Config Loaded!!");

            //load DB
            SetupDatabase();

            log("Setting Up Dependencies");
            setupDependencies();
            log("Connecting to discord...");
            //Discord.init(jda);
            //protocolManager = ProtocolLibrary.getProtocolManager();
            log("done!");
            log("Starting jukebox...");
            //initAll();
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
    private void setupEvents() {
        //unused rn. check the class SetupEvents
        if (getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), this);
        }
        if (getConfig().getBoolean("enablechatfilter")) {

        }
        if (getConfig().getBoolean("disable-enderman-pickup", true)) {
            getServer().getPluginManager().registerEvents(new onEndermanPickup(this), this);
        }
        getServer().getPluginManager().registerEvents(new onChat(this), this);
        getServer().getPluginManager().registerEvents(new OnVanish(), this);
        //getServer().getPluginManager().registerEvents(new OnPunish(), this);
        getServer().getPluginManager().registerEvents(new onarrowhit(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
    }

    private void setupConfig() {
        getConfig().addDefault("enable", true);
        getConfig().addDefault("enablelegacyblacklistitems", false);
        getConfig().addDefault("enablechatfilter", true);
        getConfig().addDefault("disable-enderman-pickup", true);
        getConfig().addDefault("enableDatabase", true);
        getConfig().addDefault("Database-Username", ""); //AetheriaCorePlugin
        getConfig().addDefault("Database-Password", ""); //AetheriaCorePlugin
        getConfig().addDefault("Database-Url", "");
        getConfig().addDefault("Database-port", "");
        getConfig().addDefault("Custom-DB-port", false);
        getConfig().addDefault("Database-Name", "");
//        getConfig().addDefault("discord-link", "");
        getConfig().addDefault("StaffChat-Channel", "");
        getConfig().addDefault("Server-Type", "NOT-SET");
        getConfig().addDefault("pvp", true);
        getConfig().addDefault("version", 1.0);
        getConfig().addDefault("Console-Debug-Default", true);
        /*
        getConfig().addDefault("check-for-updates", true);
        getConfig().addDefault("version", 2.0);
         */
        getConfig().options().copyDefaults();
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
        this.luckPerms = getServer().getServicesManager().load(LuckPerms.class);

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
            if (material.isItem())
                itemtypes.items.add(material.name());
            if (material.toString().contains("SPAWN_EGG"))
                itemtypes.blacklisted_items.add(material);
        }
        for (Plugin pl : getServer().getPluginManager().getPlugins()) {
            if (pl.getDescription().getDepend().contains("AetheriaCore") || pl.getDescription().getSoftDepend().contains("AetheriaCore")) {
                dependentPlugins.put(pl, false);
            }
        }
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "aetheriacore:messaging");
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

}