import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class MainWindow extends JFrame {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }

    public MainWindow(){
        super();
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));


        JPanel topBar = new JPanel();
        topBar.setBackground(Color.decode("#303030"));
      //  topBar.setMinimumSize(new Dimension(20,30));
        topBar.setMaximumSize(new Dimension(10000,30));
        topBar.setLayout(new BoxLayout(topBar,BoxLayout.X_AXIS));
        topBar.add(Box.createHorizontalGlue());
        IconButton button = new IconButton(null);

        button.setMaximumSize(new Dimension(30,40));
        try {
            BufferedImage icon = ImageIO.read(this.getClass().getResource("icons/run.png"));
            button.setIconButton(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        topBar.setBorder(BorderFactory.createEmptyBorder());
        topBar.add(button,BorderLayout.CENTER);
        this.add(topBar);

        MainTextAreaPanel mainTextAreaPanel = new MainTextAreaPanel();
        mainTextAreaPanel.setVisible(true);
        mainTextAreaPanel.setMaximumSize(new Dimension(1000000,this.getHeight() - topBar.getHeight() ));
        mainTextAreaPanel.setBackground(Color.BLACK);
        mainTextAreaPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.decode("#212121")));
        this.add(mainTextAreaPanel);

        this.setVisible(true);
    }
}
