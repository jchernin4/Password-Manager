package manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Manager {
    private static FileOutputStream outputStream;
    private static DatabaseReader dbReader;
    private static GUI gui;

    private static JFrame frame;

    private static File database;
    private static File saveFolder;

    private static JMenuBar menuBar;
    private static JMenu fileMenu;
    private static JMenuItem createNewDB;
    private static JMenuItem selectDB;
    private static JMenuItem changePasswordItem;

    private static JFileChooser databaseFileChooser;
    private static JFileChooser saveFolderChooser;
    private static JFileChooser generalFileChooser;

    private static FileNameExtensionFilter databaseFilesFilter;
    private static FileNameExtensionFilter saveFileFilter;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        init();
        /*
         * File databases = new File("./src/main/java/manager/databases/"); String[]
         * files = databases.list();
         * 
         * boolean foundDB = false; if (files != null && files.length > 0) { for (int i
         * = 0; i < files.length; i++) { System.out.println(files[i]); if
         * (files[i].endsWith(".pm")) { foundDB = true; } } }
         * 
         * File db; if (foundDB) { db = new File("./databases/Passwords.pm");
         * dbReader.setDatabase(db); } else { dbReader.createNewDatabase(); }
         */
    }

    public static void init() {
        dbReader = new DatabaseReader();
        gui = new GUI();

        databaseFileChooser = new JFileChooser();
        generalFileChooser = new JFileChooser();
        saveFolderChooser = new JFileChooser();

        frame = gui.getFrame();

        initMenuBar();
    }

    /**
     * Initialize Menu bar for GUI
     */
    public static void initMenuBar() {
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription("Menu for accessing database files");

        createNewDB = new JMenuItem("Create New database", KeyEvent.VK_C);
        createNewDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        createNewDB.getAccessibleContext().setAccessibleDescription("Creates a new database file");

        createNewDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                File file = new File(new String());
                try {
                    file = saveFileDialog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!file.getName().equals("NO_FILE_SELECTED")) {
                    System.out.println("Database file saved");
                    setDatabase(file);
                } else {
                    System.out.println("No database file saved");
                }
            }
        });

        selectDB = new JMenuItem("Select Database", KeyEvent.VK_S);
        selectDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        selectDB.getAccessibleContext().setAccessibleDescription("Browse for a new database file");

        selectDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                File file = selectFile();
                if (!file.getName().equals("NO_FILE_SELECTED")) {
                    System.out.println("Database file selected");
                    setDatabase(file);
                } else {
                    System.out.println("No database file selected");
                }
            }
        });

        changePasswordItem = new JMenuItem("Change Master Password", KeyEvent.VK_P);
        changePasswordItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        changePasswordItem.getAccessibleContext()
                .setAccessibleDescription("Change the master password for the selected database file");

        menuBar.add(fileMenu);
        fileMenu.add(createNewDB);
        fileMenu.add(selectDB);
        fileMenu.add(changePasswordItem);

        frame.setJMenuBar(menuBar);
    }

    /**
     * Allows user to select a file
     * 
     * @return Selected file
     */
    public static File selectFile() {
        databaseFileChooser.setCurrentDirectory(new File("."));

        databaseFileChooser.setDialogTitle("Choose a database file");

        databaseFilesFilter = new FileNameExtensionFilter("PasswordManager files", "pm");
        databaseFileChooser.setFileFilter(databaseFilesFilter);

        File selectedFile = new File("NO_FILE_SELECTED");

        int result = databaseFileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = databaseFileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Canceled database file select.");
        }

        return selectedFile;
    }

    public static File selectFolder() {
        saveFolderChooser.setCurrentDirectory(new File("."));
        saveFolderChooser.setDialogTitle("Select a folder to save the new database to");

        saveFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sets search for only folders
        saveFolderChooser.setAcceptAllFileFilterUsed(false); // Does not allow files to be selected

        File selectedFolder = new File("NO_FILE_SELECTED");

        int result = saveFolderChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFolder = saveFolderChooser.getSelectedFile();
            System.out.println("Selected folder: " + saveFolderChooser.getSelectedFile());
        } else {
            System.out.println("Cancelled folder select.");
        }

        return selectedFolder;
    }

    public static File saveFileDialog() throws IOException {
        generalFileChooser.setCurrentDirectory(new File("."));
        generalFileChooser.setDialogTitle("Specify where to save database file");
        generalFileChooser.setSelectedFile(new File("Passwords.pm"));

        saveFileFilter = new FileNameExtensionFilter("PasswordManager files", "pm");
        generalFileChooser.setFileFilter(saveFileFilter);

        File fileToSave = new File("NO_FILE_SELECTED");

        int result = generalFileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileToSave = generalFileChooser.getSelectedFile();
            fileToSave.createNewFile();

            System.out.println("Saved new database file: " + fileToSave.getAbsolutePath());
        } else {
            System.out.println("Cancelled saving new database file.");
        }

        return fileToSave;
    }

    /**
     * Sets the database
     * 
     * @param database Database to set the current database to
     */
    public static void setDatabase(File db) {
        database = db;
    }

    /**
     * Sets the save folder
     * 
     * @param folder Folder to set the current save folder to
     */
    public void setSaveFolder(File folder) {
        saveFolder = folder;
    }
}