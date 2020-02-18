package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Manager {
    private static FileOutputStream outputStream;
    private static DatabaseReader dbReader;

    public static void main(String[] args) throws IOException {
        Scanner kb = new Scanner(System.in);

        File databases = new File("./databases/");
        String[] files = databases.list();

        dbReader = new DatabaseReader();

        boolean foundDB = false;
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].equals("Passwords.pm")) {
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
        }

        kb.close();
    }
}