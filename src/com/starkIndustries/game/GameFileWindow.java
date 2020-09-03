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

public class GameFileWindow extends JFrame {

    private JLayeredPane layeredPane;
    private ImageIcon bgImg;
    private JPanel bgPanel;
    private JPanel resumePanel;
    private JLabel bgLabel;
    private JLabel resumeLabel;

    public void generateWindow(){
        layeredPane = new JLayeredPane();
        bgImg = new ImageIcon("resources/StartGameBackground.jpg");
        bgPanel = new JPanel();
        bgPanel.setBounds(0, 0,bgImg.getIconWidth(),bgImg.getIconHeight());
        bgLabel = new JLabel(bgImg);
        bgPanel.add(bgLabel);


        // make resumePanel to hold the buttons
        resumePanel = new JPanel();
        resumePanel.setBounds(300, 300, 400, 200);


        resumeLabel = new JLabel();
        resumeLabel.setText("Here are your saved games:");
        resumeLabel.setBounds(100, 100, 400, 200);
        resumeLabel.setBackground(Color.ORANGE);
        resumeLabel.setOpaque(true);


        //getting the list of file names from json file
        List<String> fileNames = readJsonFile();

        //making a bunch of buttons from fileNames list
        List<JButton> buttonsList = createBtns(fileNames);


        // adding the buttons from the list to add to the button
        for (JButton button : buttonsList) {
            button = new JButton(button.getText());
            button.setSize(100, 100);
            resumePanel.add(button);
        }



        layeredPane.add(bgPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(resumeLabel, JLayeredPane.MODAL_LAYER);
        layeredPane.add(resumePanel, JLayeredPane.MODAL_LAYER);



        this.setLayeredPane(layeredPane);
        this.setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    //extract json data and feed it into createBtns method
    public static List<String> readJsonFile() {
        List<String> keyList = null;
        try {
            JsonElement gameFile = JsonParser.parseReader(new FileReader("resources/gameFile.json"));
            JsonObject gameFileObj = gameFile.getAsJsonObject();
            Set<String> keySet = gameFileObj.keySet();
            keyList = new ArrayList<>(keySet);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return keyList;
    }



    //resume game in startWindow


    //list of games to resume parse data from gameFile.json and make list




    //make btns with title of strings from list
    //pass in list of strings
    public static List<JButton> createBtns(List<String> list ){
        List<JButton> theList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            JButton button = new JButton(list.get(i));
            theList.add(button);
        }
        return theList;
    }


    //add eventlistner to btn
}
