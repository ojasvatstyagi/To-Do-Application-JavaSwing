import javax.swing.*;
import java.awt.*;

public class NewLabel extends JLabel {

    public NewLabel(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(image));
        setBounds(0, 0, width, height);
    }

    public static void add(JPanel panel) {
        // Add your JLabel or any other components to the JPanel
        panel.add(new NewLabel("icon/todobg.jpg", 1580, 1000));
    }
}

