package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Backup {
    public static void backup(String name, UUID uuid, File dest){
        if(!dest.exists()) dest.mkdirs();
        String mainworld = Bukkit.getServer().getWorlds().get(0).getName();
        File file = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/../../" + mainworld.replaceAll("_the_end", "").replaceAll("_nether", "") + "/playerdata/" + uuid + ".DAT");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd.HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        File backup = new File( dest.getAbsolutePath() + "/" + uuid.toString() + ".DAT." + time + ".1");
        if(backup.exists()){
            backup = getNext(uuid + ".DAT." + time + ".");
        }
        Boolean success;
        try{
            copyFile(file, backup);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
    private static File getNext(String name){
        int num = 1;
        while(true){
            num++;
            File backup1 = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/backups/playerdata/" + name +num);
            if(!backup1.exists()){
                return backup1;
            }
        }
    }
}
