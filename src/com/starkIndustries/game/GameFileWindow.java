package com.starkIndustries.game;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// class that builds the window to display previously saved games
public class GameFileWindow extends JFrame {

    private JLayeredPane layeredPane;
    private ImageIcon bgImg = new ImageIcon("resources/StartGameBackground.jpg");

    // panels
    private JPanel bgPanel;
    private JPanel buttonsPanel;

    // labels
    private JLabel bgLabel;
    private JLabel greetingLabel;

    // generate a window
    public void generateWindow(){

        // bring in a layered pane
        layeredPane = this.makeLayeredPane();

        this.setLayeredPane(layeredPane);
        this.setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    // make a layered pane
    public JLayeredPane makeLayeredPane() {
        JLayeredPane result = new JLayeredPane();

        // bring in a background panel
        bgPanel = this.makeBackgroundPanel();

        // bring in a buttons panel
        buttonsPanel = this.makeButtonsPanel();

        // bring in a greeting label
        greetingLabel = this.makeGreetingLabel();

        // "assemble" the layered pane
        result.add(bgPanel, JLayeredPane.DEFAULT_LAYER);
        result.add(greetingLabel, JLayeredPane.MODAL_LAYER);
        result.add(buttonsPanel, JLayeredPane.MODAL_LAYER);
        return result;
    }

    // make a background panel
    public JPanel makeBackgroundPanel() {
        JPanel result = new JPanel();
        result.setBounds(0, 0,bgImg.getIconWidth(),bgImg.getIconHeight());

        // make a new label and add it to the background panel
        bgLabel = new JLabel(bgImg);
        result.add(bgLabel);
        return result;
    }

    // make buttons panel
    public JPanel makeButtonsPanel() {
        JPanel result = new JPanel();
        result.setBounds(100, 300, 400, 200);

        // getting the list of file names from json file
        List<String> fileNames = readJsonFile();

        // making a bunch of buttons from fileNames list
        List<JButton> buttonsList = createBtns(fileNames);

        // adding the buttons with their names, and putting it onto the panel
        for (JButton button : buttonsList) {
            button = new JButton(button.getText());
            button.setSize(100, 100);
            result.add(button);
        }
        return result;
    }

    // make greeting label
    public JLabel makeGreetingLabel() {
        JLabel result = new JLabel();
        result.setText("Here are your saved games:");
        result.setBounds(100, 100, 400, 200);
        result.setBackground(Color.ORANGE);
        result.setOpaque(true);

        return result;
    }

    // extract json data and feed it into createBtns method
    public List<String> readJsonFile() {
        List<String> result = null;
        try {
            JsonElement gameFile = JsonParser.parseReader(new FileReader("resources/gameFile.json"));
            JsonObject gameFileObj = gameFile.getAsJsonObject();
            Set<String> keySet = gameFileObj.keySet();
            result = new ArrayList<>(keySet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    // make btns with title of strings from list
    public List<JButton> createBtns(List<String> list ){
        List<JButton> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            JButton button = new JButton(list.get(i));
            result.add(button);
        }
        return result;
    }
}
