package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;

public class importAll {
    //not in use atm. :/
    AetheriaCore plugin;
    public importAll(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    loggingManager logM;
    public importAll(loggingManager logM) {
        this.logM = logM;
    }


    public void importAllConst(String classname){
        String pluginM = "pluginManager pluginM; public " + classname + "(pluginManager pluginM) { this.pluginM = pluginM; }";
        String permM = "permissionManager permM; public " + classname + "(permissionManager permM) { this.permM = permM; }";
    }

}
