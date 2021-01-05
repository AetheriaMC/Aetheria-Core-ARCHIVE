package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.aetheriacore;
import net.badbird5907.aetheriacore.spigot.commands.fun.PingWars;
import net.badbird5907.aetheriacore.spigot.commands.management.togglePvp;
import net.badbird5907.aetheriacore.spigot.commands.staff.Lockdown;
import net.badbird5907.aetheriacore.spigot.commands.staff.QuickChat;
import net.badbird5907.aetheriacore.spigot.commands.staff.StaffMode;
import net.badbird5907.aetheriacore.spigot.commands.staff.punish.punish;
import net.badbird5907.aetheriacore.spigot.commands.staff.staffchat;
import net.badbird5907.aetheriacore.spigot.commands.timevote.TimeMgr;
import net.badbird5907.aetheriacore.spigot.commands.timevote.TimeVoteCommandManager;
import net.badbird5907.aetheriacore.spigot.commands.trolls.*;
import net.badbird5907.aetheriacore.spigot.commands.utils.*;
import net.badbird5907.aetheriacore.spigot.util.TabComplete;
import org.bukkit.Bukkit;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

public class SetupCommands {
	public static void setupCommands(AetheriaCore plugin) {
		requireNonNull(plugin.getCommand("aetheriacore")).setExecutor(new aetheriacore(AetheriaCore.getInstance()));
		requireNonNull(plugin.getCommand("aetheriacore")).setTabCompleter(new TabComplete());
		requireNonNull(plugin.getCommand("invis")).setExecutor(new invis());
		requireNonNull(plugin.getCommand("clearchat")).setExecutor(new clearchat(plugin));
		requireNonNull(plugin.getCommand("rules")).setExecutor(new rules());
		requireNonNull(plugin.getCommand("performance")).setExecutor(new performance());
		requireNonNull(plugin.getCommand("itemblacklist")).setExecutor(new itemblacklist());
		//plugin.getCommand("queuerestart").setExecutor(new queuerestart(plugin.instance));
		//plugin.getCommand("levitate").setExecutor(new levitate());
		requireNonNull(plugin.getCommand("dupethis")).setExecutor(new DupeThis());
		requireNonNull(plugin.getCommand("opme")).setExecutor(new opme());
		requireNonNull(plugin.getCommand("getuuid")).setExecutor(new getUUID());
		requireNonNull(plugin.getCommand("staffchat")).setExecutor(new staffchat());
		requireNonNull(plugin.getCommand("staffmode")).setExecutor(new StaffMode());
		requireNonNull(plugin.getCommand("hush")).setExecutor(new hush());
		requireNonNull(plugin.getCommand("QuickChat")).setExecutor(new QuickChat(plugin));
		requireNonNull(plugin.getCommand("ClearFloorDrops")).setExecutor(new ClearFloorDrops());
		requireNonNull(plugin.getCommand("SudoOp")).setExecutor(new SudoOpPlaceholder());
		requireNonNull(plugin.getCommand("freeze")).setExecutor(new freezePlayer());
		requireNonNull(plugin.getCommand("unfreeze")).setExecutor(new Unfreeze());
		requireNonNull(plugin.getCommand("nightvision")).setExecutor(new NightVision());
		requireNonNull(plugin.getCommand("togglePVP")).setExecutor(new togglePvp(plugin));
		requireNonNull(plugin.getCommand("CreateNPC")).setExecutor(new CreateNPC());
		requireNonNull(plugin.getCommand("killall")).setExecutor(new KillAll());
		requireNonNull(plugin.getCommand("link")).setExecutor(new link());
		requireNonNull(plugin.getCommand("masssay")).setExecutor(new MassSay());
		requireNonNull(plugin.getCommand("getclientbrand")).setExecutor(new GetClientBrand());
		requireNonNull(plugin.getCommand("getviewdistance")).setExecutor(new GetViewDist());
		if (plugin.getConfig().getBoolean("Time-Voting")) {
			requireNonNull(plugin.getCommand("timevote")).setExecutor(new TimeVoteCommandManager());
			new TimeVoteCommandManager().setup();
			TimeMgr.start();
		}
		if (!Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")) {
			requireNonNull(plugin.getCommand("item")).setExecutor(new item());
			requireNonNull(plugin.getCommand("item")).setTabCompleter(new TabComplete());
		}
        /*
        if(!Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")) {
            try {
                final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

                bukkitCommandMap.setAccessible(true);
                CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
                plugin.getCommand("item").setTabCompleter(new TabComplete());
                commandMap.register("item", new item("item"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         */
		requireNonNull(plugin.getCommand("itemmenu")).setExecutor(new itemmenu());
		requireNonNull(plugin.getCommand("broadcast")).setExecutor(new Broadcast());
		requireNonNull(plugin.getCommand("mutechat")).setExecutor(new mutechat(plugin));
		requireNonNull(plugin.getCommand("kickallnonstaff")).setExecutor(new KickAllNonStaff());
		requireNonNull(plugin.getCommand("lockdown")).setExecutor(new Lockdown());
		requireNonNull(plugin.getCommand("shopkeeper")).setExecutor(new GuiMaker());
		requireNonNull(plugin.getCommand("loop")).setExecutor(new Loop());
		requireNonNull(plugin.getCommand("ping")).setExecutor(new Ping());
		//plugin.getCommand("test").setExecutor(new test());
		requireNonNull(plugin.getCommand("pingwars")).setExecutor(new PingWars());
		requireNonNull(plugin.getCommand("69420")).setExecutor(new six_nine_four_twenty());
		requireNonNull(plugin.getCommand("punish")).setExecutor(new punish());
		//plugin.getCommand("nick").setExecutor(new nick());
		//plugin.instance.getCommand("addgroup").setExecutor(new addGroup(plugin.instance, plugin.instance.luckPerms));
		//plugin.instance.getCommand("systeminfo").setExecutor(new SystemInfo(plugin.instance));
		SudoOp.SudoOp.addAll(asList("Badbird5907", "tuckMCWizard", "Pylons", "StrawHat_KoITta", "CONSOLE"));
		/*
        if(getConfig().getBoolean("Essentials-Replacement", true)){
            plugin.instance.getCommand("fly").setExecutor(new Fly());
            plugin.instance.getCommand("gma").setExecutor(new gma());
            plugin.instance.getCommand("gmsp").setExecutor(new gmc());
        }
         */
	}
}