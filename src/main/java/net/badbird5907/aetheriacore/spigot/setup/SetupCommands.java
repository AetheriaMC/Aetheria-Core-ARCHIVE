package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.CommandFramework;
import net.badbird5907.aetheriacore.spigot.commands.impl.aetheriacore;
import net.badbird5907.aetheriacore.spigot.commands.impl.staff.wipe.Restore;
import net.badbird5907.aetheriacore.spigot.commands.impl.staff.wipe.Wipe;
import net.badbird5907.aetheriacore.spigot.features.timevote.TimeMgr;
import net.badbird5907.aetheriacore.spigot.features.timevote.TimeVoteCommandManager;
import net.badbird5907.aetheriacore.spigot.features.punish.PunishSetup;
import net.badbird5907.aetheriacore.spigot.util.*;
import net.badbird5907.aetheriacore.spigot.commands.impl.utils.*;
import net.badbird5907.aetheriacore.spigot.commands.impl.staff.*;
import net.badbird5907.aetheriacore.spigot.commands.impl.fun.*;
import net.badbird5907.aetheriacore.spigot.commands.impl.management.*;
import net.badbird5907.aetheriacore.spigot.commands.impl.trolls.*;
import net.badbird5907.aetheriacore.spigot.commands.impl.utilcommands.*;
import org.bukkit.Bukkit;

public class SetupCommands {
    public static void setupCommands(AetheriaCore plugin) {
        CommandFramework framework = AetheriaCore.instance.getCommandFramework();
        framework.registerCommands(new DoesThisWorkCommand());
        framework.registerCommands(new InvseeCommand());
        plugin.getCommand("aetheriacore").setExecutor(new aetheriacore(AetheriaCore.getInstance()));
        plugin.getCommand("aetheriacore").setTabCompleter(new TabComplete());
        plugin.getCommand("invis").setExecutor(new invis());
        plugin.getCommand("clearchat").setExecutor(new clearchat(plugin));
        plugin.getCommand("rules").setExecutor(new rules());
        plugin.getCommand("performance").setExecutor(new performance());
        plugin.getCommand("itemblacklist").setExecutor(new itemblacklist());
        //plugin.getCommand("queuerestart").setExecutor(new queuerestart(plugin.instance));
        //plugin.getCommand("levitate").setExecutor(new levitate());
        plugin.getCommand("dupethis").setExecutor(new DupeThis());
        plugin.getCommand("opme").setExecutor(new opme());
        plugin.getCommand("getuuid").setExecutor(new getUUID());
        plugin.getCommand("staffchat").setExecutor(new staffchat());
        plugin.getCommand("staffmode").setExecutor(new StaffMode());
        plugin.getCommand("hush").setExecutor(new hush());
        plugin.getCommand("QuickChat").setExecutor(new QuickChat(plugin));
        plugin.getCommand("ClearFloorDrops").setExecutor(new ClearFloorDrops());
        plugin.getCommand("SudoOp").setExecutor(new SudoOpPlaceholder());
        plugin.getCommand("freeze").setExecutor(new freezePlayer());
        plugin.getCommand("unfreeze").setExecutor(new Unfreeze());
        plugin.getCommand("nightvision").setExecutor(new NightVision());
        plugin.getCommand("togglePVP").setExecutor(new togglePvp(plugin));
        plugin.getCommand("CreateNPC").setExecutor(new CreateNPC());
        plugin.getCommand("killall").setExecutor(new KillAll());
        plugin.getCommand("link").setExecutor(new link());
        plugin.getCommand("masssay").setExecutor(new MassSay());
        plugin.getCommand("getclientbrand").setExecutor(new GetClientBrand());
        plugin.getCommand("getviewdistance").setExecutor(new GetViewDist());
        if(plugin.getConfig().getBoolean("Time-Voting")){
            plugin.getCommand("timevote").setExecutor(new TimeVoteCommandManager());
            new TimeVoteCommandManager().setup();
            TimeMgr.start();
        }
        if(!Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")){
            plugin.getCommand("item").setExecutor(new item());
            plugin.getCommand("item").setTabCompleter(new TabComplete());
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
        plugin.getCommand("itemmenu").setExecutor(new itemmenu());
        plugin.getCommand("broadcast").setExecutor(new Broadcast());
        plugin.getCommand("mutechat").setExecutor(new mutechat(plugin));
        plugin.getCommand("kickallnonstaff").setExecutor(new net.badbird5907.aetheriacore.spigot.commands.impl.utils.KickAllNonStaff());
        plugin.getCommand("lockdown").setExecutor(new Lockdown());
        plugin.getCommand("shopkeeper").setExecutor(new GuiMaker());
        plugin.getCommand("loop").setExecutor(new Loop());
        plugin.getCommand("ping").setExecutor(new Ping());
        //plugin.getCommand("test").setExecutor(new test());
        plugin.getCommand("pingwars").setExecutor(new PingWars());
        plugin.getCommand("69420").setExecutor(new six_nine_four_twenty());
        plugin.getCommand("wipe").setExecutor(new Wipe());
        plugin.getCommand("unwipe").setExecutor(new Restore());
        plugin.getCommand("gmc").setExecutor(new gmc());
        plugin.getCommand("gms").setExecutor(new gms());
        plugin.getCommand("gmsp").setExecutor(new gmsp());
        plugin.getCommand("gma").setExecutor(new gma());
        plugin.getCommand("gm").setExecutor(new gm());
        plugin.getCommand("gm").setTabCompleter(new GmTabCompleter());
        SudoOp.SudoOp.add("Badbird5907");
        SudoOp.SudoOp.add("tuckMCWizard");
        SudoOp.SudoOp.add("Pylons");
        SudoOp.SudoOp.add("StrawHat_KoITta");
        SudoOp.SudoOp.add("CONSOLE");
        /*
        if(getConfig().getBoolean("Essentials-Replacement", true)){
            plugin.instance.getCommand("fly").setExecutor(new Fly());
            plugin.instance.getCommand("gma").setExecutor(new gma());
            plugin.instance.getCommand("gmsp").setExecutor(new gmc());
        }
         */
        PunishSetup.setup(plugin);
    }
}
