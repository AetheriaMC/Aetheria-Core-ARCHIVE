package net.badbird5907.aetheriacore.bungee;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.badbird5907.aetheriacore.bungee.commands.staff.*;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalBroadcast;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalClearChat;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.events.CommandListener;
import net.badbird5907.aetheriacore.bungee.events.OnDisconnect;
import net.badbird5907.aetheriacore.bungee.events.OnLogin;
import net.badbird5907.aetheriacore.bungee.events.OnSwitch;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.badbird5907.aetheriacore.bungee.manager.log.Log;

public final class AetheriaCoreBungee extends Plugin implements Listener {
	public static List<UUID> StaffChatPlayers = new ArrayList<>(), AdminChatPlayers = new ArrayList<>(), CommandSpyPlayers = new ArrayList<>(), Hush = new ArrayList<>();
	private static AetheriaCoreBungee instance;

	public static AetheriaCoreBungee getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		// Plugin startup logic
		Log("Starting...");
		//getProxy().registerChannel("aetheriacore:messaging");
		Log("Registering Commands...");
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Hub());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Beta());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Creative());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Survival());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Vanilla());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandSpy());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Hush());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new staff());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new StaffChat());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new AdminChat());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new GlobalClearChat());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new GlobalBroadcast());
		Log("Registering Events...");
		ProxyServer.getInstance().getPluginManager().registerListener(this, new OnLogin());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new CommandListener());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new OnDisconnect());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new OnSwitch());
		Log("Startup Finished!!!");

	}

	/*
		@Override
		public void onDisable() {
			// Plugin shutdown logic
		}
	*/
	private void setupDatabase() {
		MongoClient mongoClient = MongoClients.create("mongodb+srv://AetheriaCorePlugin:AetheriaCorePlugin@aetheriacore-db1.jyi3w.gcp.mongodb.net/AetheriaCore-DB1?retryWrites=true&w=majority");
		MongoCollection<Document> toggles = mongoClient.getDatabase("AetheriaCore-DB1").getCollection("toggles");
		MongoDatabase database = mongoClient.getDatabase("users");
	}

}


