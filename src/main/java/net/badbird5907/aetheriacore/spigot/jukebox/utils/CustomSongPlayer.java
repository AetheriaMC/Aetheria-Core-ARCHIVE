package net.badbird5907.aetheriacore.spigot.jukebox.utils;

import com.xxmicloxx.NoteBlockAPI.model.Layer;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.SoundCategory;
import com.xxmicloxx.NoteBlockAPI.model.playmode.StereoMode;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.entity.Player;

public class CustomSongPlayer extends RadioSongPlayer{

    private boolean particlesEnabled = false;

    public boolean adminPlayed;

    public CustomSongPlayer(Playlist playlist){
        super(playlist, SoundCategory.RECORDS);
        try {
            super.setChannelMode(new StereoMode());
        }catch (Exception ex) {
            AetheriaCore.getInstance().getLogger().warning("It looks like you're using an old version of NoteBlockAPI. Please update as fast as possible to version 1.5.0 or greater.");
        }
    }

    public void setParticlesEnabled(boolean particles){
        if (AetheriaCore.particles) this.particlesEnabled = particles;
    }

    @Override
    public void playTick(Player player, int tick){
        super.playTick(player, tick);
        if (!particlesEnabled) return;
        for (Layer layer : this.song.getLayerHashMap().values()) {
            if (layer.getNote(tick) != null) {
                Particles.sendParticles(player);
                break;
            }
        }
    }

}