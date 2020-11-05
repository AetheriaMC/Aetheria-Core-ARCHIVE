package net.badbird5907.aetheriacore.spigot.util;

public class IsInt {
    public static boolean Check (String s) {

        try{
            // checking valid integer using parseInt() method
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
