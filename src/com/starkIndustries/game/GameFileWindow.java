package com.starkIndustries.game;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFileWindow extends JFrame {

    private JLayeredPane layeredPane;
    private ImageIcon bgImg;
    private JPanel resumePanel;
    private JLabel resumeLabel;

    public void generateWindow() {
        layeredPane = new JLayeredPane();
        bgImg = new ImageIcon("resources/StartGameBackground.jpg");
        resumePanel = new JPanel();
        resumePanel.setBounds(0, 0,bgImg.getIconWidth(),bgImg.getIconHeight());
        resumeLabel = new JLabel(bgImg);
        resumePanel.add(resumeLabel);

        layeredPane.add(resumePanel, JLayeredPane.DEFAULT_LAYER);


        this.setLayeredPane(layeredPane);
        this.setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    //extract json data
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
