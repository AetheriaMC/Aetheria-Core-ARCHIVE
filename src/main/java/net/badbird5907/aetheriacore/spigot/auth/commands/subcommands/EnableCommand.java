package net.badbird5907.aetheriacore.spigot.auth.commands.subcommands;

import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import net.badbird5907.aetheriacore.spigot.auth.commands.Command;
import net.badbird5907.aetheriacore.shared.handlers.AuthHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnableCommand extends Command {

    private final TwoFactorAuthentication main;
    public EnableCommand(String name, TwoFactorAuthentication main) {
        super(name);
        this.main = main;
    }

    @Override
    public String[] getAliases() {
        return new String[] { "add", "enable", "setup", "on", "activate", "true" };
    }

    @Override
    public String[] getPermissions() {
        return new String[] { "2fa.use" };
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        if(!hasPermissions(player)) {
            main.getMessageHandler().sendMessage(player, "&cYou do not have permission to run this command");
        } else {
            if(main.getAuthHandler().getAuthState(player.getUniqueId()).equals(AuthHandler.AuthState.DISABLED)) {
                main.getAuthHandler().createKey(player.getUniqueId());
            } else {
                main.getMessageHandler().sendMessage(player, "&cYou are already setup with 2FA");
            }
        }
    }
}