package net.badbird5907.aetheriacore.spigot.commands.staff.wipe;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.util.ItemStackBuilder;
import net.badbird5907.aetheriacore.spigot.util.XMaterial;
import net.badbird5907.aetheriacore.utils.GuiUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicInteger;

public class ConfirmGUI {
    public void open(Player p, String target, String[] args){
        boolean force = false;
        if(args.length > 0){
            for(int i=0; i < args.length; i++){
                if(args[i].equalsIgnoreCase("--force"))
                    force = true;
            }
        }
        AtomicInteger wait_time = new AtomicInteger(3);
        Inventory gui = GuiUtils.halfGuiWithBorder("Confirm Wipe " + target, GuiUtils.Border, true);
        ItemStack yes = new ItemStackBuilder(XMaterial.GREEN_CONCRETE.parseMaterial()).amount(1).name(ChatColor.GREEN + "Yes").build();
        ItemStack no = new ItemStackBuilder(XMaterial.RED_CONCRETE.parseMaterial()).amount(1).name(ChatColor.RED + "No").build();
        ItemStack are_you_sure = new ItemStackBuilder(XMaterial.BARRIER.parseMaterial()).amount(1).name(ChatColor.RED + "Are you sure if you want to wipe " + target).build();
        ItemStack wait = new ItemStackBuilder(XMaterial.BEDROCK.parseMaterial()).name(ChatColor.RED + "Please wait " + wait_time + " seconds.").build();
        ItemStack info = new ItemStackBuilder(XMaterial.REDSTONE_TORCH.parseMaterial()).name(ChatColor.GREEN + "Info").lore(ChatColor.GRAY + "force: " + force).build();

        gui.setItem(16, no);
        wait.setAmount(wait_time.get());
        gui.setItem(13, are_you_sure);;
        gui.setItem(18, info);
        p.openInventory(gui);
        //TODO stop being lazy
        AetheriaCore.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AetheriaCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                wait_time.set(wait_time.get() -1);
                wait.setAmount(wait_time.get());
                gui.setItem(10, wait);
            }
        },20L);
        AetheriaCore.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AetheriaCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                wait_time.set(wait_time.get() -1);
                wait.setAmount(wait_time.get());
                gui.setItem(10, wait);
            }
        },40L);
        AetheriaCore.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AetheriaCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                wait_time.set(wait_time.get() -1);
                wait.setAmount(wait_time.get());
                gui.setItem(10, wait);
            }
        },60L);
        AetheriaCore.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AetheriaCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                wait_time.set(3);
                gui.setItem(10, yes);
            }
        },60L);
    }

}
