package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Data implements Serializable {
	private static transient final long serialVersionUID = -1681012206529286330L;
	public final HashMap<Location, String> blockSnapShot;
	public final HashSet<UUID> previouslyOnlinePlayers;

	// Can be used for saving
	public Data(HashMap<Location, String> blockSnapShot, HashSet<UUID> previouslyOnlinePlayers) {
		this.blockSnapShot = blockSnapShot;
		this.previouslyOnlinePlayers = previouslyOnlinePlayers;
	}

	// Can be used for loading
	public Data(Data loadedData) {
		this.blockSnapShot = loadedData.blockSnapShot;
		this.previouslyOnlinePlayers = loadedData.previouslyOnlinePlayers;
	}

	//save data
	public boolean saveData(String filePath) {
		try {
			BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
			out.writeObject(this);
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean loadData(String filePath) {
		try {
			try (BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)))) {
				Data data = (Data) in.readObject();
			}
			return true;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
