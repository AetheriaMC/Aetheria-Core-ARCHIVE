package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Hush  extends Command {
    public Hush() {
        super("hush", Permission.HUSH.node, new String[] { "dontshowstaffstuff" });
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(sender.hasPermission(Permission.HUSH.node)){
                if(AetheriaCoreBungee.Hush.contains(player.getUniqueId())){
                    AetheriaCoreBungee.Hush.remove(player.getUniqueId());
                    player.sendMessage(new TextComponent(log.prefix + ChatColor.WHITE + "Staff Alerts Toggled " + ChatColor.GREEN + "ON"));
                }
                else {
                    AetheriaCoreBungee.Hush.add(player.getUniqueId());
                    player.sendMessage(new TextComponent(log.prefix + ChatColor.WHITE + "Staff Alerts Toggled " + ChatColor.RED + "OFF"));
                }
            }
        }
    }
}
