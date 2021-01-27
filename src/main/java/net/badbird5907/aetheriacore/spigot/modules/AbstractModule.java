package net.badbird5907.aetheriacore.spigot.modules;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;

public abstract class AbstractModule {
    protected AetheriaCore plugin;
    public AbstractModule(AetheriaCore plugin){
        this.plugin = plugin;
    }
    public abstract void enable();
    public abstract void disable();
}
