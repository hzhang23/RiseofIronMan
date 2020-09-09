package com.starkIndustries.fight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightScene extends JFrame {

    // variables
    private JLayeredPane layeredPane;
    private ImageIcon bgImg = new ImageIcon("resources/StartGameBackground.jpg");

    private JPanel bgPanel;
    private JPanel buttonsPanel;

    private JLabel bgLabel;
    private JLabel tonyLabel;
    private JLabel enemyLabel;

    private JTextArea fightDescription;

    private TonyStark tonyStark;
    private NPC npc;

    // generate a window
    public void generateWindow() {
        // bring in a layered pane
        layeredPane = makeLayeredPane();

        setLayeredPane(layeredPane);
        setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // make a layered pane
    public JLayeredPane makeLayeredPane() {
        JLayeredPane result = new JLayeredPane();

        // bring in a background panel
        bgPanel = makeBackgroundPanel();

        // bring in a buttons panel
        buttonsPanel = makeButtonsPanel();

        // bring in label for Tony
        tonyLabel = makeTonyLabel();

        // bring in label for enemy
        enemyLabel = makeEnemyLabel();

        // bring in fight description box
        fightDescription = makeFightDescription();

        // "assemble" the layered pane
        result.add(bgPanel, JLayeredPane.DEFAULT_LAYER);
        result.add(buttonsPanel, JLayeredPane.MODAL_LAYER);
        result.add(tonyLabel, JLayeredPane.MODAL_LAYER);
        result.add(enemyLabel, JLayeredPane.MODAL_LAYER);
        result.add(fightDescription, JLayeredPane.MODAL_LAYER);
        return result;
    }

    private JPanel makeButtonsPanel() {
        JPanel result = new JPanel();
        result.setBounds(1000, 370, 300, 280);
        result.setBackground(Color.lightGray);
        result.setOpaque(true);
        GridLayout myLayout = new GridLayout(2, 1);
        result.setLayout(myLayout);

        // add "fight" button
        JButton fightBtn = makeFightButton();
        result.add(fightBtn);

        // add "run away" button
        JButton runAwayBtn = new JButton("Run Away");
        result.add(runAwayBtn);

        return result;
    }

    private JButton makeFightButton() {
        JButton result = new JButton("Fight!");
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player tonyStark = new Player("Tony Stark", 10, 20, 10, 5);
                Player guard = new Player ("Guard", 100, 80, 60, 10);
                while (tonyStark.isLiving(guard)){
                    fightDescription.append("Tony Strikes...\n");
                    tonyStark.attack(guard);
                    fightDescription.append("Tony's health: " + tonyStark.getHp() + "\n");
                    fightDescription.append("Enemy Strikes...\n");
                    guard.attack(tonyStark);
                    fightDescription.append("Enemy's health: " + guard.getHp() + "\n");
                }

            }
        });
        return result;
    }


    // make the fight description
    public JTextArea makeFightDescription() {
        JTextArea result = new JTextArea();
        Font font = new Font("Anime Ace 2", Font.BOLD, 24);
        result.setBounds(100, 370, 700, 280);
        result.setFont(font);
        result.setWrapStyleWord(true);
        result.setLineWrap(true);
        result.setEditable(false);
        result.setOpaque(true);
        result.setBackground(Color.MAGENTA);
        result.setText("You have chosen to fight...\n");
        return result;
    }

    // make a background panel
    public JPanel makeBackgroundPanel() {
        JPanel result = new JPanel();
        result.setBounds(0, 0,bgImg.getIconWidth(),bgImg.getIconHeight());

        // make a new label and add it to the background panel
        bgLabel = new JLabel(bgImg);
        result.add(bgLabel);
        return result;
    }

    // make greeting label
    public JLabel makeTonyLabel() {
        JLabel result = new JLabel();
        result.setText("Tony Stark image placeholder");
        result.setBounds(100, 100, 300, 200);
        result.setBackground(Color.ORANGE);
        result.setOpaque(true);

        return result;
    }

    // make enemy label
    public JLabel makeEnemyLabel() {
        JLabel result = new JLabel();
        result.setText("Enemy image placeholder");
        result.setBounds(1000, 100, 300, 200);
        result.setBackground(Color.RED);
        result.setOpaque(true);

        return result;
    }


}
