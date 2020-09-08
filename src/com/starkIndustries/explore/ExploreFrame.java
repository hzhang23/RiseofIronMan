package com.starkIndustries.explore;

import com.starkIndustries.game.CloseWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExploreFrame extends JFrame {
    //game control boolean
    boolean isStart = false; // whether the game is start
    boolean isEquipmentExist = false; // whether this is an equipment in the room
    //map config
    boolean isCenter; // cave on map
    boolean isNorth; // cctv room on map
    boolean isSouth; // outside ground on map
    boolean isWest; // Armory on map
    boolean isEast; // TenRings living room on map

    //Tony config
    Tony tony = new Tony("Tony Stark");
    ArrayList<Equipment> arcList = tony.getEquipments();
    Equipment titanium = null;
    Equipment missile = null;

    //game control elements & GUI
    int stepCount = 0; //killed by TenRings if walk too much
    JButton buttonUp, buttonDown, buttonLeft, buttonRight;
    JButton buttonPickup, buttonCheckBag;
    JTextArea gameArea;
    String description;

    //TODO: game running, remove after refactor
    public static void main(String[] args) {
        new ExploreFrame();
    }

    //TODO: MainFrame set up
    public ExploreFrame(){
        /** welcome Text show
         */
        gameArea = new JTextArea();
        gameArea.setEditable(false);
        gameArea.setPreferredSize(new Dimension(800,350));
        gameArea.setLineWrap(true);
        gameArea.setText("\n\t Hi, Tony Stark, in this mod, you will need to find all materials to build your Arc " +
                "Reactor\n");
        gameArea.append("\n\t Please press ok to start");

        /**  set control panel
         */
        JPanel controlPanel = new JPanel();
        controlPanel.setSize(500,500);
        controlPanel.setOpaque(false);
        controlPanel.setLayout(new BorderLayout());
        Dimension buttonDims = new Dimension(60,50);
        buttonUp = new JButton("UP");
        buttonDown = new JButton("DOWN");
        buttonLeft = new JButton("LEFT");
        buttonRight = new JButton("RIGHT");
        buttonUp.setPreferredSize(buttonDims);
        buttonDown.setPreferredSize(buttonDims);
        buttonLeft.setPreferredSize(buttonDims);
        buttonRight.setPreferredSize(buttonDims);
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(buttonDims);
        controlPanel.add(okButton,BorderLayout.CENTER);
        controlPanel.add(buttonUp,BorderLayout.NORTH);
        controlPanel.add(buttonLeft,BorderLayout.WEST);
        controlPanel.add(buttonDown,BorderLayout.SOUTH);
        controlPanel.add(buttonRight,BorderLayout.EAST);

        /** set check panel
         */
        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new GridLayout(1,2));
        Dimension checkBtnDim = new Dimension(60, 100);
        buttonCheckBag = new JButton("Check Bag");
        buttonPickup = new JButton("Pick Up");
        buttonCheckBag.setPreferredSize(checkBtnDim);
        buttonPickup.setPreferredSize(checkBtnDim);
        checkPanel.add(buttonCheckBag);
        checkPanel.add(buttonPickup);

        //DONE: set event listener for all buttons
        /** start button events
         */
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startExplore();
            }
        });
        /** pick up button event
         */
        buttonPickup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //TODO: method to pick equipment
            }
        });
        /** check bag button event
         */
        buttonCheckBag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBag(tony);
            }
        });

        /** direction button event
         */
        buttonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goUp();
            }
        });

        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goLeft();
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goRight();
            }
        });

        buttonDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goDown();
            }
        });

        //TODO: config Frame layout
        this.add(gameArea, BorderLayout.NORTH);
        this.add(controlPanel, BorderLayout.CENTER);
        this.add(checkPanel,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //TODO startPlay
    public void startExplore(){
        if(!isStart){
        tony = new Tony("Tony Stark");
        isStart = true;
        isCenter = true;
        description = "\tYou realize the car battery that keep your alive will be completed dry in 10 days. \n";
        description += "\t" + tony.getStatus();
        description += "\tyou look around the cave, you see there is a dark room on the west side of the cave, a " +
                "terrorist walk out the dark room with a new rifle that made by your company on his hand. \n";
        description += "\ton the North side, there is huge glass windows and people inside that room could see " +
                "everything about this cave. you could also see several monitors layout in that room. \n";
        description += "\tthere is also another cave on the East side, you dont know what is it \n";
        description += "\tyou look South eventually, it is looks bright, seems like the exit of the cave \n";

        description += "\t(please choose your direction to move)\n";
        gameArea.setText(description);}
        else{
            return;
        }
    }

    /**
     * methods in ths sections are control panel section
     */
    /*
    public void pickUpEquipment(){
        if(!isStart){
            return;
        }
        titanium = new Equipment("Titanium");
        missile = new Equipment("missile");
        if (isWest && !arcList.contains(titanium)){
            titanium.setOwner(tony);
            arcList.add(titanium);
        }else if (isSouth && !arcList.contains(missile)){
            missile.setOwner(tony);
            arcList.add(missile);
        }
    }


    public void viewBag(Tony tony){
        if(!isStart){
            return;
        }
        StringBuilder items = new StringBuilder();
        if (tony.getEquipments().isEmpty()){
            items.append(tony.getName() + ", your have nothing on you to build the Arc Reactor\n");
        } else {
            items.append(tony.getName() + ", you have following items to build Arc Reactor" + arcList.toString() +
                    "\n");
        }
        gameArea.append("\n" + items);
    }

    /**
     * this method is the fail condition of the the game, TODO: thinking refactor to easy change index
     */
    public void checkCounts(){
        gameArea.append("\n\t your battery is low, the current battery capacity is" + (20 - stepCount));
        if (stepCount > 20){
            new CloseWindow(" \n the Shrapnel have reached to your heart, you are killed by your own missile");
        }
    }

    /**
     * ALL methods in this section are handling direction methods
     */
    public void goUp(){
        if(!isStart){
            return;
        }
        checkCounts();
        if(isCenter){
            description = "\n\tyou walk into the the room at North Side, a terrorist sit by a table full of monitors " +
                    "gazed at you. then he yell at you: 'what are you doing here? go back to your cave and start " +
                    "building Jericho missile for us to rule the world!! ' \n";
            isCenter =false;
            isNorth = true;
            stepCount++;
            gameArea.setText(description);
        }else if(isSouth){
            backToCenterHandler();
        }else {
            wrongDirectionHandler();
        }
    }
    public void goDown(){
        if(!isStart){
            return;
        }
        checkCounts();
        if(isCenter){
            description = "\n\tyou walk out of the cave. you see some terrorist troops in formation are training to " +
                    "use the weapons built by your company. there is some sheets Metal lay on the ground, looks like " +
                    "Titanium, which is would be perfect materials to build the frame for the Arc Reactor \n";
            isCenter = false;
            isSouth = true;
            stepCount++;
            gameArea.setText(description);
        }else if(isNorth){
            backToCenterHandler();
        }else{
            wrongDirectionHandler();
        }
    }
    public void goLeft(){
        if(!isStart){
            return;
        }
        checkCounts();
        if(isCenter){
            description= "\n\t you walked in the hidden cave seems like the Armory of the TenRings. you see lots of " +
                    "missiles made by your company that contains palladium to power the Arc Reactor. \n";
            isCenter = false;
            isWest = true;
            stepCount++;
            gameArea.setText(description);
        }else if(isEast){
            backToCenterHandler();
        }
        else{
            wrongDirectionHandler();
        }
    }

    public void goRight(){
        if(!isStart){
            return;
        }
        checkCounts();
        if(isCenter){
            description = "\n\t you walked into a large room. you see some terrorist are cooking MREs and others lay " +
                    "around the area. you try to talk to someone but seems no one knows English. \n";
            isCenter = false;
            isEast = true;
            stepCount++;
            gameArea.setText(description);
        }
        else if (isWest){
            backToCenterHandler();
        }
        else{
            wrongDirectionHandler();
        }
    }

    public void backToCenterHandler(){
        description = "\n\tyou walked back to the cave. there is nothing here\n";
        isSouth = false;
        isNorth = false;
        isWest = false;
        isEast = false;
        isCenter = true;
        stepCount++;
        gameArea.setText(description);
    }

    public void wrongDirectionHandler(){
        description = "\n\tyou are forbidden by Ten Rings to go to this direction from here\n";
        stepCount++;
        gameArea.append(description);
    }
}
