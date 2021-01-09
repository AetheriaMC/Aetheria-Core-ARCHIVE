package net.badbird5907.aetheriacore.spigot.commands.staff.punish;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class punish implements CommandExecutor{
    PunishGUI punishGUI = new PunishGUI();
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(sender.hasPermission(Permission.PUNISH.node)){
                if(args.length == 1){
                    OfflinePlayer target = Bukkit.getOfflinePlayerIfCached(args[0]);
                    punishGUI.PunishGUI(player, target);
                }
            }
            return true;
        }
        return true;
    }
}
