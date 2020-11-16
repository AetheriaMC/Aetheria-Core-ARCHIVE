package net.badbird5907.aetheriacore.bungee.commands.staff;


import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChat extends Command {

    public StaffChat() {
        super("sc", Permission.STAFF_CHAT.node, new String[] { "staffchat" });
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0) {
                if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                    if(AetheriaCoreBungee.AdminChatPlayers.contains(p.getUniqueId())){
                        AetheriaCoreBungee.AdminChatPlayers.remove(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.GREEN + "You had admin chat on so it was toggled off!"));
                    }
                    if(AetheriaCoreBungee.Hush.contains(p.getUniqueId())) {
                        p.sendMessage(new TextComponent(ChatColor.RED + "You have hush currently enabled. please disable it with /hush to turn on Staff Chat"));
                        return;
                    }
                    if (AetheriaCoreBungee.StaffChatPlayers.contains(p.getUniqueId())) {
                        p.sendMessage(new TextComponent(log.prefix + ChatColor.WHITE + "StaffChat turned " + ChatColor.RED + "OFF"));
                        AetheriaCoreBungee.StaffChatPlayers.remove(p.getUniqueId());
                    } else {
                        AetheriaCoreBungee.StaffChatPlayers.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(log.prefix + ChatColor.WHITE + "StaffChat turned " + ChatColor.GREEN + "ON"));
                    }
                } else {
                    p.sendMessage(new TextComponent(log.permissionmessage));
                }
            } else if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";
                if(AetheriaCoreBungee.Hush.contains(p.getUniqueId())) {
                    p.sendMessage(new TextComponent(ChatColor.RED + "You have hush currently enabled. please disable it with /hush to turn on Staff Chat"));
                    return;
                }
                else{
                    for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                        if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                            //BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", msg).replaceAll("%player%", p.getName()).replaceAll("%server%", p.getServer().getInfo().getName())));
                            BaseComponent[] scm = TextComponent.fromLegacyText(ChatColor.GOLD + "StaffChat" + ChatColor.DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + ChatColor.RESET + p.getName() + ": " + msg);
                            if(!AetheriaCoreBungee.Hush.contains(staff.getUniqueId()))
                                staff.sendMessage(scm);
                            else
                                break;
                        }
                    }
                }
            } else {
                p.sendMessage(new TextComponent(log.permissionmessage));
            }
        }
    }
}
