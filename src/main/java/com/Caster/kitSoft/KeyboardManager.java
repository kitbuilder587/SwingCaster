package com.Caster.kitSoft;

import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class KeyboardManager {
    private ArrayList<CustomKeyListener> keyListeners;
    public KeyboardManager() {
        keyListeners = new ArrayList<>();
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        boolean res =false;
                        if(e.getID() == KeyEvent.KEY_PRESSED){
                            for(CustomKeyListener listener : keyListeners){
                                if(listener.keyPressed(e)){
                                    res = true;
                                }
                            }
                        }
                        if(e.getID() == KeyEvent.KEY_TYPED){
                            for(CustomKeyListener listener : keyListeners){
                                if(listener.keyTyped(e)){
                                    res = true;
                                }
                            }
                        }
                        if(e.getID() == KeyEvent.KEY_RELEASED){
                            for(CustomKeyListener listener : keyListeners){
                                if(listener.keyReleased(e)){
                                    res = true;
                                }
                            }
                        }
                        return res;
                    }
                });
    }

    public void addKeyListener(CustomKeyListener keyListener){
        keyListeners.add(keyListener);
    }
}
