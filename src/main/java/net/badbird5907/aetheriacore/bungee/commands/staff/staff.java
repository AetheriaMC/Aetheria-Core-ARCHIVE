package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class staff extends Command {
    public static ArrayList<String> OnlineStaff = new ArrayList<>();
    public staff() {
        super("staff", Permission.SEE_STAFF_ONLINE.node, new String[] { "onlinestadff" });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(Permission.SEE_STAFF_ONLINE.node)){
            OnlineStaff.clear();
            int onlinep = ProxyServer.getInstance().getPlayers().size();
            for (ProxiedPlayer staffOnline : ProxyServer.getInstance().getPlayers()) {
                if(staffOnline.hasPermission(Permission.STAFF.node)){
                    String a = PlayerHandler.playerwithrank(staffOnline) + ChatColor.DARK_GRAY + " (" + staffOnline.getServer().getInfo().getName() + ")";
                    OnlineStaff.add(a);
                }
            }
            if(OnlineStaff.size() == 0){
                sender.sendMessage(new TextComponent(ChatColor.RED + "There are no staff online."));
            }
            else{
                sender.sendMessage(new TextComponent(ChatColor.BLUE + "Online Staff " + ChatColor.DARK_GRAY + OnlineStaff.size() ));
                OnlineStaff.forEach((a) ->
                        sender.sendMessage(new TextComponent(a))
                );
            }
        }
    }
}
