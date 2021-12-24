package com.Caster.kitSoft.UI;

import com.Caster.kitSoft.KeyboardManager;
import com.Caster.kitSoft.StringHelper;
import com.Caster.kitSoft.UI.Colors;
import com.Caster.kitSoft.UI.FontFactory;
import com.Caster.kitSoft.popups.MainTextAreaHolder;
import com.Caster.kitSoft.popups.PopupAutocomplete;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rsyntaxtextarea.modes.LatexTokenMaker;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;

@Component
public class MainTextAreaPanel extends JPanel implements MainTextAreaHolder {
    private RSyntaxTextArea mainTextArea;
    private RTextScrollPane mainTextAreaScroll;
    private JLabel label;
    private JPanel scrollContent;
    private PopupAutocomplete curPopup = null;
    @Autowired
    private KeyboardManager manager;

    @Autowired
    public MainTextAreaPanel(JFrame frame){
        initMainComponents();
        addLinesCounterListener();

        this.setLayout(new BorderLayout());
        this.add(mainTextAreaScroll,BorderLayout.CENTER);
        setUpPopupLogic(frame);

        this.setVisible(true);
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.decode("#212121")));

    }

    private void setUpPopupLogic(JFrame frame) {
        MainTextAreaHolder holder = this;
        mainTextArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Rectangle cp = null;
                try {
                    cp = mainTextArea.getUI().modelToView(mainTextArea,e.getDot())  ;
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
                Point coordsOfPanel = mainTextArea.getLocationOnScreen();
                if(curPopup==null)
                    curPopup = new PopupAutocomplete(holder,frame,manager,cp.x + coordsOfPanel.x,cp.y + coordsOfPanel.y);
                else {
                    Point pp = new Point(cp.x + coordsOfPanel.x,cp.y + coordsOfPanel.y);
                 //   SwingUtilities.convertPointToScreen(pp,mainTextArea);
                    curPopup.updatePopup(pp.x+20,pp.y+20,mainTextArea.getText().substring(0,e.getDot()));

                }
            }
        });
    }

    private void addLinesCounterListener() {
        mainTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

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
    }

    private void initMainComponents() {
        scrollContent = new JPanel();
        mainTextArea = new RSyntaxTextArea();
        label = new JLabel(" ");
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.LINE_AXIS));
        scrollContent.add(label);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.setBackground(Colors.deepDark);
        p.add(mainTextArea);
        p.setBorder(new CompoundBorder(label.getBorder(), new EmptyBorder(4,5,0,0)));
        scrollContent.add(p);
        scrollContent.setVisible(true);

        label.setOpaque(true);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setMaximumSize(new Dimension(10,10000090));
        label.setBackground(Colors.dark);
        label.setForeground(Colors.lightgrey);
        label.setFont(FontFactory.getDefaultFont());
        Border margin = new EmptyBorder(4,10,10,10);
        label.setBorder(new CompoundBorder(label.getBorder(), margin));

        SyntaxScheme scheme = mainTextArea.getSyntaxScheme();
        scheme.getStyle(Token.RESERVED_WORD).foreground = Colors.yellow;
        scheme.getStyle(Token.FUNCTION).foreground = Colors.pink;
        scheme.getStyle(Token.SEPARATOR).foreground = Colors.lightgrey;
        mainTextArea.setAnimateBracketMatching(false);

        mainTextArea.setCaretColor(Colors.white);
        mainTextArea.setAutoIndentEnabled(false);
        mainTextArea.setBackground(Colors.deepDark);
        mainTextArea.setHighlightCurrentLine(false);
        mainTextArea.setFont(FontFactory.getDefaultFont());
        mainTextArea.setForeground(Colors.white);
        mainTextArea.setPaintTabLines(false);
        mainTextArea.setBorder(BorderFactory.createEmptyBorder());
        mainTextArea.setSyntaxScheme(scheme);
        mainTextArea.revalidate();
        mainTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);

        mainTextAreaScroll = new RTextScrollPane(scrollContent);
        mainTextAreaScroll.setLineNumbersEnabled(false);
        mainTextAreaScroll.setBorder(new EmptyBorder(0,0,0,0));
        mainTextAreaScroll.getVerticalScrollBar().setUnitIncrement(16);
        mainTextAreaScroll.getHorizontalScrollBar().setUnitIncrement(16);
    }





    @Override
    public void setText(String text) {
        mainTextArea.setText(text);
        mainTextArea.revalidate();
    }

    @Override
    public String getText() {
        return mainTextArea.getText();
    }

    @Override
    public int getCaretPosition() {
        return mainTextArea.getCaret().getDot();
    }

    @Override
    public void setCaretPosition(int position) {
        mainTextArea.getCaret().setDot(position);
    }


}
