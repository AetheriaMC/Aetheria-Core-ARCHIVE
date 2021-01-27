package net.badbird5907.aetheriacore.spigot.features.timevote.subcommands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.features.timevote.TimeVote;
import net.badbird5907.aetheriacore.spigot.features.timevote.VoteMgr;
import org.bukkit.entity.Player;

public class No extends TimeVote {
    @Override
    public void execute(Player player, String[] args, String page, AetheriaCore plugin) {
        VoteMgr.countvote(player, false);
    }
}
