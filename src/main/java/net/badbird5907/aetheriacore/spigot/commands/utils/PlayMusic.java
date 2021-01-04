package net.badbird5907.aetheriacore.spigot.commands.utils;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import net.badbird5907.aetheriacore.spigot.features.jukebox.PlayerData;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Lang;
import net.badbird5907.aetheriacore.spigot.features.jukebox.utils.Playlists;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayMusic implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("BROADCAST")){
                String msg;
                for(Player p:Bukkit.getOnlinePlayers()) {
                    msg = play(p.getName(), args[1]);
                    if (!msg.isEmpty()) sender.sendMessage(msg);
                }
            }
            if(args[0].equalsIgnoreCase("local")){
                String msg = play(sender.getName(), allArgs);
                if(!msg.isEmpty()) sender.sendMessage(msg);
            }
            if(args[0].equalsIgnoreCase("player")){
                try{
                    StringBuilder sb1 = new StringBuilder();
                    for (int i = 2; i < args.length; i++){
                        sb1.append(args[i]).append(" ");
                    }
                    String Allargs2 = sb.toString().trim();
                    Player target = Bukkit.getPlayerExact(args[1]);
                    String msg = play(target.getName(), Allargs2);
                    if(!msg.isEmpty()) sender.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.RED + args[1] + " is not an player.");
                }
            }
            if(!args[0].equalsIgnoreCase("player") && args[0].equalsIgnoreCase("local") && args[0].equalsIgnoreCase("broadcast"))
                sender.sendMessage(ChatColor.RED + "Error: Usage: /playmusic <BROADCAST/LOCAL/PLAYER> <Player if PLAYER> <ID/FileName>");
        }
        else{
            sender.sendMessage(ChatColor.RED + "Error: Usage: /playmusic <BROADCAST/LOCAL/PLAYER> <Player if PLAYER> <ID/FileName>");
        }
        return true;
    }

    /**
     *
     * @param who,id1
     * @return String
     *
     */
    public static String play(String who, String id1){
        Player cp = Bukkit.getPlayer(who);
        if (cp == null) return "§cUnknown player.";
        Song song;
        try{
            //get by id
            int id = Integer.parseInt(id1);
            try{
                DebugLogger.DebugLog("Attempting to get " + id1 + " By ID.");
                song = Noteblock.getSongs().get(id);
            }catch (IndexOutOfBoundsException ex){
                DebugLogger.DebugLog(id1 + " Was not found.");
                return "§cError on §l" + id + " §r§c(inexistant)";
            }
        }catch (NumberFormatException ex){
            //get by file name
            DebugLogger.DebugLog("Attempting to get " + id1 + " By file.");
            song = Noteblock.getSongByFile(id1);
            if (song == null) {
                DebugLogger.DebugLog(id1 + " Was not found.");
                return Lang.INVALID_NUMBER;
            }
        }
        DebugLogger.DebugLog("a1");
        PlayerData pdata = Noteblock.datas.getDatas(cp);
        DebugLogger.DebugLog("a2");
        pdata.setPlaylist(Playlists.PLAYLIST, false);
        DebugLogger.DebugLog("a3");
        pdata.playSong(song);
        DebugLogger.DebugLog("a4");
        pdata.songPlayer.adminPlayed = true;
        return ChatColor.GREEN + "Playing...";
    }
}
