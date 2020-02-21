package manager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUI {
    private JFrame frame;

    public GUI() {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("./src/main/java/manager/resources/InfiniumLogo.png").getImage());

        frame.setTitle("Infinium's Password Manager");

        frame.pack();
        frame.setSize(500, 400);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
