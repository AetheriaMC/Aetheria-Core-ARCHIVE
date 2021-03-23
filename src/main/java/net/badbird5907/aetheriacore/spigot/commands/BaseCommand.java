package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;

import java.util.List;

public interface BaseCommand {
    public AetheriaCore plugin = AetheriaCore.getInstance();
    CommandResult execute(Sender sender,String[] args);
    List<String> tabComplete(Sender sender, String[] args);
}
