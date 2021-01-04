package net.badbird5907.aetheriacore.bungee;

import net.badbird5907.aetheriacore.bungee.commands.staff.*;
import net.badbird5907.aetheriacore.bungee.commands.util.GlobalBroadcast;
import net.badbird5907.aetheriacore.bungee.commands.warps.*;
import net.badbird5907.aetheriacore.bungee.discord.listeners.ACListener;
import net.badbird5907.aetheriacore.bungee.discord.listeners.SCListener;
import net.badbird5907.aetheriacore.bungee.listeners.LockdownListener;
import net.badbird5907.aetheriacore.bungee.listeners.Login_Disconnect;
import net.badbird5907.aetheriacore.bungee.listeners.events;
import net.badbird5907.aetheriacore.bungee.manager.DatabaseUtils;
import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.bungee.util.DataFile;
import net.badbird5907.aetheriacore.bungee.util.Database;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AetheriaCoreBungee extends Plugin {
    public static List<UUID> inSc = new ArrayList<>();
    public static List<UUID> inAc = new ArrayList<>();
    public static List<UUID> inCSpy = new ArrayList<>();
    public static List<UUID> Hush = new ArrayList<>();
    public static Boolean is_lockdown;
    private static AetheriaCoreBungee instance;
    private JDA jda;

    public static AetheriaCoreBungee getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        log.Log("Starting...");
        long start = System.currentTimeMillis();
        BasicConfigurator.configure();
        //getProxy().registerChannel("aetheriacore:messaging");
        Messages.createFile("bungeemessages");
        DataFile.createFile("bungeedata");
        Config.createFile("bungeeconfig");
        log.Log("Registering Commands...");
        getProxy().getInstance().getPluginManager().registerCommand(this, new Hub());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Beta());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Creative());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Survival());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Vanilla());

        getProxy().getInstance().getPluginManager().registerCommand(this, new AdminChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new CSpy());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalBroadcast());
        getProxy().getInstance().getPluginManager().registerCommand(this, new GlobalClearChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new staff());
        getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChatBeta());
        //getProxy().getInstance().getPluginManager().registerCommand(this, new StaffChatBeta());

        log.Log("Registering Events...");
        getProxy().getPluginManager().registerListener(this, new events());
        getProxy().getPluginManager().registerListener(this, new Login_Disconnect());
        getProxy().getPluginManager().registerListener(this, new LockdownListener());
        /*
        getProxy().getInstance().getPluginManager().registerListener(this, new OnLogin());
        getProxy().getInstance().getPluginManager().registerListener(this, new CommandListener());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnDisconnect());
        getProxy().getInstance().getPluginManager().registerListener(this, new OnSwitch());
         */
        Configuration config = Config.getData("bungeeconfig");
        is_lockdown = config.getBoolean("Data.lockdown");
        log.Log("Connecting to database");
        /*
        try {
            Database.SetupDB();
        } catch (SQLException throwables) {
            log.Warn("Could not connect to database!");
            throwables.printStackTrace();
        }
         */
        setupDatabase();
        log.Log("Connecting to discord...");
        buildJDA();
        log.Log("Startup Finished. Took " + (System.currentTimeMillis() - start) + "ms.");

    }
    @Override
    public void onDisable() {
        Database.disconnect();
    }

    private void setupDatabase() {
        try {
            Database.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //net.badbird5907.aetheriacore.bungee.manager.Database.connect();
        new DatabaseUtils().createTable("StaffChat", "uuid varchar(36), enable TINYINT(1)");
        new DatabaseUtils().createTable("AdminChat", "uuid varchar(36), enable TINYINT(1)");
    }

    private void buildJDA(){
        Configuration conf1 = Config.getData("bungeeconfig");
        try{
            jda = JDABuilder
                    .createDefault(conf1.getString("Discord.token"))
                    .addEventListeners(new SCListener())
                    .addEventListeners(new ACListener())
                    .build();
        }catch (LoginException e){
            e.printStackTrace();
        }
    }
    public static JDA getJDA() {
        return AetheriaCoreBungee.getInstance().jda;
    }
}


