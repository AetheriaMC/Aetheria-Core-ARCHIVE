package net.badbird5907.aetheriacore.spigot.features.punish;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;

public class PunishSetup {
    public static void setup(AetheriaCore aec){
        Bukkit.getPluginManager().registerEvents(new PunishGuiListener(), aec);
        aec.getCommand("punish").setExecutor(new punish());
    }
}
