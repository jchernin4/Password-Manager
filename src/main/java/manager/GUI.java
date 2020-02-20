package manager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem createNewDB;

    public GUI() {
        frame = new JFrame();
        menuBar = new JMenuBar();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("./src/main/java/manager/resources/InfiniumLogo.png").getImage());

        frame.setTitle("Infinium's Password Manager");

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription("Menu for accessing database files");
        menuBar.add(fileMenu);

        createNewDB = new JMenuItem("Create new database", KeyEvent.VK_C);
        createNewDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        createNewDB.getAccessibleContext().setAccessibleDescription("Creates a new database file");
        fileMenu.add(createNewDB);

        selectDB = new JMenuItem("Select Database", KeyEvent.VK_S);
        selectDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        selectDB.getAccessibleContext().setAccessibleDescription("Browse for a new database file");
        fileMenu.add(selectDB);

        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setSize(500, 400);

        frame.setVisible(true);

        /*
         * //a group of JMenuItems menuItem = new JMenuItem("A text-only menu item",
         * KeyEvent.VK_T); menuItem.setAccelerator(KeyStroke.getKeyStroke(
         * KeyEvent.VK_1, ActionEvent.ALT_MASK));
         * menuItem.getAccessibleContext().setAccessibleDescription(
         * "This doesn't really do anything"); menu.add(menuItem);
         * 
         * menuItem = new JMenuItem("Both text and icon", new
         * ImageIcon("images/middle.gif")); menuItem.setMnemonic(KeyEvent.VK_B);
         * menu.add(menuItem);
         * 
         * menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
         * menuItem.setMnemonic(KeyEvent.VK_D); menu.add(menuItem);
         * 
         * //a group of radio button menu items menu.addSeparator(); ButtonGroup group =
         * new ButtonGroup(); rbMenuItem = new
         * JRadioButtonMenuItem("A radio button menu item");
         * rbMenuItem.setSelected(true); rbMenuItem.setMnemonic(KeyEvent.VK_R);
         * group.add(rbMenuItem); menu.add(rbMenuItem);
         * 
         * rbMenuItem = new JRadioButtonMenuItem("Another one");
         * rbMenuItem.setMnemonic(KeyEvent.VK_O); group.add(rbMenuItem);
         * menu.add(rbMenuItem);
         * 
         * //a group of check box menu items menu.addSeparator(); cbMenuItem = new
         * JCheckBoxMenuItem("A check box menu item");
         * cbMenuItem.setMnemonic(KeyEvent.VK_C); menu.add(cbMenuItem);
         * 
         * cbMenuItem = new JCheckBoxMenuItem("Another one");
         * cbMenuItem.setMnemonic(KeyEvent.VK_H); menu.add(cbMenuItem);
         * 
         * //a submenu menu.addSeparator(); submenu = new JMenu("A submenu");
         * submenu.setMnemonic(KeyEvent.VK_S);
         * 
         * menuItem = new JMenuItem("An item in the submenu");
         * menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_2,
         * ActionEvent.ALT_MASK)); submenu.add(menuItem);
         * 
         * menuItem = new JMenuItem("Another item"); submenu.add(menuItem);
         * menu.add(submenu);
         * 
         * //Build second menu in the menu bar. menu = new JMenu("Another Menu");
         * menu.setMnemonic(KeyEvent.VK_N);
         * menu.getAccessibleContext().setAccessibleDescription(
         * "This menu does nothing"); menuBar.add(menu);
         * 
         * ... frame.setJMenuBar(theJMenuBar);
         */
    }
}
