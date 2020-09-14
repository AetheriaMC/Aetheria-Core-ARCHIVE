package net.badbird5907.aetheriacore.spigot.other;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class scoreBoard {
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    Team team = board.registerNewTeam("AetheriaCoreScoreboard");
    Objective objective = board.registerNewObjective("Aetheria", "dummy", "");

}
