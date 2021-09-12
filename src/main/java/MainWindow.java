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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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


        MainTextAreaPanel mainTextAreaPanel = new MainTextAreaPanel();
        mainTextAreaPanel.setVisible(true);
        mainTextAreaPanel.setBackground(Color.BLACK);
        mainTextAreaPanel.setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.decode("#212121")));

        TopBar topBar = new TopBar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File destination = new File("C:/tmp.tex");
                    PrintWriter printWriter = new PrintWriter(destination);
                    printWriter.print(mainTextAreaPanel.mainTextArea.getText());
                    printWriter.flush();
                    printWriter.close();
                    TexCompiler.startBuild(destination.getAbsolutePath().replaceAll("\\\\","/"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        mainTextAreaPanel.setMaximumSize(new Dimension(1000000,this.getHeight() - topBar.getHeight() ));

        this.add(topBar);
        this.add(mainTextAreaPanel);

        this.setVisible(true);
    }
}
