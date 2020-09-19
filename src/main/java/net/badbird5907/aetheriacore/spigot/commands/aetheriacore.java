package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class aetheriacore implements CommandExecutor {
    AetheriaCore plugin;

    public aetheriacore(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    //method called on /aetheriacore
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("aetheriacore")){

            PluginDescriptionFile pdf = plugin.getDescription(); //Gets plugin.yml

            if(args.length == 0){
                player.sendMessage(ChatColor.RED + "Error: Invalid Arguments. Please use /aec help" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "INVALID_ARGUMENTS");
                return true;
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("settings")){
                    if(player.hasPermission(permissionManager.settings)){

                    }

                }
                if(args[0].equalsIgnoreCase("reload")){
                    try{
                        plugin.reloadConfig();
                        player.sendMessage(pluginManager.prefix + ChatColor.GREEN + "Config Reloaded!");
                        player.sendMessage(pluginManager.prefix + ChatColor.RED + "This is not supported. please restart the server when you can.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        player.sendMessage(ChatColor.RED + "ERROR: See console for more details");
                    }

                }
                if (args[0].equalsIgnoreCase("debug")){
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "AEC Command Works");
                    player.sendMessage(ChatColor.GREEN + "AEC Version: " + ChatColor.BOLD + pdf.getVersion() );
                    player.sendMessage(ChatColor.GREEN + "Do /AEC");
                }
                if (args[0].equalsIgnoreCase("help")){
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "AetheriaCore");
                    player.sendMessage(ChatColor.BLUE + "Version:" + pdf.getVersion());
                    player.sendMessage(ChatColor.AQUA + "By Badbird5907");
                    player.sendMessage(ChatColor.BLUE + "----------------------------------");
                    player.sendMessage(ChatColor.GOLD + "/AetheriaCore help | Shows this page.");
                    player.sendMessage(ChatColor.GOLD + "/AetheriaCore debug | Debug Info");
                    player.sendMessage(ChatColor.GOLD + "/discord | Shows Discord Link");
                    player.sendMessage(ChatColor.GOLD + "/rules | Shows Server Rules");
                    player.sendMessage(ChatColor.GOLD + "/getUUID | Get player UUID");
                    if(player.hasPermission(permissionManager.ListStaffcmds)){
                        player.sendMessage(ChatColor.GREEN + "Staff Commands:");
                        player.sendMessage(ChatColor.GOLD + "/itemblacklist or /ibl | Shows the Item Blacklist");
                        player.sendMessage(ChatColor.GOLD + "/clearchat or /cc | Staff Only | Clears Chat");
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "/queuerestart | Staff Only | Queue A Server Restart");
                        player.sendMessage(ChatColor.GOLD + "/invis | Staff Only | Gives invisibility for 6 minutes NOT VANISH");
                        player.sendMessage(ChatColor.GOLD + "/performance or /perf | Staff Only | Server Performance");
                        player.sendMessage(ChatColor.GOLD + "/staffchat or /sc | Staff Only | Staff Chat");
                        if(plugin.getConfig().getBoolean("Essentials-Replacement", true)){
                            player.sendMessage(ChatColor.GOLD + "/Fly | Fly");
                            player.sendMessage(ChatColor.GOLD + "/GMC | Creative Mode");
                            player.sendMessage(ChatColor.GOLD + "/GMS | Survival Mode");
                            player.sendMessage(ChatColor.GOLD + "/GMSP | Spectator Mode");
                            player.sendMessage(ChatColor.GOLD + "/GMA | Adventure Mode");
                            player.sendMessage(ChatColor.GOLD + "/god | Be invincible");
                            player.sendMessage(ChatColor.GOLD + "/heal | Heal to full HP");
                            player.sendMessage(ChatColor.GOLD + "/aec reload | Reload Config");
                        }
                        player.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "/DupeThis | Duplicate Item");
                    }
                    else{
                        player.sendMessage(ChatColor.BLUE + "Staff commands are not shown because you are missing the permission node:");
                        player.sendMessage(ChatColor.BLUE + "'aetheriacore.liststaffcmds'");
                    }
                    player.sendMessage(ChatColor.BLUE + "----------------------------------");
                    player.sendMessage(ChatColor.RED + "Commands with a " + ChatColor.STRIKETHROUGH + "Strikethrough" + ChatColor.RESET + "" + ChatColor.RED + " means that it is not finished.");
                    return true;
                }
                else{
                    player.sendMessage(ChatColor.RED + "Error: Invalid Arguments. Please use /aec help" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "INVALID_ARGUMENTS");
                }

            }
/*
            else{
                player.sendMessage(ChatColor.RED + "Error: Invalid Arguments. Please use /aec help" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "INVALID_ARGUMENTS");
            }

 */

        }



        return true;
    }
}