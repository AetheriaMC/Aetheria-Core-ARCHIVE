package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public abstract class TimeVote {
    /*
    /<command> args
     */
    public TimeVote(){

    }
    public abstract void execute(Player player, String[] args, String page,AetheriaCore plugin);
    public String name;
    public String info;
    public String[] aliases;
}
