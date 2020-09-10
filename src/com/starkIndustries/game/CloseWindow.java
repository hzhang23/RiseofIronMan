package com.starkIndustries.game;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Map;

public class CloseWindow extends JFrame {

    JButton yesBtn;
    JButton noBtn;
    JButton restartBtn;

    public CloseWindow (String message){
        JLabel exitlabel = new JLabel(message);
        yesBtn = new JButton("Yes");
        yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame("0");
            }
        });
        noBtn = new JButton("No");
        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartBtn = new JButton("restart");
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameWindow("0");
                dispose();
            }
        });

        JPanel panel = new JPanel();
        if (message.equals("Do you want to save the game before leave?")){
            panel.add(exitlabel);
            panel.add(yesBtn);
            panel.add(noBtn);
        } else if(message.equals("Game Over!")){
            panel.add(exitlabel);
            panel.add(noBtn);
            panel.add(restartBtn);
            noBtn.setText("leave");
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

    public void saveGame(String sceneNum){
        String filename = JOptionPane.showInputDialog(null, "Please enter the file name to save:");
        System.out.println(filename);
        try {
            Gson gson = new Gson();
            JsonElement gameFile = JsonParser.parseReader(new FileReader("resources/gameFile.json"));
            java.lang.reflect.Type fileMap = new TypeToken<Map<String, String>>(){}.getType();
            Map<String,String> newMap = gson.fromJson(gameFile, fileMap);
            newMap.put(filename,sceneNum);
            Writer writer = new FileWriter("resources/gameFile.json");
            gson.toJson(newMap, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new CloseWindow("Do you want to save the game before leave?");

    }
}
