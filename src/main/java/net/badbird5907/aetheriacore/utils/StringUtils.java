package net.badbird5907.aetheriacore.utils;

public class StringUtils {
    public static String arraytoString(String[] args){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < args.length; i++) {
            sb.append(args[i]);
        }
        return sb.toString();
    }
}
