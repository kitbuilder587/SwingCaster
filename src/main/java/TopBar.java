import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TopBar extends JPanel {

    IconButton button;
    ActionListener onRunButtonListener;

    public TopBar(ActionListener onRunButtonListener) {
        this.setBackground(Color.decode("#303030"));
        this.setMaximumSize(new Dimension(10000,30));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(Box.createHorizontalGlue());
        button = new IconButton(null);
        button.addActionListener(onRunButtonListener);
        button.setMaximumSize(new Dimension(30,40));
        try {
            BufferedImage icon = ImageIO.read(this.getClass().getResource("icons/run.png"));
            button.setIconButton(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBorder(BorderFactory.createEmptyBorder());
        this.add(button,BorderLayout.CENTER);
    }
}
