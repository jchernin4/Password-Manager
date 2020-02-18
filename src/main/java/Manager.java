import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Manager {
    private static FileOutputStream outputStream;

    public static void main(String[] args) throws IOException {
        File databases = new File("./databases");
        String[] files = databases.list();

        File db;
        if (!Arrays.asList(files).contains("Passwords.pm")) {
            db = new File("./databases/Passwords.pm");
            db.createNewFile();
        } else {
            db = new File("./databases/Passwords.pm");
        }

        JSONObject obj = new JSONObject();
        //obj.put("Name", "Crunchify.com");
 
        JSONArray misc = new JSONArray();
        misc.add("Temp Password");
        misc.add("Temp Password 2");
        obj.put("Misc", misc);

        String str = "";

        outputStream = new FileOutputStream(db);
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }
}