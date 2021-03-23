package net.badbird5907.aetheriacore.spigot.commands.impl.utilcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GmTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("gm")){
            if(args.length == 1){
                List<String> r = new ArrayList<>();
                r.add("1");
                r.add("c");
                r.add("Creative");
                r.add("0");
                r.add("s");
                r.add("Survival");
                r.add("3");
                r.add("sp");
                r.add("Spectator");
                r.add("2");
                r.add("a");
                r.add("Adventure");
                return r;
            }
            else if (args.length == 2){
                List<String> r = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(a ->{
                    r.add(a.getDisplayName());
                });
                return r;
            }
        }
        return null;
    }
}
