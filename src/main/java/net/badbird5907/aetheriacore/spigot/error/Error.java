package net.badbird5907.aetheriacore.spigot.error;

import net.badbird5907.aetheriacore.spigot.manager.PluginManager;

public class Error extends RuntimeException{
    public Error(){
        PluginManager.warn("An Unspecified error has occurred!");
    }
    public Error(String s) {
        PluginManager.warn("ERROR: "+s);
    }
}
