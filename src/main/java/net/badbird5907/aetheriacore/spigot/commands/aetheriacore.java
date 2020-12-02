package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.UUID;

public class aetheriacore implements CommandExecutor {
    AetheriaCore plugin;
    public aetheriacore(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    //method called on /aetheriacore
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("aetheriacore")) {


            PluginDescriptionFile pdf = plugin.getDescription(); //Gets plugin.yml

            if (args.length == 0) {
                player.sendMessage(ChatColor.DARK_GRAY + "------------------------------------");
                player.sendMessage(ChatColor.GOLD + "AetheriaCore Version " + pdf.getVersion());
                player.sendMessage(ChatColor.GOLD + "Made By: Badbird5907");
                player.sendMessage(ChatColor.WHITE + "");
                player.sendMessage(ChatColor.WHITE + "The Aetheria Server's Core and API");
                player.sendMessage(ChatColor.WHITE + "use /aec help for a list of commands.");
                player.sendMessage(ChatColor.WHITE + "Connected Supported Plugins: ");
                //Only Anti Cheat Enabled
                if (Bukkit.getServer().getPluginManager().isPluginEnabled("AetheriaAntiCheat"))
                    player.sendMessage(ChatColor.GREEN + "Aetheria-Anti-Cheat");
                //Only Minigames Enabled
                if (Bukkit.getServer().getPluginManager().isPluginEnabled("AetheriaMinigames"))
                    player.sendMessage(ChatColor.GREEN + "Aetheria-Minigames");
                if (!Bukkit.getServer().getPluginManager().isPluginEnabled("AetheriaAntiCheat") && !Bukkit.getServer().getPluginManager().isPluginEnabled("AetheriaAntiCheat")) {
                    player.sendMessage(ChatColor.RED + "None");
                    player.sendMessage(ChatColor.DARK_GRAY + "------------------------------------");
                    return true;
                //all enabled
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("settings")) {
                        if (player.hasPermission(permissionManager.settings)) {

                        }

                    }
                    if (args[0].equalsIgnoreCase("reload")) {
                        if (player.hasPermission(permissionManager.reload)) {
                            try {
                                plugin.reloadConfig();
                                player.sendMessage(pluginManager.prefix + ChatColor.GREEN + "Config Reloaded!");
                                player.sendMessage(pluginManager.prefix + ChatColor.RED + "This is not supported. please restart the server when you can.");
                            } catch (Exception e) {
                                e.printStackTrace();
                                player.sendMessage(ChatColor.RED + "ERROR: See console for more details");
                            }
                        }


                    }
                    if (args[0].equalsIgnoreCase("debug")) {
                        if (player instanceof Player) {
                            if (args.length == 2) {
                                Player p = (Player) player;
                                UUID uuid = p.getUniqueId(); // this should work
                                if (args[1].equalsIgnoreCase("on")) {
                                    if (DebugLogger.Debugplayers.contains(uuid))
                                        player.sendMessage(ChatColor.RED + "Error: Debug logging is already on!");
                                    else {
                                        DebugLogger.Debugplayers.add(uuid);
                                        player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "Debug Logging turned " + ChatColor.GREEN + "ON");
                                    }
                                }
                                if (args[1].equalsIgnoreCase("off")) {
                                    if (!DebugLogger.Debugplayers.contains(uuid))
                                        player.sendMessage(ChatColor.RED + "Error: Debug logging is already off!");
                                    else {
                                        DebugLogger.Debugplayers.remove(uuid);
                                        player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "Debug Logging turned " + ChatColor.RED + "OFF");
                                    }
                                }
                            }
                        } else {
                            if (plugin.getConfig().getBoolean("Console-Debug-Default"))
                                player.sendMessage(pluginManager.prefix + "Error: You may not turn on debug logging because it is on for console by default in the config.");
                            else
                                player.sendMessage(pluginManager.prefix + "Error: You may not turn on debug logging because it is off for console by default in the config.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("info")) {
                        player.sendMessage(ChatColor.GREEN + "Aetheria Core Version: " + pdf.getVersion());
                        player.sendMessage(ChatColor.GREEN + "Server Version: " + Bukkit.getServer().getVersion());
                        player.sendMessage(ChatColor.GREEN + "Server Type In Config: " + plugin.getConfig().getString("Server-Type"));
                    }
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "AetheriaCore");
                        player.sendMessage(ChatColor.BLUE + "Version:" + pdf.getVersion());
                        player.sendMessage(ChatColor.AQUA + "By Badbird5907");
                        player.sendMessage(ChatColor.BLUE + "----------------------------------");
                        player.sendMessage(ChatColor.GOLD + "/AetheriaCore help | Shows this page.");
                        player.sendMessage(ChatColor.GOLD + "/AetheriaCore debug | Debug Info");
                        player.sendMessage(ChatColor.GOLD + "/discord | Shows Discord Link");
                        player.sendMessage(ChatColor.GOLD + "/rules | Shows Server Rules");
                        player.sendMessage(ChatColor.GOLD + "/getUUID | Get player UUID");
                        if (player.hasPermission(permissionManager.ListStaffcmds)) {
                            player.sendMessage(ChatColor.GREEN + "Staff Commands:");
                            player.sendMessage(ChatColor.GOLD + "/itemblacklist or /ibl | Shows the Item Blacklist");
                            player.sendMessage(ChatColor.GOLD + "/clearchat or /cc | Staff Only | Clears Chat");
                            player.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "/queuerestart | Staff Only | Queue A Server Restart");
                            player.sendMessage(ChatColor.GOLD + "/invis | Staff Only | Gives invisibility for 6 minutes NOT VANISH");
                            player.sendMessage(ChatColor.GOLD + "/performance or /perf | Staff Only | Server Performance");
                            player.sendMessage(ChatColor.GOLD + "/staffchat or /sc | Staff Only | Staff Chat");
                            player.sendMessage(ChatColor.GOLD + "/createnpc " + ChatColor.RED + "EXPERMENTAL. MAY CRASH SERVER");
                            if (plugin.getConfig().getBoolean("Essentials-Replacement", true)) {
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
                        } else {
                            player.sendMessage(ChatColor.BLUE + "Staff commands are not shown because you are missing the permission node:");
                            player.sendMessage(ChatColor.BLUE + "'aetheriacore.liststaffcmds'");
                        }
                        player.sendMessage(ChatColor.BLUE + "----------------------------------");
                        player.sendMessage(ChatColor.RED + "Commands with a " + ChatColor.STRIKETHROUGH + "Strikethrough" + ChatColor.RESET + "" + ChatColor.RED + " means that it is not finished.");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Error: Invalid Arguments. Please use /aec help" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "INVALID_ARGUMENTS");
                        return true;
                    }

                } else {
                    return true;
                }
            }
            return true;
        }
        return true;
    }
}