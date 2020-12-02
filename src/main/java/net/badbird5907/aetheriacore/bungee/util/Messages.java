package net.badbird5907.aetheriacore.bungee.util;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Messages {
    public static void createFile(String fileName) {
        if (!AetheriaCoreBungee.getInstance().getDataFolder().exists())
            AetheriaCoreBungee.getInstance().getDataFolder().mkdir();
        File file = new File(AetheriaCoreBungee.getInstance().getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (fileName.equals("bungeeconfig")) {
                    Configuration config = getConfig(fileName);
                    config.set("Config.enable-join-message", Boolean.valueOf(true));
                    config.set("Config.enable-leave-message", Boolean.valueOf(true));
                    config.set("Config.enable-switch-messages", Boolean.valueOf(true));
                    config.set("Messages.sc-hover", "&8Player: %player%\nServer: %server%");
                    config.set("Messages.sc-enabled", "&8[&6AEC&8]  &8&7Staff chat is now &aenabled&7.");
                    config.set("Messages.sc-disabled", "&8[&6AEC&8] &8&7Staff chat is now &cdisabled&7.");
                    config.set("Messages.sc-format", "&6&lStaffChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
                    config.set("Messages.ac-enabled", "&8[&6AEC&8] &8&7Admin chat is now &aenabled&7.");
                    config.set("Messages.ac-disabled", "&8[&6AEC&8] &8&7Admin chat is now &cdisabled&7.");
                    config.set("Messages.ac-format", "&c&lAdminChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
                    config.set("Messages.cspy-enabled", "&8[&6AEC&8] &8&7Command spy is now &aenabled&7.");
                    config.set("Messages.cspy-disabled", "&8[&6AEC&8] &8&7Command spy is now &cdisabled&7.");
                    config.set("Messages.cspy-format", "&9&lCommandSpy &8» &7(%server%) &r%user%: %command%");
                    config.set("Messages.no-permission", "&cYou do not have permissions to do this!&8 &oNO_PERMISSIONS");
                    config.set("Messages.helpop-no-message", "&c&lUsage: /gethelp <message>");
                    config.set("Messages.clearchat-bypass", "&aChat was cleared by %player% but you're immune");
                    config.set("Messages.clearchat", "&aChat was cleared globally by %player%");

                    config.set("Messages.global-lockdown", "A &c&lGLOBAL&r lockdown has been called by %player%. &r All non staff players have been kicked.");
                    config.set("Messages.already-lockdown", "&cNetwork is already under lockdown. do /bungeeendlockdown to end it.");

                    config.set("Messages.plugins", "&bPlugins we use:\n&aAetheriaCore&r, &aAetheriaItems&r, &aAetheria-Anti-Cheat");

                    List<String> list = new ArrayList<>();
                    list.add("&7&m-----------------------------------");
                    list.add("&7----&r&9&lReport&r&7----");
                    list.add("&9Reporter: &e%reporter% &7(%reporter-server%)");
                    list.add("&9Reported: &e%reported% &7(%reported-server%)");
                    list.add("&9Reason: &e%reason%");
                    list.add("&7&m-----------------------------------");
                    config.set("Messages.report-to-staff-message", list);
                    config.set("Messages.report-to-player-message", "&8[&6AEC&8] &8&aYou have reported: &e%player%&a, &afor &athe &areason: &e%reason%&7.");
                    config.set("Messages.report-incorrect-format", "&8[&6AEC&8] &cUsage: /report <player> <reason>");
                    config.set("Messages.report-offline-player", "&8[&6AEC&8] &8&cThat player is currently offline.");
                    config.set("Messages.staff-join-network", "&aStaff &8» &f%player% &aConnected");
                    config.set("Messages.staff-leave-network", "&aStaff &8» &f%player% &cDisconnected");
                    config.set("Messages.staff-switch-server", "&aStaff &8 » &f%player% &aSwitched from %from% to %to%");
                    List<String> list2 = new ArrayList<>();
                    list2.add("&7&m-----------------------------------");
                    list2.add("&c&l----Help Requested----");
                    list2.add("&e%player% &7(%server%)");
                    list2.add("&eMessage: %msg%");
                    list2.add("&7&m-----------------------------------");
                    config.set("Messages.helpop-to-staff", list2);
                    config.set("Messages.helpop-to-player", "&8[&6AEC&8] &8&7Requested help from staff: &c%msg%7.");
                    config.set("Messages.in-cooldown", "&9You are still on cooldown: &7%seconds% left.");
                    saveConfig(config, fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConfig(String fileName) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(AetheriaCoreBungee.getInstance().getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveConfig(Configuration config, String fileName) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(AetheriaCore.getInstance().getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}