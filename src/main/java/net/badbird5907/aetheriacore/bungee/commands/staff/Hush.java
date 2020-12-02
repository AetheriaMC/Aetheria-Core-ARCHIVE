package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Hush extends Command {
    public Hush() {
        super("hush", Permission.HUSH.node, new String[] { "staff-off" });
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(Permission.HUSH.node)){

        }
    }
}
