package net.badbird5907.aetheriacore.spigot.setup;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.aetheriacore;
import net.badbird5907.aetheriacore.spigot.commands.management.togglePvp;
import net.badbird5907.aetheriacore.spigot.commands.staff.Lockdown;
import net.badbird5907.aetheriacore.spigot.commands.staff.QuickChat;
import net.badbird5907.aetheriacore.spigot.commands.staff.StaffMode;
import net.badbird5907.aetheriacore.spigot.commands.staff.staffchat;
import net.badbird5907.aetheriacore.spigot.commands.trolls.*;
import net.badbird5907.aetheriacore.spigot.commands.utils.*;
import net.badbird5907.aetheriacore.spigot.test;
import net.badbird5907.aetheriacore.spigot.util.TabComplete;
import net.badbird5907.aetheriacore.spigot.commands.trolls.SudoOp;

public class SetupCommands {
    public static void setupCommands(AetheriaCore plugin) {
        plugin.getCommand("aetheriacore").setExecutor(new aetheriacore(AetheriaCore.instance));
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
        plugin.getCommand("item").setExecutor(new item());
        plugin.getCommand("item").setTabCompleter(new TabComplete());
        plugin.getCommand("itemmenu").setExecutor(new itemmenu());
        plugin.getCommand("broadcast").setExecutor(new Broadcast());
        plugin.getCommand("mutechat").setExecutor(new mutechat(plugin));
        plugin.getCommand("kickallnonstaff").setExecutor(new KickAllNonStaff());
        plugin.getCommand("lockdown").setExecutor(new Lockdown());
        plugin.getCommand("shopkeeper").setExecutor(new GuiMaker());
        plugin.getCommand("loop").setExecutor(new Loop());
        plugin.getCommand("ping").setExecutor(new Ping());
        plugin.getCommand("test").setExecutor(new test());

        //plugin.getCommand("nick").setExecutor(new nick());
        //plugin.instance.getCommand("addgroup").setExecutor(new addGroup(plugin.instance, plugin.instance.luckPerms));
        //plugin.instance.getCommand("systeminfo").setExecutor(new SystemInfo(plugin.instance));
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

    }
}