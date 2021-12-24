package com.Caster.kitSoft.UI;

import com.Caster.kitSoft.MainWindow;
import com.Caster.kitSoft.texCompiler.TexCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Component
public class TopBarActionListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File destination = new File("C:/tmp.tex");
            if(destination.exists()){
                destination.delete();
            }
            PrintWriter printWriter = new PrintWriter(destination);
            printWriter.print(MainWindow.context.getBean(MainTextAreaPanel.class).getText());
            printWriter.flush();
            printWriter.close();
            TexCompiler.startBuild(destination.getAbsolutePath().replaceAll("\\\\","/"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
