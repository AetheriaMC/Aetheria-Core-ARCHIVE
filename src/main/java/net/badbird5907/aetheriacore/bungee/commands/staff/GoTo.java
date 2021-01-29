package net.badbird5907.aetheriacore.bungee.commands.staff;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.SendToServer;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class GoTo extends Command {
    public GoTo() {
            super("goto", Permission.GOTO.node, new String[] { "gotoplayer" });
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            if(args.length > 0){
                ProxiedPlayer target = null;
                Boolean check;
                try{
                    target = BungeeCord.getInstance().getPlayer(args[0]);
                    check = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    check = false;
                }
                if(check){
                    ProxiedPlayer p = (ProxiedPlayer) sender;
                    ServerInfo s = target.getServer().getInfo();
                    if (p.getServer().getInfo().getName().equalsIgnoreCase(s.getName())){
                        ProxiedPlayer finalTarget = target;
                        this.sendData(p.getDisplayName(), finalTarget.getDisplayName(), p);
                    }else{
                        SendToServer.send(p, s.getName());
                        ProxiedPlayer finalTarget = target;
                        AetheriaCoreBungee.getInstance().getProxy().getScheduler().schedule(AetheriaCoreBungee.getInstance(), () -> {
                            this.sendData(p.getDisplayName(), finalTarget.getDisplayName(), p);
                        }, 3, TimeUnit.SECONDS);
                    }
                }
            }
        }
    }
    public void sendData(String player, String target, ProxiedPlayer pp) {
        Collection<ProxiedPlayer> networkPlayers = ProxyServer.getInstance().getPlayers();
        if ( networkPlayers == null || networkPlayers.isEmpty() ) {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("goto");
        out.writeUTF(player);
        out.writeUTF(target);
        pp.getServer().getInfo().sendData( "aec:1", out.toByteArray() );
    }
}
