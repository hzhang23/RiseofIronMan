package com.starkIndustries.ironManSuits;

import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame{
    JLabel modelLabel;
    JPanel centerPanel;
    JButton emptyButton;
    int num = 0;

    public static void main(String[] args) {
        new PuzzleFrame();
    }

    public PuzzleFrame() {

        //put topPanel at top
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        //create a Label for model pic
        modelLabel = new JLabel();
        



        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);





    }




}
