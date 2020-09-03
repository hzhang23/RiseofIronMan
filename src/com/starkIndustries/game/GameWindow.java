package com.starkIndustries.game;

import com.google.gson.JsonObject;

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
    // a window with 2 static display: bg picture and dialogue box
    // a button to click to continue the story
    // when the questions pop up. I want to have click choose button or hyperlink to next scene

    private ImageIcon bgImg;
    private JLayeredPane layeredPane;
    private JPanel bgPanel;
    private JLabel bgLabel;
    private JLabel sceneLabel;
    private JButton nextBtn;
    private JLabel chatLabel;

    public GameWindow(){
        layeredPane = new JLayeredPane();
        bgImg = new ImageIcon("resources/StartGameBackground.jpg");
        bgPanel = new JPanel();
        bgPanel.setBounds(0,0,bgImg.getIconWidth(),bgImg.getIconHeight());
        bgLabel = new JLabel(bgImg);
        bgPanel.add(bgLabel);

        //TODO: when load Json file, slice the path of scene picture to the value of sceneLabel
        JsonObject scene = StoryTeller.getStory(new File("resources/story.json"));
        String imgPath = scene.get("0").getAsJsonObject().get("img").getAsString();
        ImageIcon sceneImg = new ImageIcon(imgPath);
        sceneLabel = new JLabel(sceneImg);
        sceneLabel.setBounds(200, 50, 1000, 400);
        sceneLabel.setOpaque(true);

        chatLabel = new JLabel("This is a placeholder for Json script");
        chatLabel.setBounds(200, 470, 800, 180);
        chatLabel.setOpaque(true);
        chatLabel.setBackground(Color.ORANGE);

        nextBtn = new JButton("Continue");
        nextBtn.setBounds(1050, 470,150,180);
        nextBtn.setBackground(Color.GREEN);
        nextBtn.addActionListener(new ActionListener() {
            int i = 0;
            public void actionPerformed(ActionEvent e) {
                //runGameScript(new File("resources/story.json"));
                JsonObject myStoryJson = StoryTeller.getStory(new File("resources/story.json"));
                ArrayList<String> lines = getListStrFromJson(myStoryJson, "0");
                if (i < lines.size()) {
                    chatLabel.setText(lines.get(i));
                    i++;
                } else {
                    playerChoice();
                }
            }
        });

        layeredPane.add(bgPanel,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(sceneLabel,JLayeredPane.MODAL_LAYER);
        layeredPane.add(chatLabel,JLayeredPane.MODAL_LAYER);
        layeredPane.add(nextBtn,JLayeredPane.MODAL_LAYER);


        this.setLayeredPane(layeredPane);
        this.setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
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

    public void playerChoice() {
        JsonObject scene = StoryTeller.getStory(new File("resources/story.json"));
        JsonObject thisScene = scene.get("0").getAsJsonObject();
        JsonObject playerReact = thisScene.get("Player").getAsJsonObject();
        String playerVoice = playerReact.get("voice").getAsString();
        chatLabel.setText(playerVoice);
        JButton ABtn = new JButton("A");
        ABtn.setBounds(300, 600, 100, 30);
        layeredPane.add(ABtn,JLayeredPane.POPUP_LAYER);
        JButton BBtn = new JButton("B");
        BBtn.setBounds(450, 600, 100, 30);
        layeredPane.add(BBtn,JLayeredPane.POPUP_LAYER);
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

    public static void main(String[] args) {
        new GameWindow();
    }

}
