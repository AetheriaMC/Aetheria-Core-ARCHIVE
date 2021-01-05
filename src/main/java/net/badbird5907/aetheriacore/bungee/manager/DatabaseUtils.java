package net.badbird5907.aetheriacore.bungee.manager;

import java.sql.PreparedStatement;

import static net.badbird5907.aetheriacore.bungee.util.Database.getConnection;

public class DatabaseUtils {
	public void createTable(String name, String params) {
		PreparedStatement ps;
		try {
			ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + name + "(" + params + ")");
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}