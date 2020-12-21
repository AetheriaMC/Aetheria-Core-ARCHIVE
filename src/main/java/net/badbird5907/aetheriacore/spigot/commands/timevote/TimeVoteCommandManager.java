package net.badbird5907.aetheriacore.spigot.commands.timevote;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.timevote.subcommands.No;
import net.badbird5907.aetheriacore.spigot.commands.timevote.subcommands.Yes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeVoteCommandManager implements CommandExecutor{
    private ArrayList<TimeVote> commands = new ArrayList<TimeVote>();
    public static HashMap<String, TimeVote> TimeVoteCommands = new HashMap<>();
    private AetheriaCore plugin = AetheriaCore.getInstance();
    public TimeVoteCommandManager(){}
    private void setup(){
        TimeVoteCommands.put("yes", new Yes());
        TimeVoteCommands.put("no", new No());
        TimeVoteCommands.forEach((k, v) -> {
            commands.add(v);
        });
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        ITimeVoteCmdManager.Process(sender, command, s, args);
        return true;
    }
}
