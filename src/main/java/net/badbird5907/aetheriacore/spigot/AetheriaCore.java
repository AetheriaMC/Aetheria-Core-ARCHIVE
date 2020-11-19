package net.badbird5907.aetheriacore.spigot;

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
import net.badbird5907.aetheriacore.spigot.jukebox.CommandAdmin;
import net.badbird5907.aetheriacore.spigot.jukebox.CommandMusic;
import net.badbird5907.aetheriacore.spigot.jukebox.JukeBoxDatas;
import net.badbird5907.aetheriacore.spigot.jukebox.PlayerData;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Database;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.JukeBoxRadio;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Placeholders;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.other.Lag;
import net.badbird5907.aetheriacore.spigot.setup.SetupCommands;
import net.badbird5907.aetheriacore.spigot.setup.SetupEvents;
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
import org.bukkit.event.Listener;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.Collator;
import java.util.*;
import java.util.function.Consumer;

import static java.lang.Class.forName;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.spigot.jukebox.CommandMusic.open;
import static net.badbird5907.aetheriacore.spigot.jukebox.JukeBoxInventory.discs13;
import static net.badbird5907.aetheriacore.spigot.jukebox.JukeBoxInventory.discs8;
import static net.badbird5907.aetheriacore.spigot.jukebox.PlayerData.deserialize;
import static net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang.loadFromConfig;
import static net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang.saveFile;
import static net.badbird5907.aetheriacore.spigot.jukebox.utils.Playlists.PLAYLIST;
import static net.badbird5907.aetheriacore.spigot.jukebox.utils.Playlists.RADIO;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.JUKEBOX;
import static org.bukkit.Material.matchMaterial;
import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;
import static org.bukkit.event.HandlerList.unregisterAll;
import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;
import static org.bukkit.inventory.ItemStack.deserialize;

//import net.badbird5907.aetheriacore.spigot.util.SignGUI;

public final class AetheriaCore extends JavaPlugin implements Listener {
	public static final List<String> SUPPORTED_VERSIONS = new ArrayList<>();
	//protocolib
	//private ProtocolManager protocolManager;
	//SignGUI signGui;
	public static AetheriaCore instance;
	//music
	public static final int version = Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1]);
	public static FileConfiguration players;
	public static File songsFolder;
	public static JukeBoxRadio radio = null;
	public static Map<String, Song> internalNames;
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
	private static AetheriaCore plugin;
	private static File playersFile;
	private static LinkedList<Song> songs;
	private static Map<String, Song> fileNames;
	private static Playlist playlist;
	private static final Random random = new Random();
	public File customConfigFile;
	public FileConfiguration customConfig;
	public ItemStack jukeboxItem;
	public JukeBoxDatas datas;
	public Consumer<Player> stopVanillaMusic = null;
	private final OnDiscordMessageRecieved discordsrvListener = new OnDiscordMessageRecieved(this);
	//sql
	private Connection connection;
	private String host, database, username, password;
	private int port;
	private boolean disable = false;
	private Database db;
	private BukkitTask vanillaMusicTask = null;

	public AetheriaCore() {
		instance = this;
	}
	//music-end

	public static AetheriaCore getInstance() {
		return instance;
	}

	public static Song randomSong() {
		if (songs.isEmpty()) return null;
		if (songs.size() == 1) return songs.get(0);
		return songs.get(random.nextInt(songs.size() - 1));
	}

	public static Playlist getPlaylist() {
		return playlist;
	}

	public static List<Song> getSongs() {
		return songs;
	}

	public static Song getSongByFile(String fileName) {
		if (fileName.contains(".nbs")) {
			return fileNames.get(fileName);
		}
		if (fileName.contains(".NBS")) {
			return fileNames.get(fileName);
		}
		return fileNames.get(fileName + ".nbs");
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
		if (dot == -1) return path;
		return path.substring(0, dot);
	}

	public static String format(String base, String noAuthorBase, Song song) {
		String name = song.getTitle().isEmpty() ? removeFileExtension(song.getPath().getName()) : song.getTitle();
		String author = song.getAuthor();
		String id = String.valueOf(songs.indexOf(song));
		if (author == null || author.isEmpty()) {
			return noAuthorBase.replace("{NAME}", name).replace("{ID}", id);
		}
		return base.replace("{NAME}", name).replace("{AUTHOR}", author).replace("{ID}", id);
	}

	public static boolean sendMessage(Player p, String msg) {
		if (AetheriaCore.sendMessages) {
			if (AetheriaCore.actionBar) {
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
			} else {
				p.spigot().sendMessage(TextComponent.fromLegacyText(msg));
			}
			return true;
		}
		return false;
	}

	@Override
	public void onEnable() {
		instance = this;
		if (getConfig().getBoolean("enable")) {
			//signGui = new SignGUI(this);
			boolean mc1164 = Bukkit.getServer().getClass().getPackage().getName().contains("1.16.4");
			if (!mc1164)
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
			DataFile();
			this.setupConfig();
			log("Startup: Config Loaded!!");

			//load DB
			SetupDatabase();

			log("Setting Up Dependencies");
			setupDependencies();
			//protocolManager = ProtocolLibrary.getProtocolManager();
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
		for (World w : getWorlds()) {

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
		requireNonNull(getCommand("aetheriacore")).setExecutor(new aetheriacore(this));
		requireNonNull(getCommand("aetheriacore")).setTabCompleter(new TabComplete());
		requireNonNull(getCommand("invis")).setExecutor(new invis());
		requireNonNull(getCommand("clearchat")).setExecutor(new clearchat(this));
		requireNonNull(getCommand("rules")).setExecutor(new rules());
		requireNonNull(getCommand("performance")).setExecutor(new performance());
		requireNonNull(getCommand("itemblacklist")).setExecutor(new itemblacklist());
		//getCommand("queuerestart").setExecutor(new queuerestart(this));
		//getCommand("levitate").setExecutor(new levitate());
		requireNonNull(getCommand("dupethis")).setExecutor(new DupeThis());
		requireNonNull(getCommand("opme")).setExecutor(new opme());
		requireNonNull(getCommand("getuuid")).setExecutor(new getUUID());
		requireNonNull(getCommand("staffchat")).setExecutor(new staffchat());
		requireNonNull(getCommand("staffmode")).setExecutor(new StaffMode());
		requireNonNull(getCommand("hush")).setExecutor(new hush());
		requireNonNull(getCommand("QuickChat")).setExecutor(new QuickChat(this));
		requireNonNull(getCommand("ClearFloorDrops")).setExecutor(new ClearFloorDrops());
		requireNonNull(getCommand("SudoOp")).setExecutor(new SudoOpPlaceholder());
		requireNonNull(getCommand("freeze")).setExecutor(new freezePlayer());
		requireNonNull(getCommand("unfreeze")).setExecutor(new Unfreeze());
		requireNonNull(getCommand("nightvision")).setExecutor(new NightVision());
		requireNonNull(getCommand("togglePVP")).setExecutor(new togglePvp(this));
		requireNonNull(getCommand("CreateNPC")).setExecutor(new CreateNPC());
		requireNonNull(getCommand("killall")).setExecutor(new KillAll());
		requireNonNull(getCommand("link")).setExecutor(new link());
		requireNonNull(getCommand("masssay")).setExecutor(new MassSay());
		requireNonNull(getCommand("getclientbrand")).setExecutor(new GetClientBrand());
		requireNonNull(getCommand("getviewdistance")).setExecutor(new GetViewDist());
		requireNonNull(getCommand("item")).setExecutor(new item());
		requireNonNull(getCommand("item")).setTabCompleter(new TabComplete());
		requireNonNull(getCommand("itemmenu")).setExecutor(new itemmenu());
		requireNonNull(getCommand("broadcast")).setExecutor(new Broadcast());
		requireNonNull(getCommand("mutechat")).setExecutor(new mutechat(this));
		requireNonNull(getCommand("kickallnonstaff")).setExecutor(new KickAllNonStaff());
		requireNonNull(getCommand("lockdown")).setExecutor(new Lockdown());
		requireNonNull(getCommand("shopkeeper")).setExecutor(new GuiMaker());
		requireNonNull(getCommand("loop")).setExecutor(new Loop());

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
		//unused rn. check the class SetupEvents
		if (getConfig().getBoolean("enablelegacyblacklistitems", true))
			getServer().getPluginManager().registerEvents(new InventoryOpenEvent(), this);
		if (getConfig().getBoolean("enablechatfilter")) {

		}
		if (getConfig().getBoolean("disable-enderman-pickup", true))
			getServer().getPluginManager().registerEvents(new onEndermanPickup(this), this);
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
		if (getPluginManager().isPluginEnabled("SuperVanish")) log("SuperVanish Detected! Hooking into it.");
		if (getPluginManager().isPluginEnabled("PremiumVanish")) log("PremiumVanish Detected! Hooking into it.");
		if (getPluginManager().isPluginEnabled("AetheriaMinigames"))
			log("AetheriaMinigames Is Running On This Server!");
		if (getPluginManager().isPluginEnabled("AetheriaCheat")) log("AetheriaAntiCheat Is Running On This Server!");
		LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);

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
			String versionServer = getText();
			log(Objects.equals(versionServer, getConfig().getString("version")) ? "Version Up to date." : "Please Update. Server responded with: " + versionServer);
		}
	}

	String getText() throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL("https://badbird5907.net/api/aetheriacore/version").openConnection();
		//add headers to the connection, or check the status if desired..

		// handle error response code it occurs
		int responseCode = connection.getResponseCode();
		InputStream inputStream = ((200 <= responseCode) && (responseCode <= 299)) ? connection.getInputStream() : connection.getErrorStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String response = in.lines().collect(joining());
		in.close();
		return response;
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

	public void SetupDatabase() {
		if (plugin.getConfig().getBoolean("enableDatabase", true)) {
			log("Setting Up Database");
			port = plugin.getConfig().getBoolean("Custom-DB-port") ? plugin.getConfig().getInt("Database-port") : 3306;

			host = plugin.getConfig().getString("Database-Url");
			database = plugin.getConfig().getString("Database-Name");
			username = plugin.getConfig().getString("Database-Username");
			password = plugin.getConfig().getString("Database-Password");
			try {
				openConnection();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else warn("Database is not enabled. Plugin may not work as expected");
	}

	public void openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) return;
		synchronized (this) {
			if ((connection != null) && !connection.isClosed()) return;
			forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		}
	}

	private void doStuff() {
		itemtypes.addToAllItems();
		for (Material material : Material.values()) {
			itemtypes.allitems.add(material.name());
			if (material.isBlock()) itemtypes.blocks.add(material.name());
			if (material.isItem()) itemtypes.items.add(material.name());
			if (material.toString().contains("SPAWN_EGG")) itemtypes.blacklisted_items.add(material);
		}
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "aetheriacore:messaging");
	}

	public void waitThenRun() {
		getScheduler().scheduleSyncDelayedTask(plugin, this::initAll, 20L); // 600L (ticks) is equal to 30 seconds (20 ticks = 1 second)
	}

	public void disableAll() {
		if (radio != null) {
			radio.stop();
			radio = null;
		}
		if (datas != null) {
			if (savePlayerDatas && db == null) players.set("players", datas.getSerializedList());
			players.set("item", (jukeboxItem == null) ? null : jukeboxItem.serialize());
			try {
				players.save(playersFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (vanillaMusicTask != null) vanillaMusicTask.cancel();
		unregisterAll((JavaPlugin) this);
	}

	public void initAll() {
		reloadConfig();

		loadLang();
		if (disable) return;

		FileConfiguration config = getConfig();
		jukeboxClick = config.getBoolean("jukeboxClick");
		sendMessages = config.getBoolean("sendMessages");
		async = config.getBoolean("asyncLoading");
		autoJoin = config.getBoolean("forceJoinMusic");
		defaultPlayer = deserialize(requireNonNull(config.getConfigurationSection("defaultPlayerOptions")).getValues(false), null);
		particles = config.getBoolean("noteParticles") && version >= 9;
		actionBar = config.getBoolean("actionBar") && version >= 9;
		radioEnabled = config.getBoolean("radio");
		radioOnJoin = radioEnabled && config.getBoolean("radioOnJoin");
		autoReload = config.getBoolean("reloadOnJoin");
		preventVanillaMusic = config.getBoolean("preventVanillaMusic") && version >= 13;
		songItem = matchMaterial(requireNonNull(config.getString("songItem")));
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
		if (async) new BukkitRunnable() {
			@Override
			public void run() {
				loadDatas();
				finishEnabling();
			}
		}.runTaskAsynchronously(this);
		else {
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
					} catch (ReflectiveOperationException e1) {
						e1.printStackTrace();
					}
				};
				vanillaMusicTask = getScheduler().runTaskTimerAsynchronously(this, () -> datas.getDatas().stream().filter(pdata -> pdata.isPlaying() && pdata.getPlayer() != null).forEach(pdata -> stopVanillaMusic.accept(pdata.getPlayer())), 20L, 100L); // every 5 seconds
			} catch (ReflectiveOperationException ex) {
				ex.printStackTrace();
			}
		}
	}

	private Class<?> getVersionedClass(String packageName, String className) throws ClassNotFoundException {
		return forName(packageName + "." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + className);
	}

	private void finishEnabling() {
		requireNonNull(getCommand("music")).setExecutor(new CommandMusic());
		requireNonNull(getCommand("adminmusic")).setExecutor(new CommandAdmin());
		requireNonNull(getCommand("adminmusic")).setTabCompleter(new TabComplete());
		requireNonNull(getCommand("playmusic")).setExecutor(new PlayMusic());
		getServer().getPluginManager().registerEvents(this, this);
		radioEnabled = radioEnabled && !songs.isEmpty();
		if (radioEnabled) radio = new JukeBoxRadio(playlist);
		else radioOnJoin = false;
		for (Player p : getOnlinePlayers()) datas.joins(p);
	}

	private void loadDatas() {
		/* --------------------------------------------- SONGS ------- */
		songs = new LinkedList<>();
		fileNames = new HashMap<>();
		internalNames = new HashMap<>();
		songsFolder = new File(getDataFolder(), "songs");
		if (!songsFolder.exists()) songsFolder.mkdirs();
		for (File file : songsFolder.listFiles())
			if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nbs")) {
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
		getLogger().info(internalNames.size() + " songs loadeds. Sorting by name... ");
		List<String> names = new ArrayList<>(internalNames.keySet());
		names.sort(Collator.getInstance());
		names.forEach(str -> songs.add(internalNames.get(str)));
		setMaxPage();
		getLogger().info("Songs sorted ! " + songs.size() + " songs. Number of pages : " + maxPage);
		if (!songs.isEmpty()) playlist = new Playlist(songs.toArray(new Song[0]));

		/* --------------------------------------------- PLAYERS ------- */
		try {
			playersFile = new File(getDataFolder(), "datas.yml");
			playersFile.createNewFile();
			players = loadConfiguration(playersFile);
			if (players.get("item") != null)
				jukeboxItem = deserialize(requireNonNull(players.getConfigurationSection("item")).getValues(false));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (db == null) datas = new JukeBoxDatas(players.getMapList("players"), internalNames);
		else try {
			datas = new JukeBoxDatas(db);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void setMaxPage() {
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
					YamlConfiguration defConfig = loadConfiguration(new InputStreamReader(defConfigStream, UTF_8));
					defConfig.save(lang);
					loadFromConfig(defConfig);
					getLogger().info("Created language file " + s);
					return defConfig;
				}
			} catch (IOException e) {
				e.printStackTrace();
				getLogger().severe("Couldn't create language file.");
				getLogger().severe("This is a fatal error. Now disabling.");
				disable = true;
				this.setEnabled(false);
				return null;
			}
		}
		YamlConfiguration conf = loadConfiguration(lang);
		try {
			saveFile(conf, lang);
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			getLogger().warning("Failed to save lang.yml.");
			getLogger().warning("Report this stack trace to SkytAsul on SpigotMC.");
			e.printStackTrace();
		}
		loadFromConfig(conf);
		getLogger().info("Loaded language file " + s);
		return conf;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		datas.joins(e.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		datas.quits(e.getPlayer());
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() == null) return;
		if ((jukeboxItem != null) && ((e.getAction() == RIGHT_CLICK_AIR) || (e.getAction() == RIGHT_CLICK_BLOCK)) && e.getItem().equals(jukeboxItem)) {
			open(e.getPlayer());
			e.setCancelled(true);
			return;
		}
		if (e.getAction() == RIGHT_CLICK_BLOCK && jukeboxClick && requireNonNull(e.getClickedBlock()).getType() == JUKEBOX) {
			String disc = e.getItem().getType().name();
			if ((version < 13) ? discs8.contains(disc) : discs13.contains(disc)) {
				open(e.getPlayer());
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if (!worlds) return;
		if (e.getFrom().getWorld() == requireNonNull(e.getTo()).getWorld()) return;
		if (worldsEnabled.contains(requireNonNull(e.getTo().getWorld()).getName())) return;
		PlayerData pdata = datas.getDatas(e.getPlayer());
		if (pdata == null) return;
		if (pdata.songPlayer != null) pdata.stopPlaying(true);
		if (pdata.getPlaylistType() == RADIO) pdata.setPlaylist(PLAYLIST, false);
	}
}