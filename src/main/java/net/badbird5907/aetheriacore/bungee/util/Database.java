package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.config.Configuration;

import java.sql.*;

import static java.lang.Class.forName;
import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.manager.log.Log;

public class Database {
	private static final Configuration config = Messages.getConfig("bungeeconfig");
	private static final String uri = requireNonNull(config).getString("Database.uri");
	private static final String password = config.getString("Database.password");
	private static final String username = config.getString("Database.username");
	private static final String port = config.getString("Database.port");
	private static final String database = config.getString("Database.name");
	static Connection connection;

	// connect
    /*
    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + uri + ":" + port + "/" + database ,username, password);
                log.Log("Connecting to jdbc:mysql://" + uri + ":" + port + "/" + database + "" + username + "" + password);
                if(isConnected())
                    log.Log("Database Connected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     */
	public static void connect() throws SQLException, ClassNotFoundException {
		forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + uri + ":" + port + "/" + database, username, password);
		Log("Connecting to jdbc:mysql://" + uri + ":" + port + "/" + database + "" + username + "" + password);
	}

	public static void disconnect() {
		if (isConnected()) try {
			Log("Disconnecting from database...");
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	// isConnected
	public static boolean isConnected() {
		return (connection != null);
	}

	// getConnection
	public static Connection getConnection() {
		return connection;
	}

	public static void SetupDB() throws SQLException {
		try {
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PreparedStatement staff = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_Staff (uuid varchar(36), sc BOOLEAN, ac BOOLEAN, cspy BOOLEAN, hush BOOLEAN);");
		staff.executeUpdate();
		PreparedStatement players = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS AetheriaCoreBungee_PlayerData (uuid varchar(36), name varchar(16), last_seen varchar(8), online BOOLEAN);");
		players.executeUpdate();
	}

	public static boolean TableExists(String name) throws SQLException {
		DatabaseMetaData dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, name, null);
		return tables.next();
	}

	public static ResultSet ExecuteQuery(String sql) {
		try {
			PreparedStatement a = getConnection().prepareStatement(sql);
			return a.executeQuery();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return null;
		}
	}
}
