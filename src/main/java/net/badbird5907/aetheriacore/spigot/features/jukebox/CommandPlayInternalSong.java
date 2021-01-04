package net.badbird5907.aetheriacore.spigot.features.jukebox;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandPlayInternalSong implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender.hasPermission(Permission.PLAY_INTERNAL_SONG.node)){

        }
        return true;
    }
}
