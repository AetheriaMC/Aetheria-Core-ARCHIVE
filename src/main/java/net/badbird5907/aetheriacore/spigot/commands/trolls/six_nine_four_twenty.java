package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.SoundManager;
import net.badbird5907.aetheriacore.spigot.util.SendTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class six_nine_four_twenty implements CommandExecutor {
    public SendTitle sendTitle;
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender instanceof Player){
            Player player = Bukkit.getPlayerExact(((Player) sender).getDisplayName());
//            sendTitle.send(player, ChatColor.RED + "69420", "", 10, 10, 10);
            //player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 2));
            sender.sendMessage(ChatColor.GREEN + "u ok?");
            Bukkit.getScheduler().scheduleSyncRepeatingTask(AetheriaCore.instance, new Runnable() {
                @Override
                public void run() {
                    SoundManager.high_ping(player, 10);
                }
            }, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
        }
        return true;
    }
}
