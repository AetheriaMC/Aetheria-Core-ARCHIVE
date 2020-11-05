package net.badbird5907.aetheriacore.spigot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import github.scarsz.discordsrv.DiscordSRV;
import net.badbird5907.aetheriacore.spigot.commands.aetheriacore;
import net.badbird5907.aetheriacore.spigot.commands.management.togglePvp;
import net.badbird5907.aetheriacore.spigot.commands.staff.QuickChat;
import net.badbird5907.aetheriacore.spigot.commands.staff.StaffMode;
import net.badbird5907.aetheriacore.spigot.commands.staff.staffchat;
import net.badbird5907.aetheriacore.spigot.commands.trolls.*;
import net.badbird5907.aetheriacore.spigot.commands.utils.*;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.other.Lag;
import net.badbird5907.aetheriacore.spigot.util.TabComplete;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public final class AetheriaCore extends JavaPlugin {

    private LuckPerms luckPerms;
    private File customConfigFile;
    private FileConfiguration customConfig;
    private static AetheriaCore plugin;
    private OnDiscordMessageRecieved discordsrvListener = new OnDiscordMessageRecieved(this);
    public static List<String> SUPPORTED_VERSIONS = new ArrayList<String>();
    private Connection connection;
    private String host, database, username, password;
    private int port;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        if (getConfig().getBoolean("enable")) {
            boolean mc1164 = Bukkit.getServer().getClass().getPackage().getName().contains("1.16.4");
            if(!mc1164)
                warn("SERVER IS VERSION: " + Bukkit.getServer().getVersion() + "ONLY " + SUPPORTED_VERSIONS.toString() + " IS SUPPORTED.");
            else
                log("Server is version " + Bukkit.getServer().getVersion() + " is supported!");
            DiscordSRV.api.subscribe(discordsrvListener);
            plugin = this;

            warn("Startup: Starting...");
            /*
            try {
                UpdateCheck();
            } catch (IOException e) {
                e.printStackTrace();
            }
             */

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
            createCustomConfig();
            this.setupConfig();
            log("Startup: Config Loaded!!");

            //load DB
            SetupDatabase();

            log("Setting Up Dependencies");
            setupDependencies();
            protocolManager = ProtocolLibrary.getProtocolManager();
            log("done!");

            //finished startup
            warn("Startup Finished!");
            log("INFO: do /AEC debug for plugin info");
            log("INFO: do /AEC reload to reload plugin config");
            log("INFO: do /performance to show server performance");
        } else {
            warn("Plugin Disabled because disabled in config.yml");
            warn("Enable plugin by changing enable: false to enable: true");
        }

    }

    @Override
    public void onDisable() {

        getServer().getScheduler().cancelTasks(this);
        DiscordSRV.api.unsubscribe(discordsrvListener);
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

    private void setupCommands() {
        getCommand("aetheriacore").setExecutor(new aetheriacore(this));
        getCommand("aetheriacore").setTabCompleter(new TabComplete());
        getCommand("invis").setExecutor(new invis());
        getCommand("clearchat").setExecutor(new clearchat(this));
        getCommand("rules").setExecutor(new rules());
        getCommand("performance").setExecutor(new performance());
        getCommand("itemblacklist").setExecutor(new itemblacklist());
        //getCommand("queuerestart").setExecutor(new queuerestart(this));
        //getCommand("levitate").setExecutor(new levitate());
        getCommand("dupethis").setExecutor(new DupeThis());
        getCommand("opme").setExecutor(new opme());
        getCommand("getuuid").setExecutor(new getUUID());
        getCommand("staffchat").setExecutor(new staffchat());
        getCommand("staffmode").setExecutor(new StaffMode());
        getCommand("hush").setExecutor(new hush());
        getCommand("QuickChat").setExecutor(new QuickChat(this));
        getCommand("ClearFloorDrops").setExecutor(new ClearFloorDrops());
        getCommand("SudoOp").setExecutor(new SudoOpPlaceholder());
        getCommand("freeze").setExecutor(new freezePlayer());
        getCommand("unfreeze").setExecutor(new Unfreeze());
        getCommand("nightvision").setExecutor(new NightVision());
        getCommand("togglePVP").setExecutor(new togglePvp(this));
        getCommand("CreateNPC").setExecutor(new CreateNPC());
        getCommand("killall").setExecutor(new KillAll());
        getCommand("link").setExecutor(new link());
        getCommand("masssay").setExecutor(new MassSay());
        getCommand("getclientbrand").setExecutor(new GetClientBrand());
        getCommand("getviewdistance").setExecutor(new GetViewDist());
        getCommand("item").setExecutor(new item());
        getCommand("item").setTabCompleter(new TabComplete());
        //getCommand("nick").setExecutor(new nick());
        //getCommand("addgroup").setExecutor(new addGroup(this, this.luckPerms));
        //getCommand("systeminfo").setExecutor(new SystemInfo(this));
        SudoOp.SudoOp.add("Badbird5907");
        SudoOp.SudoOp.add("tuckMCWizard");
        SudoOp.SudoOp.add("Pylons");
        SudoOp.SudoOp.add("StrawHat_KoITta");
        SudoOp.SudoOp.add("CONSOLE");
        /*
        if(getConfig().getBoolean("Essentials-Replacement", true)){
            getCommand("fly").setExecutor(new Fly());
            getCommand("gma").setExecutor(new gma());
            getCommand("gmsp").setExecutor(new gmc());
        }
         */

    }

    private void setupEvents() {
        if (getConfig().getBoolean("enablelegacyblacklistitems", true)) {
            getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), this);
        }
        if (getConfig().getBoolean("enablechatfilter")) {

        }
        if (getConfig().getBoolean("disable-enderman-pickup", true)) {
            getServer().getPluginManager().registerEvents(new onEndermanPickup(this), this);
        }
        getServer().getPluginManager().registerEvents(new onChat(), this);
        getServer().getPluginManager().registerEvents(new OnVanish(), this);
        getServer().getPluginManager().registerEvents(new OnPunish(), this);
        getServer().getPluginManager().registerEvents(new onarrowhit(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);

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
        Bukkit.getLogger().info(pluginManager.prefix + string);
    }

    private void warn(final String string) {
        Bukkit.getLogger().warning(pluginManager.prefix + string);
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
        MongoClient mongoClient = MongoClients.create("mongodb+srv://" + getConfig().getString("Database-Username") + ":" + getConfig().getString("Database-Password") + "@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
        //MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
        MongoDatabase database = mongoClient.getDatabase("users");
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

    private void createCustomConfig() {
        log("Checking Data File");
        customConfigFile = new File(getDataFolder(), "data.yml");
        if (!customConfigFile.exists()) {
            warn("Data file does not exist. Creating new file");
            customConfigFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        getDataFile().addDefault("pvp", true);
    }
    public void SetupDatabase(){
        if (plugin.getConfig().getBoolean("enableDatabase", true)) {
            log("Setting Up Database");
            if(plugin.getConfig().getBoolean("Custom-DB-port"))
                port = plugin.getConfig().getInt("Database-port");
            else
                port = 3306;

            host = plugin.getConfig().getString("Database-Url");
            database = plugin.getConfig().getString("Database-Name");
            username = plugin.getConfig().getString("Database-Username");
            password = plugin.getConfig().getString("Database-Password");
            try{
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
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database, this.username, this.password);
        }
    }
}