package com.Caster.kitSoft.popups;

import com.Caster.kitSoft.CustomKeyListener;
import com.Caster.kitSoft.UI.Colors;
import com.Caster.kitSoft.KeyboardManager;
import com.Caster.kitSoft.KeywordsFactory;
import com.Caster.kitSoft.MainWindow;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static javax.swing.ScrollPaneConstants.*;

public class PopupAutocomplete implements CustomKeyListener{
    KeyboardManager manager;
    JFrame owner;
    JPanel panel;
    JScrollPane mainContainer;
    Popup popup;
    String[] keywords;
    int activeRow=-1;
    boolean isHided = false;
    ArrayList<PopupRow> rows;
    MainTextAreaHolder mainTextAreaHolder;
    public PopupAutocomplete(MainTextAreaHolder mainTextAreaHolder,JFrame owner,KeyboardManager manager,int x,int y) {
        this.owner = owner;
        repaint(null);
        this.mainTextAreaHolder = mainTextAreaHolder;
        PopupFactory pf = PopupFactory.getSharedInstance();
        popup = pf.getPopup(mainContainer,mainContainer,x,y);
        popup.show();
        keywords = KeywordsFactory.getKeywords();
        this.manager = manager;
        manager.addKeyListener(this);
    }

    private void repaint(String[] currentKeywords) {
        int width=420,height=350;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Colors.dark);
        panel.setMinimumSize(new Dimension(width,height));
        rows = new ArrayList<>();
        if(currentKeywords != null){
            for(String keyword : currentKeywords){
                PopupRow row = new PopupRow(width,keyword);
                rows.add(row);
                panel.add(row);
            }
        }
        activeRow = -1;
        mainContainer = new JScrollPane(panel);
        mainContainer.setSize(width,height);
        mainContainer.setPreferredSize(new Dimension(width,height));
        mainContainer.setMaximumSize(new Dimension(width,height));
        mainContainer.getViewport().getView().setBackground(Colors.dark);
        mainContainer.setBorder(new EmptyBorder(0,0,0,0));
        mainContainer.getVerticalScrollBar().setUnitIncrement(16);
        mainContainer.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

    }

    public void updatePopup(int x, int y,String text){

        Popup curPopup = popup;
        String[] currentKeywords = getCurrentTokens(text);
        if(currentKeywords.length != 0 && !(currentKeywords.length ==1 && currentKeywords[0].equals(getTokenForDot()))) {
            repaint(currentKeywords);
            PopupFactory pf = PopupFactory.getSharedInstance();
            Popup newPopup = pf.getPopup(null, mainContainer, x, y);
            newPopup.show();
            curPopup.hide();
            isHided = false;
            popup = newPopup;
        }else{
            curPopup.hide();
            isHided =true;
        }
    }

    public String getTokenForDot(){
        int dot = mainTextAreaHolder.getCaretPosition()-1;
        String text = mainTextAreaHolder.getText();
        if(text.length() == 0) return "";
        int i;
        for(i=dot;i>=0;i--){
            if(text.charAt(i) == ' ' || text.charAt(i) == '\n'){
                break;
            }
        }
        i++;
        int j;
        for(j=dot;j<text.length();j++){
            if(text.charAt(j) == ' ' || text.charAt(j) == '\n'){
                break;
            }
        }
        if(i<j){
            return text.substring(i,j);
        }else{
            return "";
        }
    }

    public String[] getCurrentTokens(String newText){
        String[] tokens = newText.split("( )|(\n)");
        String lastToken;
        if(newText.length() == 0  || newText.charAt(newText.length()-1) == ' ' || newText.charAt(newText.length()-1) == '\n'){
            lastToken = "";
        }else {
            lastToken = tokens[tokens.length - 1];
            if(lastToken.charAt(0) == '\n'){
                lastToken = lastToken.substring(1);
            }
        }

        ArrayList<String> rightTokens = new ArrayList<>();
        for (String keyword : keywords) {
            if(keyword.contains(lastToken)){
                rightTokens.add(keyword);
            }
        }
        if(rightTokens.size() != 0 && !lastToken.equals("")) {
            return rightTokens.toArray(new String[rightTokens.size()]);
        }
        else {
            return new String[0];
        }
    }

    @Override
    public boolean keyTyped(KeyEvent e) {
        return false;
    }

    @Override
    public boolean keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN && !isHided){
            updateActiveRow(false);
            return true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP && !isHided){
            updateActiveRow(true);
            return true;
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER && activeRow != -1 && !isHided){
            insertNewToken();

            return true;
        }

        return false;
    }

    private void insertNewToken() {
        int dot = mainTextAreaHolder.getCaretPosition()-1;
        String text = mainTextAreaHolder.getText();
        int i;
        for(i=dot;i>=0;i--){
            if(text.charAt(i) == ' ' || text.charAt(i) == '\n'){
                break;
            }
        }
        i++;
        if(dot == text.length()-1){
            mainTextAreaHolder.setText(text.substring(0,i) + rows.get(activeRow).getText() + " ");
        }else {
            mainTextAreaHolder.setText(text.substring(0, i) + rows.get(activeRow).getText() + " " + text.substring(dot+1));
        }
        mainTextAreaHolder.setCaretPosition(dot + rows.get(activeRow).getText().length());
    }

    private void updateActiveRow(boolean isUp) {

        int newActiveRow,disablingIndex,enablingIndex;
        if(isUp) {
            newActiveRow =(activeRow - 1 + rows.size()) % rows.size();
            disablingIndex = 0;
            enablingIndex = rows.size()-1;
        }else {
            newActiveRow = (activeRow + 1) % rows.size();
            disablingIndex = rows.size()-1;
            enablingIndex = 0;
        }

        if(activeRow == -1){
            activeRow = 0;
            rows.get(0).setActive();
        }else if(activeRow == rows.size()-1){
            activeRow = 0;
            rows.get(disablingIndex).setDisabled();
            rows.get(enablingIndex).setActive();
        }else{
            rows.get(activeRow).setDisabled();
            rows.get(newActiveRow).setActive();
        }

        activeRow = newActiveRow;
        double scroollSize = mainContainer.getVerticalScrollBar().getMaximum();
        double viewportSize = mainContainer.getVerticalScrollBar().getVisibleAmount();
        double visibleCards = viewportSize / rows.get(0).getHeight();
        int yScroll = (int) Math.max(scroollSize * activeRow / rows.size() - (viewportSize * (1.0 - 3.0/visibleCards)),0);
        mainContainer.getViewport().setViewPosition(new Point(0,yScroll));
        owner.revalidate();
    }

    @Override
    public boolean keyReleased(KeyEvent e) {
        return false;
    }
}
