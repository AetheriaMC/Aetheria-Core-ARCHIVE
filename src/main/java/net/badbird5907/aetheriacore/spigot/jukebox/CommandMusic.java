package net.badbird5907.aetheriacore.spigot.jukebox;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.jukebox.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMusic implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.PLAYER);
            return false;
        }

        Player p = (Player) sender;
        open(p);

        return false;
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
        } else new JukeBoxInventory(p, pdata);
    }
}

