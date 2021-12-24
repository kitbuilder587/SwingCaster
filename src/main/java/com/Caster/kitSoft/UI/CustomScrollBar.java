package com.Caster.kitSoft.UI;

import com.Caster.kitSoft.UI.Colors;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public class CustomScrollBar extends JScrollBar {
    public CustomScrollBar(ComponentUI ui ) {
        setUI(ui);
        setPreferredSize(new Dimension(8,8));
        setForeground(Colors.lightgrey);
        setBackground(Colors.deepDark);
    }
}
