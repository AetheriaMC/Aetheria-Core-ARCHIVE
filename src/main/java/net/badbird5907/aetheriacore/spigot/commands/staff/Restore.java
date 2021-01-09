package net.badbird5907.aetheriacore.spigot.commands.staff;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

public class Restore implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender.hasPermission(Permission.RESTORE.node)) {
            if(args.length > 0){
                sender.sendMessage(ChatColor.GREEN + "Attempting...");
                //kick player if online
                try{
                    Player player = Bukkit.getPlayerExact(args[0]);
                    if(Bukkit.getOnlinePlayers().contains(player)) {
                        sender.sendMessage(ChatColor.GREEN + "Player is online. kicking from server.");
                        Player t = Bukkit.getPlayerExact(args[0]);
                        t.kickPlayer("Disconnected.");
                    }
                } catch (Exception e) {}
                //get offline player instance
                OfflinePlayer target = Bukkit.getOfflinePlayerIfCached(args[0]);
                //get data file
                String mainworld = Bukkit.getServer().getWorlds().get(0).getName();
                File backupfolder = new File(AetheriaCore.getInstance().getDataFolder() + "/pdata-backup/");
                if(!backupfolder.exists()){
                    sender.sendMessage(ChatColor.RED + "ERROR: backup folder does not exist! aborting!");
                    return true;
                }else{
                    File[] allbackups = backupfolder.listFiles();
                    ArrayList<File> allbackup = new ArrayList<>();
                    sender.sendMessage(ChatColor.GREEN + "Sorting through all backups there is for latest one.");
                    for (int i =0; i< allbackups.length; i++){
                        File f = allbackups[i];
                        if(f.getName().contains(target.getUniqueId() + ".DAT")){
                            allbackup.add(f);
                        }
                    }
                    File backupfile = getLastModified(allbackup);
                    sender.sendMessage(ChatColor.GREEN + "File identified as " + backupfile.getName());
                    File currentpdata = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/../../" + mainworld.replaceAll("_the_end", "").replaceAll("_nether", "") + "/playerdata/" + target.getUniqueId() + ".DAT");
                    if(currentpdata.exists()){
                        sender.sendMessage(ChatColor.GREEN + "Found existing pdata! overwriting");
                        currentpdata.delete();
                    }
                    try {
                        sender.sendMessage(ChatColor.GREEN + "Copying files over!");
                        copyFile(backupfile, currentpdata);
                    } catch (IOException e) {
                        e.printStackTrace();
                        sender.sendMessage(ChatColor.RED + "Uh Oh! something went wrong! please check console.");
                    }
                    sender.sendMessage(ChatColor.GREEN + "Done! If this isn't the right data file, you will need to go in manually and replace :/");
                }
            }
        }
        return true;
    }
    public static File getLastModified(ArrayList<File> files) {
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
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
}
