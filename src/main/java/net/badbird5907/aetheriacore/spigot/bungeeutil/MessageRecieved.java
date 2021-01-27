package net.badbird5907.aetheriacore.spigot.bungeeutil;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class MessageRecieved implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase( "aec:1" ) )
            return;

        ByteArrayDataInput in = ByteStreams.newDataInput( bytes );
        String subChannel = in.readUTF();
        if (subChannel.equalsIgnoreCase( "goto" ) ) {
            String p = in.readUTF();
            String t = in.readUTF();
            Player p1 = null;
            Player t1 = null;
            Boolean cont;
            try{
                p1 = Bukkit.getPlayerExact(p);
                t1 = Bukkit.getPlayerExact(t);
                cont = true;
            } catch (Exception e) {
                cont = false;
                e.printStackTrace();
            }
            if(cont){
                //TODO vanish
                p1.teleport(t1);
            }
        }
    }
}
