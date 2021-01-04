package net.badbird5907.aetheriacore.bungee.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StaffChatManager {
    public boolean doesExist(UUID uuid, Boolean sc){
        if(sc){
            try {
                PreparedStatement check = Database.getConnection().prepareStatement("SELECT * FROM StaffChat");
                ResultSet resultSet = check.executeQuery();
                if(resultSet.next()){
                    if(resultSet.getString("uuid").contains(uuid.toString())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else return false;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }else{
            try {
                PreparedStatement check = Database.getConnection().prepareStatement("SELECT * FROM AdminChat");
                ResultSet resultSet = check.executeQuery();
                if(resultSet.next()){
                    if(resultSet.getString("uuid").contains(uuid.toString())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }else return false;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
    }
    public static void createSC(UUID uuid){
        try{
            PreparedStatement set = Database.getConnection().prepareStatement("INSERT INTO StaffChat VALUES(\"" + uuid.toString() +"\",1)");
            set.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static boolean inSC(UUID uuid){
        try {
            //select the enable collum from sc where the uuid is X
            PreparedStatement check = Database.getConnection().prepareStatement("SELECT enable FROM StaffChat WHERE uuid=\""+ uuid.toString() + "\"");
            check.setString(1, uuid.toString());
            ResultSet result = check.executeQuery();
            if(result.next()){
                return result.getBoolean("enable");
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        /*
         try{
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT sc FROM AetheriaCoreBungee_Staff");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<String> arrayList = new ArrayList<>(columnCount);
            while (rs.next()) {
                int i = 1;
                while(i <= columnCount) {
                    arrayList.add(rs.getString(i++));
                }
            }
            if(arrayList.contains(uuid)){
                arrayList.clear();
                return true;
            }
            else{
                arrayList.clear();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         */
    }
    public static void setSC(UUID uuid, Boolean bool){
        try{
            if(bool){
                if(inSC(uuid))
                    throw new IllegalArgumentException("Player is already in SC!");
                else {
                    PreparedStatement set = Database.getConnection().prepareStatement("UPDATE StaffChat SET enable = 1 WHERE uuid=\"" + uuid.toString() + "\"");
                    set.setString(1, uuid.toString());
                    set.executeUpdate();
                }
            }else {
                if(inSC(uuid)){
                    PreparedStatement set = Database.getConnection().prepareStatement("UPDATE StaffChat SET enable = 0 WHERE uuid=\"" + uuid.toString() + "\"");
                    set.setString(1, uuid.toString());
                    set.executeUpdate();
                }else throw new IllegalArgumentException("Player is not in SC!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static boolean inAC(UUID uuid){
        try {
            //select the enable collum from sc where the uuid is X
            PreparedStatement check = Database.getConnection().prepareStatement("SELECT enable FROM AdminChat WHERE uuid=\"" + uuid.toString() + "\"");
            check.setString(1, uuid.toString());
            ResultSet result = check.executeQuery();
            if(result.next()){
                return result.getBoolean("enable");
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public static void setAC(UUID uuid, Boolean bool){
        try{
            if(bool){
                if(inAC(uuid))
                    throw new IllegalArgumentException("Player is already in AC!");
                else {
                    PreparedStatement set = Database.getConnection().prepareStatement("UPDATE AdminChat SET enable = 1 WHERE uuid=\"" + uuid.toString() + "\"");
                    set.setString(1, uuid.toString());
                    set.executeUpdate();
                }
            }else {
                if(inAC(uuid)){
                    PreparedStatement set = Database.getConnection().prepareStatement("UPDATE AdminChat SET enable = 0 WHERE uuid=\"" + uuid.toString() + "\"");
                    set.executeUpdate();
                }else throw new IllegalArgumentException("Player is not in AC!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
