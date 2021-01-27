package net.badbird5907.aetheriacore.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.UUID;

public class PlayerUtils {
    public static String UUIDToName(UUID uuid){
        String response = null;
        Boolean next;
        try {
            response = new WebRequest().getText("https://minecraft-api.com/api/pseudo/" + uuid);
            next = true;
        } catch (IOException e) {
            e.printStackTrace();
            next = false;
        }
        if(next){
            if(response == "Player not found !"){
                return null;
            }
            return response;
        }
        return null;
    }
    public static String UUIDToName(String uuid){
        String response = null;
        Boolean next;
        try {
            response = new WebRequest().getText("https://minecraft-api.com/api/pseudo/" + uuid);
            next = true;
        } catch (IOException e) {
            e.printStackTrace();
            next = false;
        }
        if(next){
            if(response == "Player not found !"){
                return null;
            }
            return response;
        }
        return null;
    }
    public static String NameToUUID(String uuid){
        String response = null;
        Boolean next;
        try {
            response = new WebRequest().getText("https://minecraft-api.com/api/uuid/" + uuid);
            next = true;
        } catch (IOException e) {
            e.printStackTrace();
            next = false;
        }
        if(next){
            if(response == "Player not found !"){
                return null;
            }
            return response;
        }
        return null;
    }
    public static String NameToUUID(UUID uuid){
        String response = null;
        Boolean next;
        try {
            response = new WebRequest().getText("https://minecraft-api.com/api/uuid/" + uuid.toString());
            next = true;
        } catch (IOException e) {
            e.printStackTrace();
            next = false;
        }
        if(next){
            if(response == "Player not found !"){
                return null;
            }
            return response;
        }
        return null;
    }
}
