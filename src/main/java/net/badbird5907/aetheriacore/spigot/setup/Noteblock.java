package net.badbird5907.aetheriacore.spigot.setup;

import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.utils.PlayMusic;
import net.badbird5907.aetheriacore.spigot.jukebox.CommandAdmin;
import net.badbird5907.aetheriacore.spigot.jukebox.CommandMusic;
import net.badbird5907.aetheriacore.spigot.jukebox.JukeBoxDatas;
import net.badbird5907.aetheriacore.spigot.jukebox.PlayerData;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Database;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.JukeBoxRadio;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import net.badbird5907.aetheriacore.spigot.util.TabComplete;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
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
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.Collator;
import java.util.*;

public class Noteblock {
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
    public static int version = Integer.parseInt(Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1]);
    public static boolean disable = false;
    public static File playersFile;
    public static FileConfiguration players;
    public static File songsFolder;
    public static File hiddenFolder;
    public static ItemStack jukeboxItem;
    public static BukkitTask vanillaMusicTask = null;
    public static Database db;
    public static JukeBoxDatas datas;
    private static Random random = new Random();

    public static Song randomSong() {
        if (songs.isEmpty()) return null;
        if (songs.size() == 1) return songs.get(0);
        return songs.get(random.nextInt(songs.size() - 1));
    }

    public static Playlist getPlaylist(){
        return playlist;
    }

    public static Song getSongByInternalName(String internalName) {
        return internalNames.get(internalName);
    }

    public static String getInternal(Song s) {
        if (s.getTitle() == null || s.getTitle().isEmpty()) return s.getPath().getName();
        return s.getTitle();
    }

    public static String getItemName(Song s, Player p) {
        boolean admin = p.hasPermission(Permission.MUSIC_ADMIN_ITEM.node);
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
        if (sendMessages){
            if (actionBar){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
            }else {
                p.spigot().sendMessage(TextComponent.fromLegacyText(msg));
            }
            return true;
        }
        return false;
    }

    public static void finishEnabling(){

        AetheriaCore.getInstance().getCommand("music").setExecutor(new CommandMusic());
        AetheriaCore.getInstance().getCommand("adminmusic").setExecutor(new CommandAdmin());
        AetheriaCore.getInstance().getCommand("adminmusic").setTabCompleter(new TabComplete());
        AetheriaCore.getInstance().getCommand("playmusic").setExecutor(new PlayMusic());
        radioEnabled = radioEnabled && !songs.isEmpty();
        if (radioEnabled){
            radio = new JukeBoxRadio(playlist);
        }else radioOnJoin = false;

        for (Player p : Bukkit.getOnlinePlayers()) {
            datas.joins(p);
        }
    }

    public static List<Song> getSongs(){
        return songs;
    }

    public static Song getSongByFile(String fileName){
        if(fileName.contains(".nbs")){
            return fileNames.get(fileName);
        }
        if(fileName.contains(".NBS")){
            return fileNames.get(fileName);
        }
        return fileNames.get(fileName + ".nbs");
    }

    public static void loadDatas(){
        /* --------------------------------------------- SONGS ------- */
        songs = new LinkedList<>();
        fileNames = new HashMap<>();
        internalNames = new HashMap<>();
        songsFolder = new File(AetheriaCore.getInstance().getDataFolder(), "songs");
        if (!songsFolder.exists()) songsFolder.mkdirs();
        for (File file : songsFolder.listFiles()){
            if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nbs")){
                Song song = NBSDecoder.parse(file);
                if (song == null) continue;
                String n = getInternal(song);
                if (internalNames.containsKey(n)) {
                    AetheriaCore.getInstance().getLogger().warning("Song \"" + n + "\" is duplicated. Please delete one from the songs directory. File name: " + file.getName());
                    continue;
                }
                fileNames.put(file.getName(), song);
                internalNames.put(n, song);
            }
        }
        //TODO make sure this works
        hiddenFolder = new File(AetheriaCore.getInstance().getDataFolder(), "hiddensongs");
        if(!hiddenFolder.exists()) hiddenFolder.mkdirs();
        for(File file : hiddenFolder.listFiles()){
            if(file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nbs")){
                Song song = NBSDecoder.parse(file);
                if(song == null) continue;
                String n = getInternal(song);
                if(hiddenNames.containsKey(n)) {
                    PluginManager.warn("Error: song \"" + n + "\" is duplicated!");
                    continue;
                }
                hiddenNames.put(file.getName(), song);
                internalNames.put(n, song);
            }
        }
        AetheriaCore.getInstance().getLogger().info(internalNames.size() + " songs loadeds. Sorting by name... ");
        List<String> names = new ArrayList<>(internalNames.keySet());
        Collections.sort(names, Collator.getInstance());
        for (String str : names){
            songs.add(internalNames.get(str));
        }

        setMaxPage();
        AetheriaCore.getInstance().getLogger().info("Songs sorted ! " + songs.size() + " songs. Number of pages : " + maxPage);
        if (!songs.isEmpty()) playlist = new Playlist(songs.toArray(new Song[0]));

        /* --------------------------------------------- PLAYERS ------- */
        try {
            playersFile = new File(AetheriaCore.getInstance().getDataFolder(), "datas.yml");
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

    public static void setMaxPage(){
        maxPage = (int) StrictMath.ceil(songs.size() * 1.0 / 45);
    }

    public static YamlConfiguration loadLang() {
        String s = "en.yml";
        if (AetheriaCore.getInstance().getConfig().getString("lang") != null) s = AetheriaCore.getInstance().getConfig().getString("lang") + ".yml";
        File lang = new File(AetheriaCore.getInstance().getDataFolder(), s);
        if (!lang.exists()) {
            try {
                AetheriaCore.getInstance().getDataFolder().mkdir();
                lang.createNewFile();
                InputStream defConfigStream = AetheriaCore.getInstance().getResource(s);
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8));
                    defConfig.save(lang);
                    Lang.loadFromConfig(defConfig);
                    AetheriaCore.getInstance().getLogger().info("Created language file " + s);
                    return defConfig;
                }
            } catch(IOException e) {
                e.printStackTrace();
                AetheriaCore.getInstance().getLogger().severe("Couldn't create language file.");
                AetheriaCore.getInstance().getLogger().severe("This is a fatal error. Now disabling.");
                disable = true;
                Bukkit.getPluginManager().disablePlugin(AetheriaCore.getInstance());
                return null;
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        try {
            Lang.saveFile(conf, lang);
        } catch(IOException | IllegalArgumentException | IllegalAccessException e) {
            AetheriaCore.getInstance().getLogger().warning("Failed to save lang.yml.");
            AetheriaCore.getInstance().getLogger().warning("Report this stack trace to SkytAsul on SpigotMC.");
            e.printStackTrace();
        }
        Lang.loadFromConfig(conf);
        AetheriaCore.getInstance().getLogger().info("Loaded language file " + s);
        return conf;
    }

    public static void initAll(){
        AetheriaCore.getInstance().reloadConfig();

        loadLang();
        if (disable) return;

        FileConfiguration config = AetheriaCore.getInstance().getConfig();
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
            }.runTaskAsynchronously(AetheriaCore.getInstance());
        }else{
            loadDatas();
            finishEnabling();
        }

        if (preventVanillaMusic) {
            try {
                String nms = "net.minecraft.server";
                String cb = "org.bukkit.craftbukkit";
                Method getHandle = AetheriaCore.getInstance().getVersionedClass(cb, "entity.CraftPlayer").getDeclaredMethod("getHandle");
                Field playerConnection = AetheriaCore.getInstance().getVersionedClass(nms, "EntityPlayer").getDeclaredField("playerConnection");
                Method sendPacket = AetheriaCore.getInstance().getVersionedClass(nms, "PlayerConnection").getDeclaredMethod("sendPacket", AetheriaCore.getInstance().getVersionedClass(nms, "Packet"));
                Class<?> soundCategory = AetheriaCore.getInstance().getVersionedClass(nms, "SoundCategory");
                Object packet = AetheriaCore.getInstance().getVersionedClass(nms, "PacketPlayOutStopSound").getDeclaredConstructor(AetheriaCore.getInstance().getVersionedClass(nms, "MinecraftKey"), soundCategory).newInstance(null, soundCategory.getDeclaredField("MUSIC").get(null));

                AetheriaCore.getInstance().stopVanillaMusic = player -> {
                    try {
                        sendPacket.invoke(playerConnection.get(getHandle.invoke(player)), packet);
                    }catch (ReflectiveOperationException e1) {
                        e1.printStackTrace();
                    }
                };

                vanillaMusicTask = Bukkit.getScheduler().runTaskTimerAsynchronously(AetheriaCore.getInstance(), () -> {
                    for (PlayerData pdata : datas.getDatas()) {
                        if (pdata.isPlaying() && pdata.getPlayer() != null) AetheriaCore.getInstance().stopVanillaMusic.accept(pdata.getPlayer());
                    }
                }, 20L, 100l); // every 5 seconds
            }catch (ReflectiveOperationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void DataFile() {
        PluginManager.log("Checking Data File");
        AetheriaCore.getInstance().customConfigFile = new File(AetheriaCore.getInstance().getDataFolder(), "data.yml");
        if (!AetheriaCore.getInstance().customConfigFile.exists()) {
            PluginManager.warn("Data file does not exist. Creating new file");
            AetheriaCore.getInstance().customConfigFile.getParentFile().mkdirs();
            AetheriaCore.getInstance().saveResource("data.yml", false);
        }

        AetheriaCore.getInstance().customConfig = new YamlConfiguration();
        try {
            AetheriaCore.getInstance().customConfig.load(AetheriaCore.getInstance().customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        AetheriaCore.getInstance().getDataFile().addDefault("pvp", true);
        AetheriaCore.getInstance().getDataFile().addDefault("mutechatstatis", false);

    }

    public static void waitThenRun() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(AetheriaCore.getInstance(), new Runnable() {
            public void run() {
                initAll();
            }
        }, 20L); // 600L (ticks) is equal to 30 seconds (20 ticks = 1 second)
        return;
    }
}
