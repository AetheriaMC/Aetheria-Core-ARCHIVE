package net.badbird5907.aetheriacore.spigot.bungeeutil;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
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
            ProcessMessage.process_goto(in.readUTF(), in.readUTF());
        }
    }
}
