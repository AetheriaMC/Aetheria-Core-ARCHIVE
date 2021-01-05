package net.badbird5907.aetheriacore.spigot.features.jukebox.utils;

import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.playmode.StereoMode;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import org.bukkit.entity.Player;

import static com.xxmicloxx.NoteBlockAPI.model.SoundCategory.RECORDS;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Particles.sendParticles;

public class CustomSongPlayer extends RadioSongPlayer {
	public boolean adminPlayed;
	private boolean particlesEnabled = false;

	public CustomSongPlayer(Playlist playlist) {
		super(playlist, RECORDS);
		try {
			super.setChannelMode(new StereoMode());
		} catch (Exception ex) {
			getInstance().getLogger().warning("It looks like you're using an old version of NoteBlockAPI. Please update as fast as possible to version 1.5.0 or greater.");
		}
	}

	public void setParticlesEnabled(boolean particles) {
		if (Noteblock.particles) this.particlesEnabled = particles;
	}

	@Override
	public void playTick(Player player, int tick) {
		super.playTick(player, tick);
		if (!particlesEnabled) return;
		if (this.song.getLayerHashMap().values().stream().anyMatch(layer -> layer.getNote(tick) != null))
			sendParticles(player);
	}
}