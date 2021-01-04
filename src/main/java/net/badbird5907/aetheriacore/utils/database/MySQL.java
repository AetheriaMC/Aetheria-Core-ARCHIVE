package net.badbird5907.aetheriacore.utils.database;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;

import java.sql.*;
import java.util.Properties;

public class MySQL {
    private String host, database, username, password;
    private String port;
    private Boolean ssl;
    private Connection connection;
    private Properties properties;
    public MySQL(String uri,String  database,String  username,String  password,String  port,Boolean ssl){
        this.database = database;
        this.port = port;
        this.ssl = ssl;
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
    public String getDatabase(){return database;}

    public boolean connect() throws SQLException, ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return false;
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"
                            + host+ ":" + port + "/" + database,
                    username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * @deprecated dont use, wont work.
     * @see
     */
    public boolean openConnection() {
        if (!isClosed()) return false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.getDatabase(), properties);
        }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean isClosed() {
        try {
            return connection == null || connection.isClosed() || !connection.isValid(0);
        }catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void closeConnection() {
        if (!isClosed()) {
            try {
                connection.close();
            }catch (SQLException ex) {
                AetheriaCore.getInstance().getLogger().severe("An exception occurred when closing database connection.");
                ex.printStackTrace();
            }
            connection = null;
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public PreparedStatement prepareStatementGeneratedKeys(String sql) throws SQLException {
        return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    public Connection getConnection() {
        return connection;
    }

    public class JBStatement {
        private final String statement;
        private boolean returnGeneratedKeys;

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
                prepared = returnGeneratedKeys ? connection.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS) : connection.prepareStatement(statement);
            }
            return prepared;
        }

        public String getStatementCommand() {
            return statement;
        }
    }
}