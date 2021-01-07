package net.badbird5907.aetheriacore.spigot.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.badbird5907.aetheriacore.spigot.AetheriaCore;

import java.io.*;

public class GsonManager {
    private static Gson gson;
    public static void init(){gson = new GsonBuilder().setPrettyPrinting().create();}
    public static Gson getGson(){return gson;}
    public static void write(Object o, FileWriter file){
        String output = gson.toJson(o);
        DebugLogger.DebugLog(output);
    }
    public static void write(Object o, File file){
        try {
            Writer writer = new FileWriter(file.getAbsolutePath());
            gson.toJson(o, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDir(String dir){
        File d = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath()+ "/" + dir);
        boolean direxists = d.mkdirs();
    }
    public static void createFile(String directory, String filename){
        if(AetheriaCore.getInstance().getDataFolder().exists()){
            File file = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/" + directory + "/" + filename);
            if(file.exists())
                return;
            else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static File getFile(String directory, String filename){
        if(AetheriaCore.getInstance().getDataFolder().exists()){
            File file = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/" + directory + "/" + filename );
            if(file.exists())
                return file;
            else
                return null;
        }
        else return null;
    }
    public static boolean fileExists(String name){
        if (AetheriaCore.getInstance().getDataFolder().exists()) {
            File file = new File(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/" + name);
            if(file.exists())
                return true;
            else return false;
        }
        else
            return false;
    }
    public static boolean dirExists(String dir){
        File d = new File(dir);
        return d.exists();
    }
    public static void writeToFile(String directory, String filename, Object data){
        if(AetheriaCore.getInstance().getDataFolder().exists()){
            if(fileExists(directory + "/" + filename)){
                try (Writer writer = new FileWriter(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/" + directory + "/" + filename)){
                    writer.write(data.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                return;
        }
    }
    public static FileReader readFile(String directory, String filename){
        if(AetheriaCore.getInstance().getDataFolder().exists()){
            try{
                FileReader fileReader = new FileReader(AetheriaCore.getInstance().getDataFolder().getAbsolutePath() + "/" + directory + "/" + filename);
                return fileReader;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        else return null;
    }
}
