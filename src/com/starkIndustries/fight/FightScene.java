package com.starkIndustries.fight;

import javax.swing.*;
import java.awt.*;

public class FightScene extends JFrame {
    // fight scene will have two people fighting

    private JLayeredPane layeredPane;
    private ImageIcon bgImg = new ImageIcon("resources/StartGameBackground.jpg");

    // panels
    private JPanel bgPanel;
    private JPanel fightersPanel;

    // labels
    private JLabel bgLabel;
    private JLabel tonyLabel;
    private JLabel enemyLabel;

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

        // bring in a fighters panel
//        fightersPanel = makeButtonsPanel();

        // bring in label for Tony
        tonyLabel = makeTonyLabel();

        // bring in label for enemy
        enemyLabel = makeEnemyLabel();

        // "assemble" the layered pane
        result.add(bgPanel, JLayeredPane.DEFAULT_LAYER);
        result.add(tonyLabel, JLayeredPane.MODAL_LAYER);
        result.add(enemyLabel, JLayeredPane.MODAL_LAYER);
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
        result.setText("You have chosen to fight the enemy...");
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
