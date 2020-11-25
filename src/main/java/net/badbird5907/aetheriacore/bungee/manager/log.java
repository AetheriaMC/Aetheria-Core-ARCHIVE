package net.badbird5907.aetheriacore.bungee.manager;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class log {
    public static AetheriaCoreBungee plugin;
    public log(AetheriaCoreBungee plugin) {
        this.plugin = plugin;
    }
    public static String prefix = "§8[§6AEC§8] ";
    public static void Log(String s){
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + s));
    }
    public static void Warn(String s){
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + s));
    }
    public static String permissionmessage = "§cYou do not have permissions to do this!§8 §oNO_PERMISSIONS";
}
