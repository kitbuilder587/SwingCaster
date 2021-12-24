package com.Caster.kitSoft.UI;

import com.Caster.kitSoft.UI.IconButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class TopBar extends JPanel {

    IconButton button;
    ActionListener onRunButtonListener;

    @Autowired
    public TopBar(ActionListener onRunButtonListener,JFrame frame) {
        this.setBackground(Color.decode("#303030"));
        this.setPreferredSize(new Dimension(frame.getWidth(),30));
        this.setMaximumSize(new Dimension(10000,30));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(Box.createHorizontalGlue());
        button = new IconButton(null);
        button.addActionListener(onRunButtonListener);
        button.setMaximumSize(new Dimension(30,40));
        try {
            BufferedImage icon = ImageIO.read(this.getClass().getResource("/icons/run.png"));
            button.setIconButton(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBorder(BorderFactory.createEmptyBorder());
        this.add(button,BorderLayout.CENTER);

    }
}
