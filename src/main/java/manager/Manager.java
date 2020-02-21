package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Manager {
    private static FileOutputStream outputStream;
    private static DatabaseReader dbReader;
    private static GUI gui;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        init();

        /*File databases = new File("./src/main/java/manager/databases/");
        String[] files = databases.list();

        boolean foundDB = false;
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]);
                if (files[i].endsWith(".pm")) {
                    foundDB = true;
                }
            }
        }

        File db;
        if (foundDB) {
            db = new File("./databases/Passwords.pm");
            dbReader.setDatabase(db);
        } else {
            dbReader.createNewDatabase();
        }*/
    }

    public static void init() {
        dbReader = new DatabaseReader();
        gui = new GUI();
    }
}