package net.badbird5907.aetheriacore.spigot.auth.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.auth.TwoFactorAuthentication;
import net.badbird5907.aetheriacore.spigot.auth.commands.subcommands.*;
import net.badbird5907.aetheriacore.shared.handlers.AuthHandler;
import net.badbird5907.aetheriacore.shared.utils.Constants;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class CommandHandler implements CommandExecutor {
    private static CommandHandler instance;
    private final AetheriaCore main;
    private final Set<Command> commands;
    private Command loginCommand, setupCommand;

    public CommandHandler() {
        this.main = AetheriaCore.getInstance();
        //this.main.getCommand(Constants.mainCommand).setExecutor(this);
        this.commands = new HashSet<>();
        this.setupCommands();
    }

    /**
     * Sets up all of the sub commands of /2fa
     */
    private void setupCommands() {
        loginCommand = new LoginCommand("", TwoFactorAuthentication.getInstance());
        setupCommand = new SetupCommand("", TwoFactorAuthentication.getInstance());
        commands.add(new EnableCommand(Constants.enableCommand, TwoFactorAuthentication.getInstance()));
        commands.add(new DisableCommand(Constants.disableCommand, TwoFactorAuthentication.getInstance()));
        commands.add(new ReloadCommand(Constants.reloadCommand, TwoFactorAuthentication.getInstance()));
        commands.add(new HelpCommand(Constants.helpCommand));
    }

    /**
     * Returns a {@link net.badbird5907.aetheriacore.spigot.auth.commands.Command} object related to the given name
     *
     * @param name   Name/Alias of the command to return
     * @return       The command object from the commands set
     */
    public Command getCommand(String name) {
        for(Command cmd : this.commands) {
            if(cmd.getName() != null) {
                if(cmd.getName().equalsIgnoreCase(name))
                    return cmd;
            }

            if(cmd.getAliases() != null) {
                for(String s : cmd.getAliases()) {
                    if(s.equalsIgnoreCase(name))
                        return cmd;
                }
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender cs, @Nonnull org.bukkit.command.Command cmd, @Nonnull String cmdLabel, @Nonnull String[] args) {
        if(!(cs instanceof Player)) {
            cs.sendMessage(TwoFactorAuthentication.getInstance().getMessageHandler().getMessage("&cThis command must be ran as a player"));
            return false;
        }

        Player player = (Player)cs;

        if(TwoFactorAuthentication.getInstance().getAuthHandler() == null) {
            TwoFactorAuthentication.getInstance().getMessageHandler().sendMessage(player, "&cSomething went wrong. Please contact a Staff Member!");
            return false;
        } else if(TwoFactorAuthentication.getInstance().getAuthHandler().getAuthState(player.getUniqueId()) == null) {
            TwoFactorAuthentication.getInstance().getAuthHandler().playerJoin(player.getUniqueId());
        }

        if(TwoFactorAuthentication.getInstance().getAuthHandler().getAuthState(player.getUniqueId()).equals(AuthHandler.AuthState.PENDING_LOGIN)) {
            loginCommand.execute(player, args);
        } else if(TwoFactorAuthentication.getInstance().getAuthHandler().getAuthState(player.getUniqueId()).equals(AuthHandler.AuthState.PENDING_SETUP)) {
            setupCommand.execute(player, args);
        } else {
            if(args.length == 0) {
                Command subCommand = getCommand(Constants.helpCommand);
                if(subCommand != null) subCommand.execute(player, args);
            } else {
                Command subCommand = getCommand(args[0]);
                if(subCommand == null) subCommand = getCommand(Constants.helpCommand);

                String[] arguments = new String[args.length-1];
                System.arraycopy(args, 1, arguments, 0, arguments.length);
                subCommand.execute(player, arguments);
            }
        }
        return false;
    }
    public static CommandHandler getInstance(){ return instance; }
}