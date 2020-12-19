package net.badbird5907.aetheriacore.shared.storage.json;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JSONUtils {

    /**
     * Reads an InputStream and creates a JSONObject
     *
     * @param is             InputStream to create the JSONObject from
     * @return               JSONObject of the InputStream
     * @throws IOException   Throws an exception if something goes wrong
     */
    public static JSONObject read(InputStream is) throws IOException {
        String fileData = IOUtils.toString(is);
        if(fileData.length() < 2)
            fileData = "{}";
        is.close();
        return new JSONObject(fileData);
    }

    /**
     * Writes a JSONObject to and OutputStream
     *
     * @param jsonObject     JSONObject to write
     * @param os             OutputStream to write the JSONObject to
     * @throws IOException   Throws an exception if something goes wrong
     */
    public static void write(JSONObject jsonObject, OutputStream os) throws IOException {
        byte[] bytes = jsonObject.toString().getBytes();
        os.write(bytes);
        os.close();
        os.flush();
    }
}
