import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MainTextAreaPanel extends JPanel {
    JTextPane mainTextArea;
    JScrollPane mainTextAreaScroll;
    JLabel label;
    JPanel scrollContent;

    MainTextAreaPanel(){
        scrollContent = new JPanel();
        mainTextArea = new JTextPane();
        label = new JLabel("<html>lol <br> lol");
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.LINE_AXIS));
        scrollContent.add(label);
        scrollContent.add(mainTextArea);
        scrollContent.setVisible(true);

        label.setOpaque(true);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setMaximumSize(new Dimension(10,10000090));
        label.setBackground(Color.decode("#303030"));
        label.setForeground(Color.decode("#606060"));
        label.setFont(new Font("Consolas",Font.PLAIN,16));
        Border margin = new EmptyBorder(4,10,10,10);
        label.setBorder(new CompoundBorder(label.getBorder(), margin));

        mainTextArea.setBackground(Color.decode("#262626"));

        mainTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Runnable doHighlight = new Runnable() {
                    @Override
                    public void run() {
                        SimpleAttributeSet attr = new SimpleAttributeSet();
                        StyleConstants.setForeground(attr,Color.WHITE);
                        StyleConstants.setFontFamily(attr,"Consolas");
                        StyleConstants.setFontSize(attr,16);
                        mainTextArea.getStyledDocument().setCharacterAttributes(0,mainTextArea.getText().length(),attr,true);

                    }
                };
                SwingUtilities.invokeLater(doHighlight);

                label.setText(StringHelper.getStringFrom1ToN(StringHelper.getLinesCount(mainTextArea.getText())));

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                label.setText(StringHelper.getStringFrom1ToN(mainTextArea.getText().split("\n").length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        mainTextAreaScroll = new JScrollPane(scrollContent);
        this.add(mainTextAreaScroll);
        mainTextArea.setSize(200,200);
        this.setVisible(true);
    }
}
