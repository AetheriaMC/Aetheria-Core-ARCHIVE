package net.badbird5907.aetheriacore.bungee.manager;

import net.badbird5907.aetheriacore.utils.database.MySQL;
import net.md_5.bungee.config.Configuration;

import java.sql.Connection;

import static net.badbird5907.aetheriacore.bungee.util.Config.getData;

public class Database {
	private static MySQL db;
	private static Connection connection;

	public static MySQL connect() {
		Configuration config = getData("bungeeconfig");

		//db = new MySQL(config.getString("Database.uri"), config.getString("Database.name"), config.getString("Database.username"), config.getString("Database.password") ,config.getInt("Database.port"), /*config.getBoolean("MySql.ssl")*/ true);
		//if(db.openConnection()){
		//    log.Log("Connected to database!");
		//}else log.Warn("An error occurred while connecting to the database!");
		//connection = db.getConnection();
		//if(db.connect())
		return null;
	}

	public static boolean isConnected() {
		return connection != null;
	}

	public static void disconnect() {
		db.closeConnection();
	}

	public static Connection getConnection() {
		return connection;
	}

	public static MySQL getDB() {
		return db;
	}
}