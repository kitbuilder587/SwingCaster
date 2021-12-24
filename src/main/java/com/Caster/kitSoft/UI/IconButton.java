package com.Caster.kitSoft.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class IconButton extends JButton {

    private BufferedImage iconButton;
    private Color background;

    public IconButton(BufferedImage icon) {
        setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.iconButton = icon;

        background = new Color(1f,1f,1f,0.0f);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                background = new Color(1f,1f,1f,0.5f);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                background = new Color(1f,1f,1f,0.0f);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                background = new Color(1f,1f,1f,0.2f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                background = new Color(1f,1f,1f,0.0f);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(background);
        graphics2D.fillRect(0,0,getWidth(),getHeight());

        int imageWidth = iconButton.getWidth(null );
        int imageHeight = iconButton.getHeight(null );
        graphics2D.drawImage(iconButton,(getWidth() / 2) - imageWidth/2,(getHeight() / 2) - imageHeight/2,null);
        System.out.println(getHeight());
        super.paintComponent(graphics);
    }

    public void setIconButton(BufferedImage iconButton) {
        this.iconButton = iconButton;
    }


    public BufferedImage getIconButton() {
        return iconButton;
    }
}
