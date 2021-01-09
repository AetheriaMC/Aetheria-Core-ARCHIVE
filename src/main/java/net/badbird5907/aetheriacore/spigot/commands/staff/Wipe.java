package net.badbird5907.aetheriacore.spigot.commands.staff;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Wipe implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender.hasPermission(Permission.WIPE.node)){
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
                File file = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/../../" + mainworld.replaceAll("_the_end", "").replaceAll("_nether", "") + "/playerdata/" + target.getUniqueId() + ".DAT");
                DebugLogger.DebugLog("pdata file: " + file.getAbsolutePath());
                File backup_folder = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/pdata-backup");
                DebugLogger.DebugLog("Backup folder path: " + backup_folder.getAbsolutePath());
                if(!backup_folder.exists())
                    backup_folder.mkdirs();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd.HH-mm-ss");
                LocalDateTime now = LocalDateTime.now();
                String time = dtf.format(now);
                File backup = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/pdata-backup/" + target.getUniqueId() + ".DAT." + time + ".1");
                if(backup.exists()){
                    backup = getNext(target.getUniqueId() + ".DAT." + time + ".");
                }
                Boolean success;
                try {
                    sender.sendMessage(ChatColor.GREEN + "Backing up pdata");
                    copyFile(file, backup);
                    success = true;

                } catch (IOException e) {
                    success = false;
                    e.printStackTrace();
                }
                if(success) {
                    sender.sendMessage(ChatColor.GREEN + "deleting pdata");
                    file.delete();
                }
                else if(args[1].equalsIgnoreCase("--force"))
                    file.delete();
                else if(!args[1].equalsIgnoreCase("--force")){
                    sender.sendMessage(ChatColor.RED + "There was a error trying to backup the file! aborted. try using the --force option next time (will not back up)");

                }
                sender.sendMessage(ChatColor.GREEN +  "finished executing!");
            }
        }else throw new NoPermsError((Player)sender, "wipe");
        return true;
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
            File backup1 = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/pdata-backup/" + name +num);
            if(!backup1.exists()){
                return backup1;
            }
        }
    }
}
