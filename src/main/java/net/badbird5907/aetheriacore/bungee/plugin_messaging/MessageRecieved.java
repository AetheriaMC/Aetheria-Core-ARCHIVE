package net.badbird5907.aetheriacore.bungee.plugin_messaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.badbird5907.aetheriacore.bungee.features.features.Message;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MessageRecieved implements Listener {
    @EventHandler
    public void onPluginMessage(PluginMessageEvent event){
        if(!event.getTag().equalsIgnoreCase("aec:1"))
            return;
        ByteArrayDataInput in = ByteStreams.newDataInput( event.getData() );
        String subchannel = in.readUTF();
        if(subchannel.equalsIgnoreCase("msgin")){
            Message.processMessage(in.readUTF(), in.readUTF(), in.readUTF());
        }

    }
}
