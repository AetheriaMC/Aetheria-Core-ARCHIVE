package net.badbird5907.aetheriacore.spigot.setup;

import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.jukebox.PlayerData;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.JukeBoxRadio;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Noteblock {
    public static JukeBoxRadio radio = null;
    public static LinkedList<Song> songs;
    public static Map<String, Song> fileNames;
    public static Map<String, Song> internalNames;
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
    private static Random random = new Random();

    public static AetheriaCore getInstance() {
        return AetheriaCore.instance;
    }

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
}
