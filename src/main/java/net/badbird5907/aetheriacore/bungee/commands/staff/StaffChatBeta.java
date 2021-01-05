package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.bungee.util.Database.getConnection;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.STAFF_CHAT;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.GREEN;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;
import static net.md_5.bungee.api.chat.TextComponent.fromLegacyText;

public class StaffChatBeta extends Command {
	public StaffChatBeta() {
		super("scb", STAFF_CHAT.node, "staffchatbeta");
	}

	public static boolean insc(String uuid) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("SELECT sc FROM AetheriaCoreBungee_Staff");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			ArrayList<String> arrayList = new ArrayList<>(columnCount);
			while (rs.next()) {
				int i = 1;
				while (i <= columnCount) arrayList.add(rs.getString(i++));
			}
			if (arrayList.contains(uuid)) {
				arrayList.clear();
				return true;
			} else {
				arrayList.clear();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			Configuration config = getConfig("bungeemessages");
			if (args.length == 0) if (p.hasPermission(STAFF_CHAT.node)) if (insc(p.getUniqueId().toString())) {
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.sc-disabled"))));
				try {
					PreparedStatement ps = getConnection().prepareStatement("UPDATE AetheriaCoreBungee_Staff SET sc=0 WHERE uuid = \"" + p.getUniqueId() + "\"");
					ps.execute();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			} else {
				try {
					PreparedStatement ps = getConnection().prepareStatement("UPDATE AetheriaCoreBungee_Staff SET sc=1 WHERE uuid = \"" + p.getUniqueId() + "\"");
					ps.execute();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.sc-enabled"))));
			}
			else
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
			else if (p.hasPermission(STAFF_CHAT.node)) {
				if (AetheriaCoreBungee.inAc.contains(p.getUniqueId())) {
					AetheriaCoreBungee.inAc.remove(p.getUniqueId());
					p.sendMessage(new TextComponent(GREEN + "You were in Admin chat so Admin chat was toggled off"));
				}
				String msg = stream(args).map(arg -> arg + " ").collect(joining());
				for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
					if (staff.hasPermission(STAFF_CHAT.node))
						staff.sendMessage(fromLegacyText(translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", msg).replaceAll("%player%", playerwithrank(p)).replaceAll("%server%", p.getServer().getInfo().getName()))));
			} else
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
		}
	}
}
