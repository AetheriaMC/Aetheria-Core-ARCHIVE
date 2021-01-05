package net.badbird5907.aetheriacore.spigot.features.jukebox.utils;

import com.xxmicloxx.NoteBlockAPI.event.SongNextEvent;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.xxmicloxx.NoteBlockAPI.model.RepeatMode.ALL;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang.MUSIC_PLAYING;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang.MUSIC_STOPPED;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.getSongName;
import static net.badbird5907.aetheriacore.spigot.setup.Noteblock.sendMessage;
import static org.bukkit.Bukkit.getPluginManager;

public class JukeBoxRadio implements Listener {

	private final CustomSongPlayer songPlayer;
	private final List<Player> listening = new ArrayList<>();

    public JukeBoxRadio(Playlist songs){
	    getPluginManager().registerEvents(this, getInstance());
	    songPlayer = new CustomSongPlayer(songs);
        songPlayer.playNextSong();
        songPlayer.setRandom(true);
	    songPlayer.setAutoDestroy(false);
	    songPlayer.setRepeatMode(ALL);
	    songPlayer.setPlaying(true);
    }

    @EventHandler
    public void onSongNext(SongNextEvent e) {
	    if (e.getSongPlayer() == songPlayer)
		    songPlayer.getPlayerUUIDs().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> sendMessage(p, MUSIC_PLAYING + " " + getSongName(e.getSongPlayer().getSong())));
    }

    public Song getSong() {
        return songPlayer.getSong();
    }

    public void join(Player p){
        songPlayer.addPlayer(p);
	    listening.add(p);
	    sendMessage(p, MUSIC_PLAYING + " " + getSongName(songPlayer.getSong()));
    }

    public void leave(Player p){
        songPlayer.removePlayer(p);
        listening.remove(p);
    }

    public boolean isListening(Player p) {
        return listening.contains(p);
    }

    public void stop(){
	    songPlayer.getPlayerUUIDs().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(p -> sendMessage(p, MUSIC_STOPPED));
	    songPlayer.destroy();
    }
}