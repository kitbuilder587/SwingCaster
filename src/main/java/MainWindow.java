import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Random;

public class MainWindow extends JFrame {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }

    public MainWindow(){
        super();
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        MainTextAreaPanel mainTextAreaPanel = new MainTextAreaPanel();
        mainTextAreaPanel.setSize(200,200);
        mainTextAreaPanel.setVisible(true);
        this.add(mainTextAreaPanel);

        this.setVisible(true);
    }
}
