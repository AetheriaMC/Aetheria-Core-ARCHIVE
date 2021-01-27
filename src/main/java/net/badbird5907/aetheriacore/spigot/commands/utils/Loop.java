package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Loop implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender.hasPermission(Permission.LOOP.node)){
            if(args.length < 2){
                String argsstr = StringUtils.arraytoString(args).replace(args[0], "");
                int looptimes = 0;
                Boolean success;
                try{
                    looptimes = Integer.parseInt(args[0]);
                    success = true;
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid integer!");
                    success = false;
                }
                if(success){
                    if(argsstr.startsWith("c:")){
                        for(int i = 0; i < looptimes; ++i)
                            ((Player) sender).chat(ChatColor.translateAlternateColorCodes('&', argsstr.replaceFirst("c:", "")));
                    }
                    else{
                        for(int i = 0; i < looptimes; ++i)
                            ((Player) sender).chat("/" + argsstr);
                    }
                }
            }
        }else{
            throw new NoPermsError((Player) sender, "loop");
        }
        return true;
    }
}
