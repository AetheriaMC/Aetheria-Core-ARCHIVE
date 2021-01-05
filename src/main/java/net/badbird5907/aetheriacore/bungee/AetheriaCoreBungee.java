package net.badbird5907.aetheriacore.bungee;

import net.badbird5907.aetheriacore.bungee.commands.staff.*;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalBroadcast;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.discord.listeners.ACListener;
import net.badbird5907.aetheriacore.bungee.discord.listeners.SCListener;
import net.badbird5907.aetheriacore.bungee.listeners.LockdownListener;
import net.badbird5907.aetheriacore.bungee.listeners.Login_Disconnect;
import net.badbird5907.aetheriacore.bungee.listeners.events;
import net.badbird5907.aetheriacore.bungee.manager.DatabaseUtils;
import net.badbird5907.aetheriacore.bungee.util.DataFile;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.dv8tion.jda.api.JDA;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;
import static net.badbird5907.aetheriacore.bungee.manager.log.Log;
import static net.badbird5907.aetheriacore.bungee.util.Config.createFile;
import static net.badbird5907.aetheriacore.bungee.util.Config.getData;
import static net.badbird5907.aetheriacore.bungee.util.Database.connect;
import static net.badbird5907.aetheriacore.bungee.util.Database.disconnect;
import static net.dv8tion.jda.api.JDABuilder.createDefault;

public final class AetheriaCoreBungee extends Plugin {
	public static List<UUID> inSc = new ArrayList<>();
	public static List<UUID> inAc = new ArrayList<>();
	public static List<UUID> inCSpy = new ArrayList<>();
	public static List<UUID> Hush = new ArrayList<>();
	public static Boolean is_lockdown;
	private static AetheriaCoreBungee instance;
	private JDA jda;

	public static AetheriaCoreBungee getInstance() {
		return instance;
	}

	public static JDA getJDA() {
		return AetheriaCoreBungee.getInstance().jda;
	}

	@Override
	public void onEnable() {
		instance = this;
		// Plugin startup logic
		Log("Starting...");
		long start = currentTimeMillis();
		BasicConfigurator.configure();
		//getProxy().registerChannel("aetheriacore:messaging");
		Messages.createFile("bungeemessages");
		DataFile.createFile("bungeedata");
		createFile("bungeeconfig");
		Log("Registering Commands...");
		getProxy().getInstance().getPluginManager().registerCommand(this, new Hub());
		getProxy().getInstance().getPluginManager().registerCommand(this, new Beta());
		getProxy().getInstance().getPluginManager().registerCommand(this, new Creative());
		getProxy().getInstance().getPluginManager().registerCommand(this, new Survival());
		getProxy().getInstance().getPluginManager().registerCommand(this, new Vanilla());

		getProxy().getInstance().getPluginManager().registerCommand(this, new AdminChat());
		getProxy().getInstance().getPluginManager().registerCommand(this, new CSpy());
		getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalBroadcast());
		getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalClearChat());
		getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChat());
		getProxy().getInstance().getPluginManager().registerCommand(this, new staff());
		getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChatBeta());

		Log("Registering Events...");
		getProxy().getPluginManager().registerListener(this, new events());
		getProxy().getPluginManager().registerListener(this, new Login_Disconnect());
		getProxy().getPluginManager().registerListener(this, new LockdownListener());
		Configuration config = getData("bungeeconfig");
		assert config != null;
		is_lockdown = config.getBoolean("Data.lockdown");
		Log("Connecting to database");
		setupDatabase();
		Log("Connecting to discord...");
		buildJDA();
		Log("Startup Finished. Took " + (currentTimeMillis() - start) + "ms.");

	}

	@Override
	public void onDisable() {
		disconnect();
	}

	private void setupDatabase() {
		try {
			connect();
		} catch (SQLException | ClassNotFoundException throwables) {
			throwables.printStackTrace();
		}
		//net.badbird5907.aetheriacore.bungee.manager.Database.connect();
		new DatabaseUtils().createTable("StaffChat", "uuid varchar(36), enable TINYINT(1)");
		new DatabaseUtils().createTable("AdminChat", "uuid varchar(36), enable TINYINT(1)");
	}

	private void buildJDA() {
		Configuration conf1 = getData("bungeeconfig");
		assert conf1 != null;
		try {
			jda = createDefault(conf1.getString("Discord.token")).addEventListeners(new SCListener()).addEventListeners(new ACListener()).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}

