package net.badbird5907.aetheriacore.bungee.commands.util;

import it.unimi.dsi.fastutil.Hash;
import net.badbird5907.aetheriacore.bungee.discord.SendReport;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.utils.PlayerUtils;
import net.badbird5907.aetheriacore.utils.StringUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Report extends Command {
    public Report() {
        super("report", Permission.SEE_STAFF_ONLINE.node, new String[] { "reportplayer" });
    }
    //1 hour cooldown for user 1
    //30s cooldown for everything else
    @Override
    public void execute(CommandSender s, String[] args) {
        if(s instanceof ProxiedPlayer){
            ProxiedPlayer sender = (ProxiedPlayer) s;
            //USAGE: /report player reason
            if(args.length > 2){
                if(sender.hasPermission(Permission.SEND_REPORT.node)){
                    if(cooldown(((ProxiedPlayer) s).getUniqueId(), args[0])){
                        String reason = StringUtils.arraytoString(args).replaceFirst(args[0], "");
                        String player = args[0];
                        if(PlayerUtils.NameToUUID(args[0]) == null)
                            s.sendMessage(new TextComponent(ChatColor.RED + "ERROR: " + args[0] + " is not a player!"));
                        else{
                            HashMap<String, Long> a = new HashMap<>();
                            a.put(args[0], System.currentTimeMillis());
                            cooldownplayers.put(((ProxiedPlayer) s).getUniqueId(), a);
                            lastreport.put(((ProxiedPlayer) s).getUniqueId(), System.currentTimeMillis());
                            //
                            //SendReport.send(sender.getDisplayName(), player, reason, sender.getServer().getInfo().getName());
                        }
                    }
                }
            }
            else sender.sendMessage(new TextComponent(ChatColor.RED + "You are still on cooldown!"));
        }
    }
    //player uuid, last reported player, time of that report
    private static HashMap<UUID, HashMap<String, Long>> cooldownplayers = new HashMap<>();
    //last report
    private static HashMap<UUID, Long> lastreport = new HashMap<>();
    static int cooldownsecondsalreadyreported = 3600;
    static int cooldownsecondsnotreported = 30;
    public static boolean cooldown(UUID uuid, String targetplayer){
        //if in cooldown
        if (cooldownplayers.containsKey(uuid)) {
            if(lastreport.containsKey(uuid)){
                //they have already reported in the last 30 seconds
                HashMap<String, Long> cooldown = cooldownplayers.get(uuid);
                if(lastreport.get(uuid) - System.currentTimeMillis() * 1000 < cooldownsecondsnotreported){
                    return false;
                }else{
                    //if target player has not been reported yet
                    if(!cooldown.containsKey(targetplayer)) return true; //30 seconds is up and player has not been reported yet
                    //has been reported before
                    else{
                        //if the last time the reported player is reported is less than 1 hour
                        if(cooldown.get(targetplayer) - System.currentTimeMillis() * 1000 < cooldownsecondsalreadyreported){
                            //do not allow
                            return false;
                        }
                        //allow since 1 hour is up
                        else return true;
                    }
                }
            }return true;
        }
        return true;
    }
}
