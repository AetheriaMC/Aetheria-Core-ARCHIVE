package net.badbird5907.aetheriacore.spigot.commands.impl.trolls;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class endgamescreen implements CommandExecutor {
    AetheriaCore plugin;

    public endgamescreen(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            //sendCredits((Player) sender);
            //GAME_STATE_CHANGE.getPacketClass().
        } else {
            sender.sendMessage(ChatColor.RED + "Must be a player.");
        }

        return true;
    }

    /**
     *
     * @param player
     * rolls the end screen credits to the player, nice for trolls
     */
    public void sendCredits(Player player){
        /*
        PacketContainer sendCreds = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
        sendCreds.getDoubles().write(4, )
         */
        //PacketPlayOutGameStateChange sendCreds = new PacketPlayOutGameStateChange();


    }
}
