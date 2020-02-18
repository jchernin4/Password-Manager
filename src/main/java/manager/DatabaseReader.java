package manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

public class DatabaseReader {
    private File database;
    private BufferedReader reader;

    public DatabaseReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public DatabaseReader(File database) {
        this.database = database;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void setDatabase(File database) {
        this.database = database;
    }

    public void createNewDatabase() throws IOException {
        File db = new File("./src/main/java/manager/databases/Passwords.pm");
        db.createNewFile();

        HashMap<String, Object> masterPassword = new HashMap<String, Object>();
        HashMap<String, Object> obj = new HashMap<String, Object>();

        System.out.print("Enter a password to be the master password for your manager. ");
        String masterPass = reader.readLine();
        String salt = BCrypt.gensalt(10);

        // masterPassword.put("Salt", salt);
        // masterPassword.put("Pass", BCrypt.hashpw(masterPass, salt));

        obj.put("Master Password", masterPass);

        /*
         * JSONArray misc = new JSONArray(); misc.add("Temp Password");
         * misc.add("Temp Password 2"); obj.put("Misc", misc);
         */

        JSONObject objJSON = new JSONObject(obj);

        String str = objJSON.toJSONString();

        FileOutputStream outputStream = new FileOutputStream(db);
        outputStream.write(str.getBytes());
        outputStream.close();
    }
}