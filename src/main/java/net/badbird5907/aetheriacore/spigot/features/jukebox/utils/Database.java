package net.badbird5907.aetheriacore.spigot.features.jukebox.utils;

import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Class.forName;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;

public class Database {

	private final Properties properties;
	private final String host;
	private final String database;
	private final int port;

	private Connection connection;

	public Database(ConfigurationSection config) {
		this.host = config.getString("MySQL.credentials.host");
		this.database = config.getString("MySQL.credentials.database");
		this.port = config.getInt("MySQL.credentials.port");
		properties = new Properties();
		properties.setProperty("user", config.getString("MySQL.auth.username"));
		properties.setProperty("password", config.getString("MySQL.auth.password"));
		if (!config.getBoolean("ssl")) {
			properties.setProperty("verifyServerCertificate", "false");
			properties.setProperty("useSSL", "false");
		}
	}

	public String getDatabase() {
		return database;
	}

	public boolean openConnection() {
		if (isClosed()) return false;
		try {
			forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			getInstance().getLogger().severe("Database driver not found.");
			return false;
		}

		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.getDatabase(), properties);
		} catch (SQLException ex) {
			getInstance().getLogger().severe("An exception occurred when connecting to the database.");
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isClosed() {
		try {
			return connection != null && !connection.isClosed() && connection.isValid(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void closeConnection() {
		if (isClosed()) {
			try {
				connection.close();
			} catch (SQLException ex) {
				getInstance().getLogger().severe("An exception occurred when closing database connection.");
				ex.printStackTrace();
			}
			connection = null;
		}
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public PreparedStatement prepareStatementGeneratedKeys(String sql) throws SQLException {
		return connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
	}

	public Connection getConnection() {
		return connection;
	}

	public class JBStatement {
		private final String statement;
		private final boolean returnGeneratedKeys;
		private PreparedStatement prepared;

		public JBStatement(String statement) {
			this(statement, false);
		}

		public JBStatement(String statement, boolean returnGeneratedKeys) {
			this.statement = statement;
			this.returnGeneratedKeys = returnGeneratedKeys;
		}

		public PreparedStatement getStatement() throws SQLException {
			if (prepared == null || prepared.isClosed() || !prepared.getConnection().isValid(0)) {
				openConnection();
				prepared = returnGeneratedKeys ? connection.prepareStatement(statement, RETURN_GENERATED_KEYS) : connection.prepareStatement(statement);
			}
			return prepared;
		}

		public String getStatementCommand() {
			return statement;
		}
	}

}