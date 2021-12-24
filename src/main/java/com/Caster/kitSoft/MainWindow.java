package com.Caster.kitSoft;

import com.Caster.kitSoft.UI.MainTextAreaPanel;
import com.Caster.kitSoft.UI.TopBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


@Component
public class MainWindow extends JFrame {

    public static ApplicationContext context;

    @Autowired
    MainTextAreaPanel mainTextAreaPanel;
    @Autowired
    TopBar topBar;


    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }

    public MainWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super();
        this.setSize(800,600);
        this.setPreferredSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

        pack();
        this.setVisible(true);
    }

    @PostConstruct
    public void init() {
        add(topBar);
        add(mainTextAreaPanel);
        revalidate();
    }
}
