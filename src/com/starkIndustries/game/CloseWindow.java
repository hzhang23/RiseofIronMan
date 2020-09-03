package com.starkIndustries.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseWindow extends JFrame {
    public CloseWindow (String message){
        JLabel exitlabel = new JLabel(message);
        JButton yesBtn = new JButton("Yes");
        JButton noBtn = new JButton("No");
        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel panel = new JPanel();
        if (message.equals("Do you want to save the game before leave?")){
            panel.add(exitlabel);
            panel.add(yesBtn);
            panel.add(noBtn);
        }else{
            panel.add(exitlabel);
            noBtn.setText("Leave");
            panel.add(noBtn);
        }
        this.add(panel);
        this.setSize(300,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new CloseWindow("Do you want to save the game before leave?");

    }
}
