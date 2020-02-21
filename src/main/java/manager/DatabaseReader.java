package manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;

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

    public void createNewDatabase() throws IOException, NoSuchAlgorithmException {
        File db = new File("./src/main/java/manager/databases/Passwords.pm");
        db.createNewFile();

        HashMap<String, Object> masterPassword = new HashMap<String, Object>();
        HashMap<String, Object> obj = new HashMap<String, Object>();

        // System.out.print("Enter a password to be the master password for your
        // manager. ");

        SecretKeySpec masterPass;
        masterPass = stringToKey(reader.readLine());

        obj.put("Master Password", masterPass);

        /*
         * JSONArray misc = new JSONArray(); misc.add("Temp Password");
         * misc.add("Temp Password 2"); obj.put("Misc", misc);
         */

        JSONObject objJSON = new JSONObject(obj);
        String str = objJSON.toJSONString();

        String headerString = "--- Infinium Password Manager File DO NOT edit this header. Doing so will result in data corruption. ---";

        FileOutputStream outputStream = new FileOutputStream(db);

        outputStream.write(headerString.getBytes());
        outputStream.write(str.getBytes());
        outputStream.close();
    }

    public String encryptString(String str, SecretKeySpec key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
    }

    public String decryptString(String str, SecretKeySpec key) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
    }

    public SecretKeySpec stringToKey(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest sha = null;
        byte[] key = str.getBytes("UTF-8");

        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);

        return new SecretKeySpec(key, "AES");
    }
}