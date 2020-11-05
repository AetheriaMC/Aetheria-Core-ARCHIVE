package net.badbird5907.aetheriacore.spigot.commands.trolls;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.util.Byte;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
