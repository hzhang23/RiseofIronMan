package com.starkIndustries.game;

import com.google.gson.JsonObject;
import com.starkIndustries.explore.ExploreFrame;
import com.starkIndustries.fight.FightScene;
import com.starkIndustries.fight.Player;
import com.starkIndustries.fight.TonyStark;
import com.starkIndustries.puzzleGame.PuzzleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class GameWindow extends JFrame {

    public GameWindow(String scene) {
        //load JSON file
        JsonObject myStory = StoryTeller.getStory(new File("resources/demo.json"));
        JsonObject thisScene = myStory.get(scene).getAsJsonObject();
        String scenePicPath = thisScene.get("img").getAsString();
        ArrayList<String> lines = getListStrFromJson(myStory, scene);

        //add bg pic
        JLayeredPane layeredPane = new JLayeredPane();
        JPanel bgPanel = getBgLabel();
        layeredPane.add(bgPanel,JLayeredPane.DEFAULT_LAYER);
        //add scene pic
        ImageIcon sceneImg = new ImageIcon(scenePicPath);
        JLabel sceneLabel = new JLabel(sceneImg);
        sceneLabel.setBounds(200, 50, 1000, 400);
        sceneLabel.setOpaque(true);
        layeredPane.add(sceneLabel,JLayeredPane.MODAL_LAYER);
        //add ChatLabel'
        JTextArea chatArea = new JTextArea(2,20);
        Font font = new Font("Anime Ace 2", Font.BOLD, 24);
        chatArea.setBounds(200, 470, 800, 180);
        chatArea.setFont(font);
        chatArea.setWrapStyleWord(true);
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);
        chatArea.setOpaque(true);
        chatArea.setBackground(Color.ORANGE);
        chatArea.setText(lines.get(0));
        layeredPane.add(chatArea,JLayeredPane.MODAL_LAYER);

        // adding the characters

        Player tonyStark = new TonyStark("Tony Stark", 20, 20, 10, 5, false);
        Player npc = new Player ("Guard", 50, 30, 20, 10);

        //add nextBtn
        JButton nextBtn = new JButton("Continue");
        nextBtn.setBounds(1050, 470,150,180);
        nextBtn.setBackground(Color.GREEN);
        nextBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        layeredPane.add(nextBtn,JLayeredPane.MODAL_LAYER);
        nextBtn.addActionListener(new ActionListener() {
            int i = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i < lines.size()) {
                    chatArea.setText(lines.get(i));
                    i++;
                } else {
                    try{ String playerVoice = thisScene.get("Player").getAsJsonObject().get("voice").getAsString();
                        chatArea.setText(playerVoice);

                        // Adding "Choice A" Button
                        JButton ABtn = new JButton("A");
                        ABtn.addActionListener(new ActionListener() {
                            String nextScene =
                                    thisScene.get("Player").getAsJsonObject()
                                            .get("reply").getAsJsonObject().get("A").getAsString();
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new GameWindow(nextScene);
                                dispose();
                            }
                        });
                        ABtn.setBounds(300, 600, 100, 50);
                        layeredPane.add(ABtn,JLayeredPane.POPUP_LAYER);

                        // Adding "Choice B" Button
                        JButton BBtn = new JButton("B");
                        BBtn.addActionListener(new ActionListener() {
                            String nextScene =
                                    thisScene.get("Player").getAsJsonObject()
                                            .get("reply").getAsJsonObject().get("B").getAsString();
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new GameWindow(nextScene);
                                dispose();
                            }
                        });
                        BBtn.setBounds(450, 600, 100, 50);
                        layeredPane.add(BBtn,JLayeredPane.POPUP_LAYER);

                        // Adding "Fight" Button
                        if (scene.equals("3")) {
                            JButton fightBtn = new JButton("Fight the Enemy");

                            fightBtn.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    FightScene newScene = new FightScene(scene, tonyStark, npc);
                                    newScene.generateWindow();
                                }
                            });

                            fightBtn.setBounds(600, 600, 200, 50);
                            layeredPane.add(fightBtn, JLayeredPane.POPUP_LAYER);

                        }}
                    catch (NullPointerException excep){
                        String scene2 = "well. why not? I don't want to hook up to a car battery for the rest of my " +
                                "life";
                        String scene5 = "Yinsen: I will buy some time for you. it was a great time spending with you. live a good life if I never come back!";
                        String sceneFail = "Game over, you have Died!!";
                        String scene23 = "I AM IRONMAN!!!";

                        for (String line : lines){
                            if (line.equals(scene2)){
                                new ExploreFrame();
                                dispose();
                            }else if(line.equals(scene5)){
                                new PuzzleFrame();
                                dispose();
                            }else if (line.equals(sceneFail) || line.equals(scene23)){
                                new CloseWindow("Game Over!");
                            }
                        }
                    }

                }
            }
        });

        this.setLayeredPane(layeredPane);
        this.setSize(bgPanel.getWidth(), bgPanel.getHeight());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new CloseWindow("Do you want to save the game before leave?");
            }
        });
    }

    public ArrayList<String> getListStrFromJson (JsonObject script, String scene){
        ArrayList<String> linesToShow = new ArrayList<>();
        JsonObject thisScene = script.get(scene).getAsJsonObject();
        Set<String> voiceKey = thisScene.keySet();
        for (String key : voiceKey) {
            if (!key.equals("Player") && !key.equals("img")) {
                linesToShow.add(thisScene.get(key).getAsString());
            }
        }
        return linesToShow;
    }

    private JPanel getBgLabel(){
        JPanel bgPanel = new JPanel();
        ImageIcon bgImg = new ImageIcon("resources/StartGameBackground.jpg");
        bgPanel.setBounds(0,0,bgImg.getIconWidth(),bgImg.getIconHeight());
        JLabel bgLabel = new JLabel(bgImg);
        bgPanel.add(bgLabel);
        return bgPanel;
    }
}
