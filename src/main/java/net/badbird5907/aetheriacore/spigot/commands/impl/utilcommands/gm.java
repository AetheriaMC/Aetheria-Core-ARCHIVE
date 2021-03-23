package net.badbird5907.aetheriacore.spigot.commands.impl.utilcommands;

import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.util.Gamemode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class gm implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0)
            sender.sendMessage(ChatColor.RED + "Usage: /gm <mode>");
        else if (args.length == 1){
            String arg = args[0];
            if(arg.equalsIgnoreCase("1") || arg.equalsIgnoreCase("c") || arg.equalsIgnoreCase("creative")){
                if(sender.hasPermission(Permission.GMC.node)){
                    Gamemode.setGameMode((Player) sender, GameMode.CREATIVE);
                }else throw new NoPermsError((Player) sender, "gmc");
            }else if(arg.equalsIgnoreCase("0") || arg.equalsIgnoreCase("s") || arg.equalsIgnoreCase("survival")){
                if(sender.hasPermission(Permission.GMS.node)){
                    Gamemode.setGameMode((Player) sender, GameMode.SURVIVAL);
                }else throw new NoPermsError((Player) sender, "gms");
            }else if(arg.equalsIgnoreCase("3") || arg.equalsIgnoreCase("sp") || arg.equalsIgnoreCase("spectator")){
                if(sender.hasPermission(Permission.GMS.node)){
                    Gamemode.setGameMode((Player) sender, GameMode.SPECTATOR);
                }else throw new NoPermsError((Player) sender, "gmsp");
            }else if(arg.equalsIgnoreCase("2") || arg.equalsIgnoreCase("a") || arg.equalsIgnoreCase("adventure")){
                if(sender.hasPermission(Permission.GMA.node)){
                    Gamemode.setGameMode((Player) sender, GameMode.ADVENTURE);
                }else throw new NoPermsError((Player) sender, "gma");
            }
        }else if(args.length == 2){
            Player target = null;
            String arg = args[0];
            boolean c;
            try{
                target = Bukkit.getPlayer(args[1]);
                c=true;
            } catch (Exception e) {
                c=false;
                sender.sendMessage(ChatColor.RED + "Error: " + args[1] + " is not a online player!");
            }
            if(c){
                if(arg.equalsIgnoreCase("1") || arg.equalsIgnoreCase("c") || arg.equalsIgnoreCase("creative")){
                    if(sender.hasPermission(Permission.GMC.node)){
                        sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Creative");
                        Gamemode.setGameMode( target, GameMode.CREATIVE);
                    }else throw new NoPermsError((Player) sender, "gmc");
                }else if(arg.equalsIgnoreCase("0") || arg.equalsIgnoreCase("s") || arg.equalsIgnoreCase("survival")){
                    sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Survival");
                    if(sender.hasPermission(Permission.GMS.node)){
                        Gamemode.setGameMode( target, GameMode.SURVIVAL);
                    }else throw new NoPermsError((Player) sender, "gms");
                }else if(arg.equalsIgnoreCase("3") || arg.equalsIgnoreCase("sp") || arg.equalsIgnoreCase("spectator")){
                    sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Spectator");
                    if(sender.hasPermission(Permission.GMS.node)){
                        Gamemode.setGameMode( target, GameMode.SPECTATOR);
                    }else throw new NoPermsError((Player) sender, "gmsp");
                }else if(arg.equalsIgnoreCase("2") || arg.equalsIgnoreCase("a") || arg.equalsIgnoreCase("adventure")){
                    sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Adventure");
                    if(sender.hasPermission(Permission.GMA.node)){
                        Gamemode.setGameMode( target, GameMode.ADVENTURE);
                    }
                }else throw new NoPermsError((Player) sender, "gma");
            }
        }
        return true;
    }
}
