package net.badbird5907.aetheriacore.commons;

import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class LuckPermsManager {
    @Getter
    private static LuckPerms lp;
    public static void setup(){
        lp = LuckPermsProvider.get();
    }
}
