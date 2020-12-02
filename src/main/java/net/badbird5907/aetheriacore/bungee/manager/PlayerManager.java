package net.badbird5907.aetheriacore.bungee.manager;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {
    public static List<UUID> current_sc_players = new ArrayList<>();
    private static Configuration data = net.badbird5907.aetheriacore.bungee.util.DataFile.getData("bungeedata");
    public static boolean IsOnSc(ProxiedPlayer player){
        if(data.contains("Data." + player.getUniqueId())){
            return true;
        }
        else{
            return false;
        }
    }
    public static void PutInSc(ProxiedPlayer player){
        //get list of current sc players
        current_sc_players.clear();
        List<String> current_players1 = data.getStringList("StaffChat");
        for (String n : current_players1) {
            current_sc_players.add(UUID.fromString(n));
        }
        data.set("StaffChat", current_sc_players);
    }
}
