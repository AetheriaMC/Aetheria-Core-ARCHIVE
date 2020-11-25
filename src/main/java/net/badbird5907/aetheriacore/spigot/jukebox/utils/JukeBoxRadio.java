package net.badbird5907.aetheriacore.spigot.jukebox.utils;

import com.xxmicloxx.NoteBlockAPI.event.SongNextEvent;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JukeBoxRadio implements Listener {

    private final CustomSongPlayer songPlayer;
    private List<Player> listening = new ArrayList<>();

    public JukeBoxRadio(Playlist songs){
        Bukkit.getPluginManager().registerEvents(this, AetheriaCore.getInstance());
        songPlayer = new CustomSongPlayer(songs);
        songPlayer.playNextSong();
        songPlayer.setRandom(true);
        songPlayer.setAutoDestroy(false);
        songPlayer.setRepeatMode(RepeatMode.ALL);
        songPlayer.setPlaying(true);
    }

    @EventHandler
    public void onSongNext(SongNextEvent e){
        if (e.getSongPlayer() == songPlayer) {
            for (UUID id : songPlayer.getPlayerUUIDs()){
                Player p = Bukkit.getPlayer(id);
                if (p != null) Noteblock.sendMessage(p, Lang.MUSIC_PLAYING + " " + Noteblock.getSongName(e.getSongPlayer().getSong()));
            }
        }
    }

    public Song getSong() {
        return songPlayer.getSong();
    }

    public void join(Player p){
        songPlayer.addPlayer(p);
        listening.add(p);
        Noteblock.sendMessage(p, Lang.MUSIC_PLAYING + " " + Noteblock.getSongName(songPlayer.getSong()));
    }

    public void leave(Player p){
        songPlayer.removePlayer(p);
        listening.remove(p);
    }

    public boolean isListening(Player p) {
        return listening.contains(p);
    }

    public void stop(){
        for (UUID id : songPlayer.getPlayerUUIDs()){
            Player p = Bukkit.getPlayer(id);
            if (p != null) Noteblock.sendMessage(p, Lang.MUSIC_STOPPED);
        }
        songPlayer.destroy();
    }

}
