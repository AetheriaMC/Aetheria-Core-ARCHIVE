package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.badbird5907.aetheriacore.bungee.util.Ranks;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

import static net.badbird5907.aetheriacore.bungee.util.Permission.SEE_STAFF_ONLINE;
import static net.badbird5907.aetheriacore.bungee.util.Permission.STAFF;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.*;
import static net.md_5.bungee.api.ProxyServer.getInstance;

public class staff extends Command {
	public static ArrayList<String> OnlineStaff = new ArrayList<>();

	public staff() {
		super("staff", SEE_STAFF_ONLINE.node, "onlinestaff");
	}

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(Permission.SEE_STAFF_ONLINE.node)){
            OnlineStaff.clear();
            for (ProxiedPlayer staffOnline : ProxyServer.getInstance().getPlayers()) {
                if(staffOnline.hasPermission(Permission.STAFF.node)){
                    String a = PlayerHandler.playerwithrank(staffOnline) + ChatColor.DARK_GRAY + " (" + staffOnline.getServer().getInfo().getName() + ")";
                    OnlineStaff.add(a);
                }
            }
            if(OnlineStaff.size() == 0){
                sender.sendMessage(new TextComponent(ChatColor.RED + "There are no staff online."));
            }
            else{
                sender.sendMessage(new TextComponent(ChatColor.BLUE + "Online Staff " + ChatColor.DARK_GRAY + OnlineStaff.size() ));
                OnlineStaff.forEach((a) ->
                        sender.sendMessage(new TextComponent(
                                a
                                        /*
                                        .replaceAll("Owner", Ranks.OWNER.getString())
                                        .replaceAll("Admin", Ranks.ADMIN.getString())
                                        .replaceAll("Mod", Ranks.MOD.getString())
                                        .replaceAll("Helper", Ranks.HELPER.getString())
                                        .replaceAll("Dev", Ranks.DEV.getString())
                                        .replaceAll("Builder", Ranks.BUILDER.getString())
                                        .replaceAll("LoreMaster", Ranks.LM.getString())
                                        .replaceAll("Trial-Dev", Ranks.TRIAL_DEV.getString())
                                        .replaceAll("Trial-Builder", Ranks.TRIAL_BUILDER.getString())
                                        .replaceAll("Trial-LoreMaster", Ranks.TRIAL_LM.getString())
                                         */
                        ))
                );
            }
        }
    }
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission(SEE_STAFF_ONLINE.node)) {
			OnlineStaff.clear();
			int onlinep = getInstance().getPlayers().size();
			getInstance().getPlayers().stream().filter(staffOnline -> staffOnline.hasPermission(STAFF.node)).forEach(staffOnline -> OnlineStaff.add(playerwithrank(staffOnline) + DARK_GRAY + " (" + staffOnline.getServer().getInfo().getName() + ")"));
			if (OnlineStaff.size() == 0) sender.sendMessage(new TextComponent(RED + "There are no staff online."));
			else {
				sender.sendMessage(new TextComponent(BLUE + "Online Staff " + DARK_GRAY + OnlineStaff.size()));
				OnlineStaff.stream().map(TextComponent::new).forEach(sender::sendMessage);
			}
		}
	}
}
