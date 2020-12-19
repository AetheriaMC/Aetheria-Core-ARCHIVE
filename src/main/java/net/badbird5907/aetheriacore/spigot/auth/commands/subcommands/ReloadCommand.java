package net.badbird5907.aetheriacore.spigot.auth.commands.subcommands;

import net.badbird5907.aetheriacore.shared.handlers.AuthHandler;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import net.badbird5907.aetheriacore.spigot.auth.commands.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReloadCommand extends Command {

    private final TwoFactorAuthentication main;
    public ReloadCommand(String name, TwoFactorAuthentication main) {
        super(name);
        this.main = main;
    }

    @Override
    public String[] getAliases() {
        return null;
    }

    @Override
    public String[] getPermissions() {
        return new String[] { "2fa.reload" };
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        if(!hasPermissions(player)) {
            main.getMessageHandler().sendMessage(player, "&cYou do not have permission to run this command");
        } else {
            Map<UUID, AuthHandler.AuthState> states = new HashMap<>();
            for(Player pl : Bukkit.getOnlinePlayers()) {
                if(main.getAuthHandler().getAuthState(pl.getUniqueId()) != null)
                    states.put(pl.getUniqueId(), main.getAuthHandler().getAuthState(pl.getUniqueId()));
            }

            AetheriaCore.getInstance().reloadConfig();
            main.setupAuth(AetheriaCore.getInstance());

            for(UUID uuid : states.keySet())
                main.getAuthHandler().changeState(uuid, states.get(uuid));

            main.getMessageHandler().sendMessage(player, "&aConfig was reloaded");
        }
    }
}