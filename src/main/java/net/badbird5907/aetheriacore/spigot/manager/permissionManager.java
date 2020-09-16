package net.badbird5907.aetheriacore.spigot.manager;

public class permissionManager {
    public static String teleportparticles = "aetheriacore.cosmetic.teleportparticles";
    public static String gmc = "aetheriacore.gamemode.creative";
    public static String gms = "aetheriacore.gamemode.survival";
    public static String gmsp = "aetheriacore.gamemode.spectator";
    public static String gma = "aetheriacore.gamemode.adventure";
    public static String god = "aetheriacore.god";
    public static String heal = "aetheriacore.heal";
    public static String settings = "aetheriacore.settings";
    public static String discord = "aetheriacore.discord";
    public static String freeze = "aetheriacore.freeze";
    public static String rpt = "aetheriacore.teleport.random.toplayer";
    public static String fly = "aetheriacore.fly";
    public String flyothers = "aetheriacore.fly.others";
    public String CreateNpc = "aetheriacore.npc.create";
    public static String dupethis = "aetheriacore.dupethis";
    public static String staffchat = "aetheriacore.staffchat";
    public static String hush = "aetheriacore.staffchat.hush";

    public static void permissionMessage(String perm){
        if(perm.contains("[usesender]")){
            String perm2 = perm.replace("[usesender]", "");
            /*
            do not use. only here so other stuff doesn't break
            use permissionMessage2
            */
            String permissionmessage = "sender.sendMessage(ChatColor.RED + You don't have the required permission node 'aetheriacore." + perm2 + "' to execute this command.);";
        }
        else{
            String permissionmessage = "player.sendMessage(ChatColor.RED + You don't have the required permission node 'aetheriacore." + perm + "' to execute this command.);";
        }
    }
    public static void permissionMessage2(String perm){
        String permissionmessage2 = "sender.sendMessage(ChatColor.RED + You don't have the required permission node 'aetheriacore." + perm + "' to execute this command.);";

    }

}
