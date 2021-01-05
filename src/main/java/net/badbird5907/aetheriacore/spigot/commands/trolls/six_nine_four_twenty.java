package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.util.SendTitle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.SoundManager.high_ping;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.potion.PotionEffectType.CONFUSION;

public class six_nine_four_twenty implements CommandExecutor {
	public SendTitle sendTitle;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender instanceof Player) {
			Player player = getPlayerExact(((Player) sender).getDisplayName());
//          sendTitle.send(player, ChatColor.RED + "69420", "", 10, 10, 10);
			//player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
			requireNonNull(player).addPotionEffect(new PotionEffect(CONFUSION, 100, 2));
			sender.sendMessage(GREEN + "u ok?");
			getScheduler().scheduleSyncRepeatingTask(AetheriaCore.getInstance(), () -> high_ping(player, 10), 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
		}
		return true;
	}
}