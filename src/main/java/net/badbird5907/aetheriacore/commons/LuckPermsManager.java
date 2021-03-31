package net.badbird5907.aetheriacore.commons;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

import java.util.HashMap;
import java.util.UUID;

@UtilityClass
public class LuckPermsManager {
    @Getter
    private static LuckPerms luckPerms;
    private static HashMap<String, String> colors = new HashMap<>();
    public static void init(){
        luckPerms = LuckPermsProvider.get();
        colors.put("red", CC.RED);
        colors.put("aqua", CC.AQUA);
        colors.put("yellow", CC.YELLOW);
        colors.put("dred", CC.D_RED);
        colors.put("gold", CC.GOLD);
        colors.put("dgreen",CC.D_GREEN);
        colors.put("daqua", CC.D_AQUA);
        colors.put("dblue", CC.D_BLUE);
        colors.put("blue", CC.BLUE);
        colors.put("lpurple", CC.PINK);
        colors.put("purple", CC.PURPLE);
        colors.put("dpurple", CC.PURPLE);
        colors.put("white", CC.WHITE);
        colors.put("gray", CC.GRAY);
        colors.put("dgray", CC.D_GRAY);
        colors.put("black", CC.BLACK);
    }
    public static User getUser(UUID uuid){
        return getLuckPerms().getUserManager().getUser(uuid);
    }
    public static String getPrefix(UUID uuid){
        String prefix = getUser(uuid).getCachedData().getMetaData().getPrefix();
        return (prefix == null || prefix == "null")?"" : prefix;
    }

    /**
     * may cause some lag
     * @param uuid
     * @return
     */
    public static String getMainColor(UUID uuid){
        String color = null;
        if(hasPermission(uuid, "color.red"))
            return CC.RED;
        for (String s : colors.keySet()) {
            if(hasPermission(uuid, "color." + s)){
                color = colors.get(s);
            }
        }
        if(color == null || color == "" || color == "null")
            return CC.GRAY;
        else return color;
    }
    public static boolean hasPermission(User user, String permission) {
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
    public static boolean hasPermission(UUID user, String permission) {
        return getUser(user).getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
