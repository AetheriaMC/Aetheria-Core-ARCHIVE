package net.badbird5907.aetheriacore.spigot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import github.scarsz.discordsrv.DiscordSRV;
import net.badbird5907.aetheriacore.spigot.commands.aetheriacore;
import net.badbird5907.aetheriacore.spigot.commands.management.togglePvp;
import net.badbird5907.aetheriacore.spigot.commands.staff.Lockdown;
import net.badbird5907.aetheriacore.spigot.commands.staff.QuickChat;
import net.badbird5907.aetheriacore.spigot.commands.staff.StaffMode;
import net.badbird5907.aetheriacore.spigot.commands.staff.staffchat;
import net.badbird5907.aetheriacore.spigot.commands.trolls.*;
import net.badbird5907.aetheriacore.spigot.commands.utils.*;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.jukebox.*;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.*;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.other.Lag;
import net.badbird5907.aetheriacore.spigot.util.TabComplete;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import net.luckperms.api.LuckPerms;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Collator;
import java.util.*;
import java.util.function.Consumer;

//import net.badbird5907.aetheriacore.spigot.util.SignGUI;

public final class AetheriaCore extends JavaPlugin implements Listener {
    private LuckPerms luckPerms;
    public File customConfigFile;
    public FileConfiguration customConfig;
    private static AetheriaCore plugin;
    private OnDiscordMessageRecieved discordsrvListener = new OnDiscordMessageRecieved(this);
    public static List<String> SUPPORTED_VERSIONS = new ArrayList<String>();
    //sql
    private Connection connection;
    private String host, database, username, password;
    private int port;
    //protocolib
    private ProtocolManager protocolManager;
    //SignGUI signGui;
    public static AetheriaCore instance;
    public AetheriaCore() {
        instance = this;
    }
    public static AetheriaCore getInstance() {
        return instance;
    }

    //music
    public static int version = Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1]);
    private boolean disable = false;

    private static File playersFile;
    public static FileConfiguration players;
    public static File songsFolder;

    public static JukeBoxRadio radio = null;

    private static LinkedList<Song> songs;
    private static Map<String, Song> fileNames;
    public static Map<String, Song> internalNames;
    private static Playlist playlist;

    public static int maxPage;
    public static boolean jukeboxClick = false;
    public static boolean sendMessages = true;
    public static boolean async = false;
    public static boolean autoJoin = false;
    public static boolean radioEnabled = true;
    public static boolean radioOnJoin = false;
    public static boolean autoReload = true;
    public static boolean preventVanillaMusic = false;
    public static PlayerData defaultPlayer = null;
    public static List<String> worldsEnabled;
    public static boolean worlds;
    public static boolean particles;
    public static boolean actionBar;
    public static Material songItem;
    public static String itemFormat;
    public static String itemFormatWithoutAuthor;
    public static String itemFormatAdmin;
    public static String itemFormatAdminWithoutAuthor;
    public static String songFormat;
    public static String songFormatWithoutAuthor;
    public static boolean savePlayerDatas = true;

    public ItemStack jukeboxItem;

    private Database db;
    public JukeBoxDatas datas;

    private BukkitTask vanillaMusicTask = null;
    public Consumer<Player> stopVanillaMusic = null;
    //music-end


    @Override
    public void onEnable() {
        instance = this;
        if (getConfig().getBoolean("enable")) {
            //signGui = new SignGUI(this);
            boolean mc1164 = Bukkit.getServer().getClass().getPackage().getName().contains("1.16.4");
            if(!mc1164)
                warn("SERVER IS VERSION: " + Bukkit.getServer().getVersion() + "ONLY " + SUPPORTED_VERSIONS.toString() + " IS SUPPORTED.");
            else
                log("Server is version " + Bukkit.getServer().getVersion() + " is supported!");
            DiscordSRV.api.subscribe(discordsrvListener);
            plugin = this;

            warn("Startup: Starting...");
            doStuff();
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
            DataFile();
            this.setupConfig();
            log("Startup: Config Loaded!!");

            //load DB
            SetupDatabase();

            log("Setting Up Dependencies");
            setupDependencies();
            protocolManager = ProtocolLibrary.getProtocolManager();
            log("done!");
            log("Starting jukebox...");
            //initAll();
            if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) Placeholders.registerPlaceholders();
            getLogger().info("This JukeBox version requires NoteBlockAPI version 1.5.0 or more. Please ensure you have the right version before using JukeBox (you are using NBAPI ver. " + getPlugin(NoteBlockAPI.class).getDescription().getVersion() + ")");
            saveDefaultConfig();
            waitThenRun();
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
        if (!disable) disableAll();
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
        getCommand("itemmenu").setExecutor(new itemmenu());
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("mutechat").setExecutor(new mutechat(this));
        getCommand("kickallnonstaff").setExecutor(new KickAllNonStaff());
        getCommand("lockdown").setExecutor(new Lockdown());
        getCommand("shopkeeper").setExecutor(new GuiMaker());

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
        getServer().getPluginManager().registerEvents(new onChat(this), this);
        getServer().getPluginManager().registerEvents(new OnVanish(), this);
        getServer().getPluginManager().registerEvents(new OnPunish(), this);
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

    public void DataFile() {
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
        getDataFile().addDefault("mutechatstatis", false);

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
    private void doStuff(){
        for (Material material : Material.values()) {
            itemtypes.allitems.add(material.name().toString());
            if(material.isBlock())
                itemtypes.blocks.add(material.name().toString());
            if(material.isItem())
                itemtypes.items.add(material.name().toString());
            if(material.toString().contains("SPAWN_EGG"))
                itemtypes.blacklisted_items.add(material);
        }
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "aetheriacore:messaging");
    }
    public void waitThenRun(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                initAll();
            }
        }, 20L); // 600L (ticks) is equal to 30 seconds (20 ticks = 1 second)
        return;
    }
    public void disableAll(){
        if (radio != null){
            radio.stop();
            radio = null;
        }
        if (datas != null) {
            if (savePlayerDatas && db == null) players.set("players", datas.getSerializedList());
            players.set("item", (jukeboxItem == null) ? null : jukeboxItem.serialize());
            try {
                players.save(playersFile);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (vanillaMusicTask != null) vanillaMusicTask.cancel();
        HandlerList.unregisterAll((JavaPlugin) this);
    }
    public void initAll(){
        reloadConfig();

        loadLang();
        if (disable) return;

        FileConfiguration config = getConfig();
        jukeboxClick = config.getBoolean("jukeboxClick");
        sendMessages = config.getBoolean("sendMessages");
        async = config.getBoolean("asyncLoading");
        autoJoin = config.getBoolean("forceJoinMusic");
        defaultPlayer = PlayerData.deserialize(config.getConfigurationSection("defaultPlayerOptions").getValues(false), null);
        particles = config.getBoolean("noteParticles") && version >= 9;
        actionBar = config.getBoolean("actionBar") && version >= 9;
        radioEnabled = config.getBoolean("radio");
        radioOnJoin = radioEnabled && config.getBoolean("radioOnJoin");
        autoReload = config.getBoolean("reloadOnJoin");
        preventVanillaMusic = config.getBoolean("preventVanillaMusic") && version >= 13;
        songItem = Material.matchMaterial(config.getString("songItem"));
        itemFormat = config.getString("itemFormat");
        itemFormatWithoutAuthor = config.getString("itemFormatWithoutAuthor");
        itemFormatAdmin = config.getString("itemFormatAdmin");
        itemFormatAdminWithoutAuthor = config.getString("itemFormatAdminWithoutAuthor");
        songFormat = config.getString("songFormat");
        songFormatWithoutAuthor = config.getString("songFormatWithoutAuthor");
        savePlayerDatas = config.getBoolean("savePlayerDatas");

        worldsEnabled = config.getStringList("enabledWorlds");
        worlds = !worldsEnabled.isEmpty();

        ConfigurationSection dbConfig = config.getConfigurationSection("database");
        /*
        if (dbConfig.getBoolean("enabled")) {
            db = new Database(dbConfig);
            if (db.openConnection()) {
                getLogger().info("Connected to database.");
            }else {
                getLogger().info("Failed to connect to database. Now using YAML system.");
                db = null;
            }
        }
         */
        db = null;

        if (async){
            new BukkitRunnable() {
                @Override
                public void run() {
                    loadDatas();
                    finishEnabling();
                }
            }.runTaskAsynchronously(this);
        }else{
            loadDatas();
            finishEnabling();
        }

        if (preventVanillaMusic) {
            try {
                String nms = "net.minecraft.server";
                String cb = "org.bukkit.craftbukkit";
                Method getHandle = getVersionedClass(cb, "entity.CraftPlayer").getDeclaredMethod("getHandle");
                Field playerConnection = getVersionedClass(nms, "EntityPlayer").getDeclaredField("playerConnection");
                Method sendPacket = getVersionedClass(nms, "PlayerConnection").getDeclaredMethod("sendPacket", getVersionedClass(nms, "Packet"));
                Class<?> soundCategory = getVersionedClass(nms, "SoundCategory");
                Object packet = getVersionedClass(nms, "PacketPlayOutStopSound").getDeclaredConstructor(getVersionedClass(nms, "MinecraftKey"), soundCategory).newInstance(null, soundCategory.getDeclaredField("MUSIC").get(null));

                stopVanillaMusic = player -> {
                    try {
                        sendPacket.invoke(playerConnection.get(getHandle.invoke(player)), packet);
                    }catch (ReflectiveOperationException e1) {
                        e1.printStackTrace();
                    }
                };

                vanillaMusicTask = Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
                    for (PlayerData pdata : datas.getDatas()) {
                        if (pdata.isPlaying() && pdata.getPlayer() != null) stopVanillaMusic.accept(pdata.getPlayer());
                    }
                }, 20L, 100l); // every 5 seconds
            }catch (ReflectiveOperationException ex) {
                ex.printStackTrace();
            }
        }
    }
    private Class<?> getVersionedClass(String packageName, String className) throws ClassNotFoundException {
        return Class.forName(packageName + "." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + className);
    }

    private void finishEnabling(){

        getCommand("music").setExecutor(new CommandMusic());
        getCommand("adminmusic").setExecutor(new CommandAdmin());
        getCommand("adminmusic").setTabCompleter(new TabComplete());
        getServer().getPluginManager().registerEvents(this, this);

        radioEnabled = radioEnabled && !songs.isEmpty();
        if (radioEnabled){
            radio = new JukeBoxRadio(playlist);
        }else radioOnJoin = false;

        for (Player p : Bukkit.getOnlinePlayers()) {
            datas.joins(p);
        }
    }

    private void loadDatas(){
        /* --------------------------------------------- SONGS ------- */
        songs = new LinkedList<>();
        fileNames = new HashMap<>();
        internalNames = new HashMap<>();
        songsFolder = new File(getDataFolder(), "songs");
        if (!songsFolder.exists()) songsFolder.mkdirs();
        for (File file : songsFolder.listFiles()){
            if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nbs")){
                Song song = NBSDecoder.parse(file);
                if (song == null) continue;
                String n = getInternal(song);
                if (internalNames.containsKey(n)) {
                    getLogger().warning("Song \"" + n + "\" is duplicated. Please delete one from the songs directory. File name: " + file.getName());
                    continue;
                }
                fileNames.put(file.getName(), song);
                internalNames.put(n, song);
            }
        }
        getLogger().info(internalNames.size() + " songs loadeds. Sorting by name... ");
        List<String> names = new ArrayList<>(internalNames.keySet());
        Collections.sort(names, Collator.getInstance());
        for (String str : names){
            songs.add(internalNames.get(str));
        }

        setMaxPage();
        getLogger().info("Songs sorted ! " + songs.size() + " songs. Number of pages : " + maxPage);
        if (!songs.isEmpty()) playlist = new Playlist(songs.toArray(new Song[0]));

        /* --------------------------------------------- PLAYERS ------- */
        try {
            playersFile = new File(getDataFolder(), "datas.yml");
            playersFile.createNewFile();
            players = YamlConfiguration.loadConfiguration(playersFile);
            if (players.get("item") != null) jukeboxItem = ItemStack.deserialize(players.getConfigurationSection("item").getValues(false));
        }catch (IOException e) {
            e.printStackTrace();
        }
        if (db == null) {
            datas = new JukeBoxDatas(players.getMapList("players"), internalNames);
        }else {
            try {
                datas = new JukeBoxDatas(db);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void setMaxPage(){
        maxPage = (int) StrictMath.ceil(songs.size() * 1.0 / 45);
    }


    private YamlConfiguration loadLang() {
        String s = "en.yml";
        if (getConfig().getString("lang") != null) s = getConfig().getString("lang") + ".yml";
        File lang = new File(getDataFolder(), s);
        if (!lang.exists()) {
            try {
                getDataFolder().mkdir();
                lang.createNewFile();
                InputStream defConfigStream = this.getResource(s);
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8));
                    defConfig.save(lang);
                    Lang.loadFromConfig(defConfig);
                    getLogger().info("Created language file " + s);
                    return defConfig;
                }
            } catch(IOException e) {
                e.printStackTrace();
                getLogger().severe("Couldn't create language file.");
                getLogger().severe("This is a fatal error. Now disabling.");
                disable = true;
                this.setEnabled(false);
                return null;
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        try {
            Lang.saveFile(conf, lang);
        } catch(IOException | IllegalArgumentException | IllegalAccessException e) {
            getLogger().warning("Failed to save lang.yml.");
            getLogger().warning("Report this stack trace to SkytAsul on SpigotMC.");
            e.printStackTrace();
        }
        Lang.loadFromConfig(conf);
        getLogger().info("Loaded language file " + s);
        return conf;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        datas.joins(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        datas.quits(e.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if (e.getItem() == null) return;
        if (jukeboxItem != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)){
            if (e.getItem().equals(jukeboxItem)){
                CommandMusic.open(e.getPlayer());
                e.setCancelled(true);
                return;
            }
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && jukeboxClick){
            if (e.getClickedBlock().getType() == Material.JUKEBOX){
                String disc = e.getItem().getType().name();
                if (version < 13 ? JukeBoxInventory.discs8.contains(disc) : JukeBoxInventory.discs13.contains(disc)) {
                    CommandMusic.open(e.getPlayer());
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        if (!worlds) return;
        if (e.getFrom().getWorld() == e.getTo().getWorld()) return;
        if (worldsEnabled.contains(e.getTo().getWorld().getName())) return;
        PlayerData pdata = datas.getDatas(e.getPlayer());
        if (pdata == null) return;
        if (pdata.songPlayer != null) pdata.stopPlaying(true);
        if (pdata.getPlaylistType() == Playlists.RADIO) pdata.setPlaylist(Playlists.PLAYLIST, false);
    }
    private static Random random = new Random();
    public static Song randomSong() {
        if (songs.isEmpty()) return null;
        if (songs.size() == 1) return songs.get(0);
        return songs.get(random.nextInt(songs.size() - 1));
    }

    public static Playlist getPlaylist(){
        return playlist;
    }

    public static List<Song> getSongs(){
        return songs;
    }

    public static Song getSongByFile(String fileName){
        return fileNames.get(fileName);
    }

    public static Song getSongByInternalName(String internalName) {
        return internalNames.get(internalName);
    }

    public static String getInternal(Song s) {
        if (s.getTitle() == null || s.getTitle().isEmpty()) return s.getPath().getName();
        return s.getTitle();
    }

    public static String getItemName(Song s, Player p) {
        boolean admin = p.hasPermission("aetheriacore.music.adminItem");
        return format(admin ? itemFormatAdmin : itemFormat, admin ? itemFormatAdminWithoutAuthor : itemFormatWithoutAuthor, s);
    }

    public static String getSongName(Song song) {
        return format(songFormat, songFormatWithoutAuthor, song);
    }

    private static String removeFileExtension(String path) {
        int dot = path.lastIndexOf('.');
        if(dot == -1) return path;
        return path.substring(0, dot);
    }

    public static String format(String base, String noAuthorBase, Song song) {
        String name = song.getTitle().isEmpty() ? removeFileExtension(song.getPath().getName()) : song.getTitle();
        String author = song.getAuthor();
        String id = String.valueOf(songs.indexOf(song));
        if(author == null || author.isEmpty()) {
            return noAuthorBase.replace("{NAME}", name).replace("{ID}", id);
        }
        return base.replace("{NAME}", name).replace("{AUTHOR}", author).replace("{ID}", id);
    }

    public static boolean sendMessage(Player p, String msg){
        if (AetheriaCore.sendMessages){
            if (AetheriaCore.actionBar){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
            }else {
                p.spigot().sendMessage(TextComponent.fromLegacyText(msg));
            }
            return true;
        }
        return false;
    }
}