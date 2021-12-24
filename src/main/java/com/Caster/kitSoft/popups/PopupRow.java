package com.Caster.kitSoft.popups;

import com.Caster.kitSoft.UI.Colors;
import com.Caster.kitSoft.UI.FontFactory;

import javax.swing.*;
import java.awt.*;

public class PopupRow extends  JPanel{
    private JLabel label;
    public void setActive(){
        setBackground(Colors.deepDark);
    }
    public void setDisabled(){
        setBackground(Colors.dark);
    }
    public PopupRow(int width,String text) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(width,20));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        label = new JLabel(text);
        label.setFont(FontFactory.getDefaultFont());
        label.setForeground(Colors.lightgrey);
        setDisabled();
        add(label);
    }
    public String getText(){
        return label.getText();
    }

}
