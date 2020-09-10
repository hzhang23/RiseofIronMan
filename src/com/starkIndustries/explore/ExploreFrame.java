package com.starkIndustries.explore;

import com.starkIndustries.game.CloseWindow;
import com.starkIndustries.game.GameWindow;

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
    Tony tony = null;
    ArrayList<Equipment> arcList = new ArrayList<>();
    Equipment titanium = null;
    Equipment missile = null;

    //game control elements & GUI
    int stepCount = 0; //battery power run out before find all components
    JButton buttonUp, buttonDown, buttonLeft, buttonRight;
    JButton buttonPickup, buttonCheckBag;
    JTextArea gameArea;
    String description;

    //DONE: MainFrame set up
    public ExploreFrame(){
        /** welcome Text show
         */
        Font font = new Font("Anime Ace 2", Font.BOLD, 24);
        gameArea = new JTextArea();
        gameArea.setFont(font);
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
        /**add a panel to handle check Panel and control Panel
         */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));
        bottomPanel.add(controlPanel);
        bottomPanel.add(checkPanel);

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
               pickUpEquipment();
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
        this.add(bottomPanel,BorderLayout.SOUTH);
        this.setVisible(true);
        this.setSize(1400,700);
        this.setLocationRelativeTo(null);
    }

    //DONE startPlay
    public void startExplore(){
        if(!isStart){
        tony = new Tony("Tony Stark");
        isStart = true;
        isCenter = true;
        description = "You realize the car battery that keep your alive will be completed dry in 10 days. \n";
        description += "you look around the cave, you see there is a dark room on the west side of the cave, a " +
                "terrorist walk out the dark room with a new rifle that made by your company on his hand. \n";
        description += "on the North side, there is huge glass windows and people inside that room could see " +
                "everything about this cave. you could also see several monitors layout in that room. \n";
        description += "there is also another cave on the East side, you dont know what is it \n";
        description += "you look South eventually, it is looks bright, seems like the exit of the cave \n";

        description += "\t(please choose your direction to move)\n";
        gameArea.setText(description);}
        else{
            return;
        }
    }

    public void checkWins(){
        if (isCenter && (arcList.size() >1 ) ){
            description = "Tony, you have found all the materials to build the Arc Reactor!";
            gameArea.setText(description);
            JOptionPane winMsg = new JOptionPane();
            winMsg.showMessageDialog(this, description,"you win", JOptionPane.PLAIN_MESSAGE);
            new GameWindow("3");
            dispose();
        }

    }

    /**
     * methods in ths sections are control panel section
     */
    public String showEquipments(){
        StringBuilder stringB = new StringBuilder();
        for (Equipment items : arcList){
            stringB.append("\t");
            stringB.append(items.getName());
            stringB.append("\n");
        }
        return stringB.toString();
    }


    public void pickUpEquipment(){
        titanium = new Equipment("Titanium");
        missile = new Equipment("missile");
        if(isSouth && isEquipmentExist){
            titanium.setOwner(tony);
            arcList.add(titanium);
            gameArea.append("\t you have found the titanium to build the container of Arc Reactor");
            isEquipmentExist = false;
        }else if(isWest && isEquipmentExist){
            missile.setOwner(tony);
            arcList.add(missile);
            gameArea.append("\t you have fund the missiles which you could extract palladium for Arc Reactor");
            isEquipmentExist = false;
        }else{
            gameArea.append("\n\tthere is nothing to pick up in this area now");
        }
    }


    public void viewBag(Tony tony){
        if(!isStart){
            return;
        }
        if(arcList.isEmpty()){
            gameArea.append("\n\t you don't have anything to build Arc Reactor yet..\n");
        }else {
            gameArea.append("\n\t you have following items on you: \n" + showEquipments()+ "\n");
        }
    }

    /**
     * this method is the fail condition of the the game, TODO: thinking refactor to easy change index
     */
    public String checkCounts(){
        if (stepCount > 10){
            JOptionPane.showMessageDialog(null,"the sharpnel has reached to your heart","YOU DIE!",JOptionPane.WARNING_MESSAGE);
            new GameWindow("fail");
        }
        return ("\n\t your battery is low, the current battery capacity is: " + (10 - stepCount));
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
        if(!arcList.contains(titanium)){
            isEquipmentExist = true;
        }
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
        if (!arcList.contains(missile)) {
            isEquipmentExist = true;
        }
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
        checkWins();
        gameArea.setText(description);
    }

    public void wrongDirectionHandler(){
        description = "\n\tyou are forbidden by Ten Rings to go to this direction from here\n";
        stepCount++;
        String steps = "\n\t" + checkCounts();
        gameArea.setText(description + steps);
    }
}
