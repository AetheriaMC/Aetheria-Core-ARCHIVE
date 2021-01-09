package net.badbird5907.aetheriacore.spigot.util.staffmenu;

import net.badbird5907.aetheriacore.spigot.util.staffmenu.gui.MainGui;
import org.bukkit.entity.Player;

public class StaffMenu {
    public static void open(Player player, Inventory inv){
        switch (inv){
            case MAIN:
                new MainGui().open(player);
        }
    }
    public static enum Inventory{
        MAIN, PLAYERS, SERVER_CONTROL, STAFF, STAFF_CONTROL
    }
}
