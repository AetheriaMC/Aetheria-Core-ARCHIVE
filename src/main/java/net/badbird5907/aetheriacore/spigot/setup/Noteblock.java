package net.badbird5907.aetheriacore.spigot.setup;

import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import net.badbird5907.aetheriacore.spigot.commands.utils.PlayMusic;
import net.badbird5907.aetheriacore.spigot.features.jukebox.CommandAdmin;
import net.badbird5907.aetheriacore.spigot.features.jukebox.CommandMusic;
import net.badbird5907.aetheriacore.spigot.features.jukebox.JukeBoxDatas;
import net.badbird5907.aetheriacore.spigot.features.jukebox.PlayerData;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Database;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.JukeBoxRadio;
import net.badbird5907.aetheriacore.spigot.util.TabComplete;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.Collator;
import java.util.*;

import static com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder.parse;
import static java.lang.StrictMath.ceil;
import static java.lang.String.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getVersionedClass;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang.loadFromConfig;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang.saveFile;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.MUSIC_ADMIN_ITEM;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.log;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.warn;
import static net.md_5.bungee.api.ChatMessageType.ACTION_BAR;
import static net.md_5.bungee.api.chat.TextComponent.fromLegacyText;
import static org.bukkit.Bukkit.*;
import static org.bukkit.Material.matchMaterial;
import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;
import static org.bukkit.inventory.ItemStack.deserialize;

public class Noteblock {
	private static final Random random = new Random();
	public static JukeBoxRadio radio = null;
	public static LinkedList<Song> songs;
	public static Map<String, Song> fileNames;
	public static Map<String, Song> internalNames;
	public static Map<String, Song> hiddenNames;
	public static Playlist playlist;
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
	//music
	public static int version = Integer.parseInt(getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1]);
	public static boolean disable = false;
	public static File playersFile;
	public static FileConfiguration players;
	public static File songsFolder;
	public static File hiddenFolder;
	public static ItemStack jukeboxItem;
	public static BukkitTask vanillaMusicTask = null;
	public static Database db;
	public static JukeBoxDatas datas;

	public static Song randomSong() {
		if (songs.isEmpty()) return null;
		if (songs.size() == 1) return songs.get(0);
		return songs.get(random.nextInt(songs.size() - 1));
	}

	public static Playlist getPlaylist() {
		return playlist;
	}

	public static Song getSongByInternalName(String internalName) {
		return internalNames.get(internalName);
	}

	public static String getInternal(Song s) {
		if ((s.getTitle() == null) || s.getTitle().isEmpty()) return s.getPath().getName();
		return s.getTitle();
	}

	public static String getItemName(Song s, Player p) {
		boolean admin = p.hasPermission(MUSIC_ADMIN_ITEM.node);
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
		String id = valueOf(songs.indexOf(song));
		return ((author == null) || author.isEmpty()) ? noAuthorBase.replace("{NAME}", name).replace("{ID}", id) : base.replace("{NAME}", name).replace("{AUTHOR}", author).replace("{ID}", id);
	}

	public static boolean sendMessage(Player p, String msg) {
		if (sendMessages) {
			if (actionBar) p.spigot().sendMessage(ACTION_BAR, fromLegacyText(msg));
			else p.spigot().sendMessage(fromLegacyText(msg));
			return true;
		}
		return false;
	}

	public static void finishEnabling() {

		requireNonNull(getInstance().getCommand("music")).setExecutor(new CommandMusic());
		requireNonNull(getInstance().getCommand("adminmusic")).setExecutor(new CommandAdmin());
		requireNonNull(getInstance().getCommand("adminmusic")).setTabCompleter(new TabComplete());
		requireNonNull(getInstance().getCommand("playmusic")).setExecutor(new PlayMusic());
		radioEnabled = radioEnabled && !songs.isEmpty();
		if (radioEnabled) radio = new JukeBoxRadio(playlist);
		else radioOnJoin = false;
		getOnlinePlayers().forEach(p -> datas.joins(p));
	}

	public static List<Song> getSongs() {
		return songs;
	}

	public static Song getSongByFile(String fileName) {
		return (fileName.contains(".nbs") || fileName.contains(".NBS")) ? fileNames.get(fileName) : fileNames.get(fileName + ".nbs");
	}

	public static void loadDatas() {
		/* --------------------------------------------- SONGS ------- */
		songs = new LinkedList<>();
		fileNames = new HashMap<>();
		internalNames = new HashMap<>();
		songsFolder = new File(getInstance().getDataFolder(), "songs");
		if (!songsFolder.exists()) songsFolder.mkdirs();
		stream(songsFolder.listFiles()).filter(file -> file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nbs")).forEach(file -> {
			Song song = parse(file);
			if (song == null) return;
			String n = getInternal(song);
			if (internalNames.containsKey(n)) {
				getInstance().getLogger().warning("Song \"" + n + "\" is duplicated. Please delete one from the songs directory. File name: " + file.getName());
				return;
			}
			fileNames.put(file.getName(), song);
			internalNames.put(n, song);
		});
		//TODO make sure this works
		hiddenFolder = new File(getInstance().getDataFolder(), "hiddensongs");
		if (!hiddenFolder.exists()) hiddenFolder.mkdirs();
		stream(hiddenFolder.listFiles()).filter(file -> file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nbs")).forEach(file -> {
			Song song = parse(file);
			if (song == null) return;
			String n = getInternal(song);
			if (hiddenNames.containsKey(n)) {
				warn("Error: song \"" + n + "\" is duplicated!");
				return;
			}
			hiddenNames.put(file.getName(), song);
			internalNames.put(n, song);
		});
		getInstance().getLogger().info(internalNames.size() + " songs loadeds. Sorting by name... ");
		List<String> names = new ArrayList<>(internalNames.keySet());
		names.sort(Collator.getInstance());
		names.forEach(str -> songs.add(internalNames.get(str)));
		setMaxPage();
		getInstance().getLogger().info("Songs sorted ! " + songs.size() + " songs. Number of pages : " + maxPage);
		if (!songs.isEmpty()) playlist = new Playlist(songs.toArray(new Song[0]));
		/* --------------------------------------------- PLAYERS ------- */
		try {
			playersFile = new File(getInstance().getDataFolder(), "datas.yml");
			playersFile.createNewFile();
			players = loadConfiguration(playersFile);
			if (players.get("item") != null)
				jukeboxItem = deserialize(requireNonNull(players.getConfigurationSection("item")).getValues(false));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (db == null) datas = new JukeBoxDatas(players.getMapList("players"), internalNames);
		else {
			try {
				datas = new JukeBoxDatas(db);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setMaxPage() {
		maxPage = (int) ceil((songs.size() * 1.0) / 45);
	}

	public static YamlConfiguration loadLang() {
		String s = "en.yml";
		if (getInstance().getConfig().getString("lang") != null)
			s = getInstance().getConfig().getString("lang") + ".yml";
		File lang = new File(getInstance().getDataFolder(), s);
		if (!lang.exists()) {
			try {
				getInstance().getDataFolder().mkdir();
				lang.createNewFile();
				InputStream defConfigStream = getInstance().getResource(s);
				if (defConfigStream != null) {
					YamlConfiguration defConfig = loadConfiguration(new InputStreamReader(defConfigStream, UTF_8));
					defConfig.save(lang);
					loadFromConfig(defConfig);
					getInstance().getLogger().info("Created language file " + s);
					return defConfig;
				}
			} catch (IOException e) {
				e.printStackTrace();
				getInstance().getLogger().severe("Couldn't create language file.");
				getInstance().getLogger().severe("This is a fatal error. Now disabling.");
				disable = true;
				getPluginManager().disablePlugin(getInstance());
				return null;
			}
		}
		YamlConfiguration conf = loadConfiguration(lang);
		try {
			saveFile(conf, lang);
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			getInstance().getLogger().warning("Failed to save lang.yml.");
			getInstance().getLogger().warning("Report this stack trace to SkytAsul on SpigotMC.");
			e.printStackTrace();
		}
		loadFromConfig(conf);
		getInstance().getLogger().info("Loaded language file " + s);
		return conf;
	}

	public static void initAll() {
		getInstance().reloadConfig();
		loadLang();
		if (disable) return;
		FileConfiguration config = getInstance().getConfig();
		jukeboxClick = config.getBoolean("jukeboxClick");
		sendMessages = config.getBoolean("sendMessages");
		async = config.getBoolean("asyncLoading");
		autoJoin = config.getBoolean("forceJoinMusic");
		defaultPlayer = PlayerData.deserialize(requireNonNull(config.getConfigurationSection("defaultPlayerOptions")).getValues(false), null);
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

		if (async) {
			new BukkitRunnable() {
				@Override
				public void run() {
					loadDatas();
					finishEnabling();
				}
			}.runTaskAsynchronously(getInstance());
		} else {
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
				getInstance().stopVanillaMusic = player -> {
					try {
						sendPacket.invoke(playerConnection.get(getHandle.invoke(player)), packet);
					} catch (ReflectiveOperationException e1) {
						e1.printStackTrace();
					}
				};
				vanillaMusicTask = getScheduler().runTaskTimerAsynchronously(getInstance(), () -> datas.getDatas().stream().filter(pdata -> pdata.isPlaying() && pdata.getPlayer() != null).forEach(pdata -> getInstance().stopVanillaMusic.accept(pdata.getPlayer())), 20L, 100l); // every 5 seconds
			} catch (ReflectiveOperationException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void DataFile() {
		log("Checking Data File");
		getInstance().customConfigFile = new File(getInstance().getDataFolder(), "data.yml");
		if (!getInstance().customConfigFile.exists()) {
			warn("Data file does not exist. Creating new file");
			getInstance().customConfigFile.getParentFile().mkdirs();
			getInstance().saveResource("data.yml", false);
		}
		getInstance().customConfig = new YamlConfiguration();
		try {
			getInstance().customConfig.load(getInstance().customConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		getInstance().getDataFile().addDefault("pvp", true);
		getInstance().getDataFile().addDefault("mutechatstatis", false);
	}

	public static void waitThenRun() {
		getScheduler().scheduleSyncDelayedTask(getInstance(), Noteblock::initAll, 20L); // 600L (ticks) is equal to 30 seconds (20 ticks = 1 second)
	}
}