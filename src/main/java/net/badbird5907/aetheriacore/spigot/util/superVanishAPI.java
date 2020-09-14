package net.badbird5907.aetheriacore.spigot.util;
import de.myzelyam.api.vanish.VanishAPI;
import de.myzelyam.api.vanish.PlayerHideEvent;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class superVanishAPI {
    public void listVanishedPlayers(Player player, Player player2){
        // A list of all ONLINE vanished players
        List<UUID> invisiblePlayers = VanishAPI.getInvisiblePlayers();
        // Returns a list of the uuids of ALL vanished players, online AND offline
        // MAY INVOLVE A MYSQL-QUERY, so don't use it too often or use it on a different thread!
        List<UUID> allInvisiblePlayers = VanishAPI.getAllInvisiblePlayers();
        // Hide a player
        VanishAPI.hidePlayer(player);
        // Show a player
        VanishAPI.showPlayer(player);
        // Returns true if player is allowed to see player2
        boolean canSee = VanishAPI.canSee(player, player2);
        // Returns true if a player is invisible
        boolean isVanished = VanishAPI.isInvisible(player);
    }

}
