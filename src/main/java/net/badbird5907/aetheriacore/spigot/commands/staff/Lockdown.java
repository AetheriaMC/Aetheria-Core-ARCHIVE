package net.badbird5907.aetheriacore.spigot.commands.staff;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.util.KickAllNonStaff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Lockdown implements CommandExecutor {
    public static Boolean IsLockdown;
    public static String LockdownSeverity, Reason, Servers;
    public static UUID WhoCalled;
    private static String kickmsg = ChatColor.GOLD + "" + ChatColor.BOLD + "Aetheria\n \n" + ChatColor.RED + "This server is currently in lockdown mode.\n" + ChatColor.AQUA + "Reason: %reason%";
    //public static HashMap<String, String> IsLockdown = new HashMap<String, String>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            if(args.length == 2 ){
                if(args[0].equalsIgnoreCase("low") || args[0].equalsIgnoreCase("medium") || args[0].equalsIgnoreCase("high")) {
                    if(args[1].equalsIgnoreCase("here") || args[1].equalsIgnoreCase("server") || args[1].equalsIgnoreCase("network") || args[1].equalsIgnoreCase("global")){
                        if (sender.hasPermission(Permission.LOCKDOWN.node))
                            if(args[2].isEmpty())
                                LockDown((Player) sender, args[0], args[1], "not specified.");
                            else
                                LockDown((Player) sender, args[0], args[1], args[2] );
                        else
                            sender.sendMessage(permissionManager.PermissionMessage);
                    }
                }
                else
                    sender.sendMessage(ChatColor.RED + "Error: please use a lockdown level. \n" + ChatColor.RED + "Usage: /lockdown <low/medium/high> <server/network>");

            }
        }
        return true;
    }
    public static void LockDown(Player sender, String severity, String server_global, String reason){
        if(severity.equalsIgnoreCase("low"))
            LowLockdown(sender, server_global, reason);
        if(severity.equalsIgnoreCase("medium"))
            MedLockdown(sender, server_global, reason);
        if(severity.equalsIgnoreCase("high"))
            HighLockdown(sender, server_global, reason);
    }
    private static void LowLockdown(Player sender, String server_global, String reason){
        String kickmessage = kickmsg.replace("%reason%", reason);
        IsLockdown = true;
        LockdownSeverity = "LOW";
        Reason = reason;
        Servers = server_global;
        WhoCalled = sender.getUniqueId();
        KickAllNonStaff.KickAll(kickmessage);
        //IsLockdown.put("LOW", server_global.toUpperCase());
    }
    private static void MedLockdown(Player sender, String server_global, String reason){
        String kickmessage = kickmsg.replace("%reason%", reason);
        IsLockdown = true;
        LockdownSeverity = "MEDIUM";
        Reason = reason;
        Servers = server_global;
        WhoCalled = sender.getUniqueId();
        KickAllNonStaff.KickAll(kickmessage);
        //IsLockdown.put("MEDIUM", server_global.toUpperCase());
    }
    private static void HighLockdown(Player sender, String server_global, String reason){
        String kickmessage = kickmsg.replace("%reason%", reason);
        IsLockdown = true;
        LockdownSeverity = "HIGH";
        Reason = reason;
        Servers = server_global;
        WhoCalled = sender.getUniqueId();
        KickAllNonStaff.KickAll(kickmessage);
        //IsLockdown.put("HIGH", server_global.toUpperCase());
    }
}
