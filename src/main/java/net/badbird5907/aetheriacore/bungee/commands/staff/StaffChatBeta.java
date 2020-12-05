package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Database;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.sql.*;
import java.util.ArrayList;

public class StaffChatBeta extends Command {
    public StaffChatBeta() {
        super("scb", Permission.STAFF_CHAT.node, new String[] { "staffchatbeta" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration config = Messages.getConfig("bungeemessages");
            if (args.length == 0) {
                if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                    if (insc(p.getUniqueId().toString())) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-disabled"))));
                        try {
                            PreparedStatement ps = Database.getConnection().prepareStatement("UPDATE AetheriaCoreBungee_Staff SET sc=0 WHERE uuid = \"" + p.getUniqueId() + "\"");
                            ps.execute();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        try {
                            PreparedStatement ps = Database.getConnection().prepareStatement("UPDATE AetheriaCoreBungee_Staff SET sc=1 WHERE uuid = \"" + p.getUniqueId() + "\"");
                            ps.execute();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-enabled"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
                }
            } else if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                if(AetheriaCoreBungee.inAc.contains(p.getUniqueId())){
                    AetheriaCoreBungee.inAc.remove(p.getUniqueId());
                    p.sendMessage(new TextComponent(
                            ChatColor.GREEN + "You were in Admin chat so Admin chat was toggled off"
                    ));
                }
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";
                for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                    if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                        BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", msg).replaceAll("%player%", PlayerHandler.playerwithrank(p)).replaceAll("%server%", p.getServer().getInfo().getName())));
                        staff.sendMessage(cp);
                    }
                }
            } else {
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
            }
        }
    }
    public static boolean insc(String uuid){
        try{
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT sc FROM AetheriaCoreBungee_Staff");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<String> arrayList = new ArrayList<>(columnCount);
            while (rs.next()) {
                int i = 1;
                while(i <= columnCount) {
                    arrayList.add(rs.getString(i++));
                }
            }
            if(arrayList.contains(uuid)){
                arrayList.clear();
                return true;
            }
            else{
                arrayList.clear();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
