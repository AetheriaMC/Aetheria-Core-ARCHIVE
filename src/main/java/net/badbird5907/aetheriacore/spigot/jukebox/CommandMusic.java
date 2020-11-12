package net.badbird5907.aetheriacore.spigot.jukebox;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMusic implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        DebugLogger.DebugLog("a1");
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.PLAYER);
            return true;
        }
        Player p = (Player) sender;
        DebugLogger.DebugLog("Attempting to open inventory for " + p.getDisplayName());
        open(p);

        return true;
    }

    public static void open(Player p) {
        if (AetheriaCore.worlds && !AetheriaCore.worldsEnabled.contains(p.getWorld().getName())) return;
        PlayerData pdata = AetheriaCore.getInstance().datas.getDatas(p);
        if (pdata == null) {
            p.sendMessage("§cLoading player... Try again!");
            return;
        }
        if (pdata.linked != null) {
            JukeBoxInventory inv = pdata.linked;
            inv.setSongsPage(p);
            inv.openInventory(p);
        } else {
            new JukeBoxInventory(p, pdata);
        }
    }
}

