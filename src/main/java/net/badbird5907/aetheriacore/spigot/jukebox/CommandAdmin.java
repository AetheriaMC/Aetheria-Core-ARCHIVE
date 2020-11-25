package net.badbird5907.aetheriacore.spigot.jukebox;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Playlists;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Files;

public class CommandAdmin implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		/*if (!(sender instanceof Player)){
			sender.sendMessage(Lang.PLAYER);
			return false;
		}
		Player p = (Player) sender;*/

        if (args.length == 0){
            sender.sendMessage(Lang.INCORRECT_SYNTAX);
            return true;
        }

        switch (args[0]){

            case "reload":
                sender.sendMessage(Lang.RELOAD_LAUNCH);
                try{
                    AetheriaCore.getInstance().disableAll();
                    AetheriaCore.getInstance().initAll();
                }catch (Exception ex){
                    sender.sendMessage("§cError while reloading. Please check the console   .");
                    ex.printStackTrace();
                }
                sender.sendMessage(Lang.RELOAD_FINISH);
                break;

            case "player":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                OfflinePlayer pp = Bukkit.getOfflinePlayer(args[1]);
                if (pp == null){
                    sender.sendMessage("§cUnknown player.");
                    return true;
                }
                PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(pp.getUniqueId());
                String s = Lang.MUSIC_PLAYING + " ";
                if (pdata == null){
                    s = s + "§cx";
                }else {
                    if (pdata.songPlayer == null){
                        s = s + "§cx";
                    }else {
                        Song song = pdata.songPlayer.getSong();
                        s = Noteblock.getSongName(song);
                    }
                }
                sender.sendMessage(s);
                break;

            case "play":
                if (args.length < 3){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        args[1] = p.getName();
                        String msg = play(args);
                        if (!msg.isEmpty()) sender.sendMessage(p.getName() + " : " + msg);
                    }
                }else {
                    String msg = play(args);
                    if (!msg.isEmpty()) sender.sendMessage(msg);
                }
                break;

            case "stop":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        sender.sendMessage(stop(p));
                    }
                }else {
                    Player cp = Bukkit.getPlayer(args[1]);
                    if (cp == null){
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    sender.sendMessage(stop(cp));
                }
                break;

            case "toggle":
                if (args.length < 2) {
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        toggle(p);
                    }
                }else {
                    Player cp = Bukkit.getPlayer(args[1]);
                    if (cp == null) {
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    toggle(cp);
                }
                break;

            case "setitem":
                if (!(sender instanceof Player)){
                    sender.sendMessage("§cYou have to be a player to do that.");
                    return true;
                }
                ItemStack is = ((Player) sender).getInventory().getItemInHand();
                if (is == null || is.getType() == Material.AIR){
                    AetheriaCore.getInstance().jukeboxItem = null;
                }else AetheriaCore.getInstance().jukeboxItem = is;
                sender.sendMessage("§aItem edited. Now : §2" + ((AetheriaCore.getInstance().jukeboxItem == null) ? "null" : AetheriaCore.getInstance().jukeboxItem.toString()));
                break;

            case "download":
                if (args.length < 3){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                try {
                    File file = new File(AetheriaCore.songsFolder, args[2] + ".nbs");
                    Files.copy(new URL(args[1]).openStream(), file.toPath());
                    boolean valid = true;
                    FileInputStream stream = new FileInputStream(file);
                    try{
                        Song song = NBSDecoder.parse(stream);
                        if (song == null) valid = false;
                    }catch (Throwable e){
                        valid = false;
                    }finally {
                        stream.close();
                        if (!valid) sender.sendMessage("§cDownloaded file is not a nbs song file.");
                    }
                    if (valid){
                        sender.sendMessage("§aSong downloaded. To add it to the list, you must reload the plugin. (§o/amusic reload§r§a)");
                    }else file.delete();
                } catch (Throwable e) {
                    sender.sendMessage("§cError when downloading file.");
                    e.printStackTrace();
                }
                break;

            case "shuffle":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        sender.sendMessage(p.getName() + " : " + shuffle(p));
                    }
                }else {
                    Player cp = Bukkit.getPlayer(args[1]);
                    if (cp == null){
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    sender.sendMessage(shuffle(cp));
                }
                break;

            case "particles":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        sender.sendMessage(p.getName() + " : " + particles(p));
                    }
                }else {
                    Player cp = Bukkit.getPlayer(args[1]);
                    if (cp == null){
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    sender.sendMessage(particles(cp));
                }
                break;

            case "join":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        sender.sendMessage(p.getName() + " : " + join(p));
                    }
                }else {
                    Player cp = Bukkit.getPlayer(args[1]);
                    if (cp == null){
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    sender.sendMessage(join(cp));
                }
                break;

            case "random":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        sender.sendMessage(p.getName() + " : " + random(p));
                    }
                }else {
                    Player cp = Bukkit.getPlayer(args[1]);
                    if (cp == null){
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    sender.sendMessage(random(cp));
                }
                break;

            case "volume":
                if (args.length < 3){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                Player cp = Bukkit.getPlayer(args[1]);
                if (cp == null){
                    sender.sendMessage("§cUnknown player.");
                    return true;
                }
                pdata = AetheriaCore.getInstance().datas.getDatas(cp);
                try{
                    int volume;
                    if (args[2].equals("+")) {
                        volume = pdata.getVolume() + 10;
                    }else if (args[2].equals("-")) {
                        volume = pdata.getVolume() - 10;
                    }else volume = Integer.parseInt(args[2]);
                    pdata.setVolume(volume);
                    sender.sendMessage("§aVolume : " + pdata.getVolume());
                }catch (NumberFormatException ex){
                    sender.sendMessage(Lang.INVALID_NUMBER);
                }
                break;

            case "loop":
                if (args.length < 2){
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        sender.sendMessage(p.getName() + " : " + loop(p));
                    }
                }else {
                    cp = Bukkit.getPlayer(args[1]);
                    if (cp == null){
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    sender.sendMessage(loop(cp));
                }
                break;

            case "next":
                if (args.length < 2) {
                    sender.sendMessage(Lang.INCORRECT_SYNTAX);
                    return true;
                }
                if (args[1].equals("@a")) {
                    int i = 0;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        AetheriaCore.getInstance().datas.getDatas(p).nextSong();
                        i++;
                    }
                    sender.sendMessage("§aNext song for " + i + "players.");
                }else {
                    cp = Bukkit.getPlayer(args[1]);
                    if (cp == null) {
                        sender.sendMessage("§cUnknown player.");
                        return true;
                    }
                    AetheriaCore.getInstance().datas.getDatas(cp).nextSong();
                    sender.sendMessage("§aNext song for " + cp.getName());
                }
                break;

            default:
                sender.sendMessage(Lang.AVAILABLE_COMMANDS + " <reload|player|play|stop|toggle|setitem|download|shuffle|particles|join|random|volume|loop|next> ...");
                break;

        }

        return true;
    }

    private String play(String[] args){
        Player cp = Bukkit.getPlayer(args[1]);
        if (cp == null) return "§cUnknown player.";
        if (Noteblock.worlds && !Noteblock.worldsEnabled.contains(cp.getWorld().getName())) return "§cMusic isn't enabled in the world the player is into.";
        Song song;
        try{
            int id = Integer.parseInt(args[2]);
            try{
                song = AetheriaCore.getSongs().get(id);
            }catch (IndexOutOfBoundsException ex){
                return "§cError on §l" + id + " §r§c(inexistant)";
            }
        }catch (NumberFormatException ex){
            String fileName = args[2];
            for (int i = 3; i < args.length; i++){
                fileName = fileName + args[i] + (i == args.length-1 ? "" : " ");
            }
            song = AetheriaCore.getSongByFile(fileName);
            if (song == null) return Lang.INVALID_NUMBER;
        }
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        pdata.setPlaylist(Playlists.PLAYLIST, false);
        pdata.playSong(song);
        pdata.songPlayer.adminPlayed = true;
        return "";
    }

    private String stop(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        pdata.stopPlaying(true);
        return Lang.PLAYER_MUSIC_STOPPED + cp.getName();
    }

    private void toggle(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        pdata.togglePlaying();
    }

    private String shuffle(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        return "§aShuffle: " + pdata.setShuffle(!pdata.isShuffle());
    }

    private String join(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        return "§aJoin: " + pdata.setJoinMusic(!pdata.hasJoinMusic());
    }

    private String particles(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        return "§aParticles: " + pdata.setParticles(!pdata.hasParticles());
    }

    private String loop(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        return "§aLoop: " + pdata.setRepeat(!pdata.isRepeatEnabled());
    }

    private String random(Player cp){
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(cp);
        Song song = pdata.playRandom();
        if (song == null) return "§aShuffle: §cnothing to play";
        return "§aShuffle: " + song.getTitle();
    }

}
