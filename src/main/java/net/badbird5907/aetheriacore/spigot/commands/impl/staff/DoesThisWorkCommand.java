package net.badbird5907.aetheriacore.spigot.commands.impl.staff;

import net.badbird5907.aetheriacore.commons.LuckPermsManager;
import net.badbird5907.aetheriacore.spigot.commands.*;

import java.util.Arrays;
import java.util.List;

public class DoesThisWorkCommand implements BaseCommand {
    @Command(name = "doesthiswork",playerOnly = true)
    public CommandResult execute(Sender sender, String[] args) {
        if(args.length > 0)
            return CommandResult.INVALID_ARGS;
        sender.sendMessage(LuckPermsManager.getMainColor(sender.getPlayer().getUniqueId()) + "Hello there " + LuckPermsManager.getPrefix(sender.getPlayer().getUniqueId()) + sender.getDisplayName());
        return CommandResult.SUCCESS;
    }

    @Completer(name = "doesthiswork")
    public List<String> tabComplete(Sender sender, String[] args) {
        return Arrays.asList(new String[]{"a","b","c"});
    }
}
