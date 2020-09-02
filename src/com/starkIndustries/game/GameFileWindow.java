package com.starkIndustries.game;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFileWindow extends JFrame {
    //extract json data
    //resume game in startWindow
    //list of games to resume parse data from gameFile.json and make list
    //pass in list of strings
    //make btns with title of strings from list
    //add eventlistner to btn
    //write method to do above
    public static List<JButton> createBtns(List<String> list ){
        List<JButton> theList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            JButton button = new JButton(list.get(i));
            theList.add(button);

        }
        return theList;

    }


}
