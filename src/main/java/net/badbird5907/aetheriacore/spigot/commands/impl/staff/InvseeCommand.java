package net.badbird5907.aetheriacore.spigot.commands.impl.staff;

import net.badbird5907.aetheriacore.spigot.commands.*;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InvseeCommand implements BaseCommand {
    @Command(permission = Permission.INVSEE, name = "invsee", usage = "/invsee <player>")
    public CommandResult execute(Sender sender, String[] args) {
        if(args.length > 1 || args.length == 0)
            return CommandResult.INVALID_ARGS;
        Player p;
        try{
            p = Bukkit.getPlayer(args[0]);
        } catch (Exception e) {
            return CommandResult.INVALID_PLAYER;
        }
        sender.getPlayer().openInventory(p.getInventory());
        return CommandResult.SUCCESS;
    }

    @Completer(name = "invsee")
    public List<String> tabComplete(Sender sender, String[] args) {
        List<String> r = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player ->{
            r.add(player.getDisplayName());
        });
        return r;
    }
}
