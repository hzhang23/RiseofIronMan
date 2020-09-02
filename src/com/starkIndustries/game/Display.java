package com.starkIndustries.game;


import javax.swing.*;
import java.awt.*;

//Game title
//picture of Ironman suit
//Start game button
//resume game button
//create exit button

public class Display {
    public void startGameWindow(){
        JFrame frame = new JFrame("Rise of Iron Man");
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton startBtn = new JButton("Start Game");
        JButton resumeBtn = new JButton("Resume Game");
        JButton exitBtn = new JButton("Exit Game");
        panel.add(startBtn);
        panel.add(resumeBtn);
        panel.add(exitBtn);
        frame.getContentPane().add(panel);
        ImageIcon backgroundImage = new ImageIcon("resources/StartGameBackground.jpg");
        JLabel label = new JLabel(backgroundImage);
        label.setOpaque(true);
        frame.getLayeredPane().add(label,(Integer.MIN_VALUE));
        label.setBounds(0,0, backgroundImage.getIconWidth(),backgroundImage.getIconHeight());


    }

    public static void main(String[] args) {
        Display test = new Display();
        test.startGameWindow();

    }



}
