package net.badbird5907.aetheriacore.spigot;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import net.badbird5907.aetheriacore.spigot.events.*;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Placeholders;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import net.badbird5907.aetheriacore.spigot.other.Lag;
import net.badbird5907.aetheriacore.spigot.setup.SetupCommands;
import net.badbird5907.aetheriacore.spigot.setup.SetupEvents;
import net.badbird5907.aetheriacore.spigot.util.inventories.ClickListener;
import net.badbird5907.aetheriacore.utils.spigui.SpiGUI;
import net.dv8tion.jda.api.JDA;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Class.forName;
import static java.lang.System.*;
import static java.sql.DriverManager.getConnection;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.*;
import static net.badbird5907.aetheriacore.spigot.util.itemtypes.*;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getWorlds;
import static org.bukkit.Material.values;
import static org.bukkit.event.HandlerList.unregisterAll;

//import github.scarsz.discordsrv.DiscordSRV;

//import net.badbird5907.aetheriacore.spigot.util.SignGUI;

public final class AetheriaCore extends JavaPlugin implements Listener {
	public static AetheriaCore instance;
	public static List<String> SUPPORTED_VERSIONS = new ArrayList<String>();
	public static SpiGUI spiGUI;
	private static AetheriaCore plugin;
	private final HashMap<Plugin, Boolean> dependentPlugins = new HashMap<>();
	public File customConfigFile;
	public FileConfiguration customConfig;
	public Consumer<Player> stopVanillaMusic = null;
	public JDA jda;
	private LuckPerms luckPerms;
	//private final OnDiscordMessageRecieved discordsrvListener = new OnDiscordMessageRecieved(this);
	//sql
	private Connection connection;
	private String host, database, username, password;
	//music-end
	private int port;

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
		return forName(packageName + "." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + className);
	}

	@Override
	public void onEnable() {
		instance = this;
		long start = currentTimeMillis();
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
			DataFile();
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
			waitThenRun();
			//finished startup
			warn("Startup Finished! Took " + (currentTimeMillis() - start) + "ms.");
		} else {
			warn("Plugin Disabled because disabled in config.yml");
			warn("Enable plugin by changing enable: false to enable: true");
		}

	}

	@Override
	public void onDisable() {
		if (!disable) disableAll();
		getServer().getScheduler().cancelTasks(this);
		//DiscordSRV.api.unsubscribe(discordsrvListener);
		log("Killing All Custom Hostile Mobs. (as Colbite wanted)");
		// Iterate through every world on the server
		int removed_entities = 0;
		for (World w : getWorlds()) // Iterate through every entity in that world
			for (Entity e : w.getEntities())
				if (e.hasMetadata("Hostile_AI")) { //If Entity has custom Hostile AI as defined by MetaData, remove
					removed_entities++;
					e.remove();
				}
		log(removed_entities + " Custom Hostile Entites Removed.");
		log("Plugin Disabled.");
		warn("Baiwoo!!!");
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
		Bukkit.getLogger().info(prefix + string);
	}

	private void warn(final String string) {
		Bukkit.getLogger().warning(prefix + string);
	}

	private void setupDependencies() {

		if (getPluginManager().isPluginEnabled("SuperVanish")) log("SuperVanish Detected! Hooking into it.");
		if (getPluginManager().isPluginEnabled("PremiumVanish")) log("PremiumVanish Detected! Hooking into it.");
		if (getPluginManager().isPluginEnabled("AetheriaMinigames"))
			log("AetheriaMinigames Is Running On This Server!");
		if (getPluginManager().isPluginEnabled("AetheriaCheat")) log("AetheriaAntiCheat Is Running On This Server!");
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
			log(versionServer.equals(getConfig().getString("version")) ? "Version Up to date." : ("Please Update. Server responded with: " + versionServer));
		}
	}

	String getText(String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		//add headers to the connection, or check the status if desired..
		// handle error response code it occurs
		int responseCode = connection.getResponseCode();
		return new BufferedReader(new InputStreamReader(((200 <= responseCode) && (responseCode <= 299)) ? connection.getInputStream() : connection.getErrorStream())).lines().collect(joining());
	}

	public FileConfiguration getDataFile() {
		return this.customConfig;
	}

	public void SetupDatabase() {
		if (plugin.getConfig().getBoolean("enableDatabase", true)) {
			PluginManager.log("Setting Up Database");
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

		} else {
			warn("Database is not enabled. Plugin may not work as expected");
		}
	}

	public void openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) {
			return;
		}

		synchronized (this) {
			if (connection != null && !connection.isClosed()) return;
			forName("com.mysql.jdbc.Driver");
			connection = getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		}
	}

	private void doStuff() {
		addToAllItems();
		stream(values()).forEach(material -> {
			allitems.add(material.name());
			if (material.isBlock()) blocks.add(material.name());
			if (material.isItem()) items.add(material.name());
			if (material.toString().contains("SPAWN_EGG")) blacklisted_items.add(material);
		});
		stream(getServer().getPluginManager().getPlugins()).filter(pl -> pl.getDescription().getDepend().contains("AetheriaCore") || pl.getDescription().getSoftDepend().contains("AetheriaCore")).forEach(pl -> dependentPlugins.put(pl, false));
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "aetheriacore:messaging");
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

}