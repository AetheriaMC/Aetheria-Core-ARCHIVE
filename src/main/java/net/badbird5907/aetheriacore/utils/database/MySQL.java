package net.badbird5907.aetheriacore.utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Class.forName;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;

public class MySQL {
	private final String host;
	private final String database;
	private final String username;
	private final String password;
	private final String port;
	private Connection connection;
	private Properties properties;

	public MySQL(String uri, String database, String username, String password, String port, Boolean ssl) {
		this.database = database;
		this.port = port;
		this.username = username;
		this.password = password;
		this.host = uri;
        /*
        properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        if(!ssl){
            properties.setProperty("verifyServerCertificate", "false");
            properties.setProperty("useSSL", "false");
        }
         */
	}

	public String getDatabase() {
		return database;
	}

	public boolean connect() {
		try {
			forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return false;
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @see
	 * @deprecated dont use, wont work.
	 */
	public boolean openConnection() {
		if (isClosed()) return false;

		try {
			forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.getDatabase(), properties);
		} catch (SQLException ex) {
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
			if ((prepared == null) || prepared.isClosed() || !prepared.getConnection().isValid(0)) {
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