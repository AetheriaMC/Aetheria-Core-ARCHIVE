package net.badbird5907.aetheriacore.spigot.commands.timevote.subcommands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.timevote.TimeVote;
import net.badbird5907.aetheriacore.spigot.commands.timevote.VoteMgr;
import org.bukkit.entity.Player;

public class No extends TimeVote {
    @Override
    public void execute(Player player, String[] args, String page, AetheriaCore plugin) {
        VoteMgr.countvote(player, false);
    }
}
