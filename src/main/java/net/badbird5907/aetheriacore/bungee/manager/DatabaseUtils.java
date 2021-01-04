package net.badbird5907.aetheriacore.bungee.manager;

import net.badbird5907.aetheriacore.bungee.util.Database;
import net.badbird5907.aetheriacore.utils.database.MySQL;

import java.sql.PreparedStatement;

public class DatabaseUtils {
    public void createTable(String name, String params){
        PreparedStatement ps;
        try{
            ps = Database.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + name + "(" + params + ")");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
