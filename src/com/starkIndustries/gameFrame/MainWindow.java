package com.starkIndustries.gameFrame;

import com.starkIndustries.game.CloseWindow;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private ImageIcon bgImg;
    private PicPanel bgPanel;
    private JButton startBtn;
    private JButton resumeBtn;
    private JButton exitBtn;
    String path = "resources/StartGameBackground.jpg";


    JPanel newP = new JPanel();

    public MainWindow() {
        initFrame();
        bgPanel = new PicPanel(path);
        this.add(bgPanel);
        startBtn = new JButton("start game");
        resumeBtn = new JButton("resume a game");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshWindow();
            }
        });
        startBtn.setPreferredSize(new Dimension(200,100));
        newP.setBounds(new Rectangle(400, 200));
        newP.setOpaque(false);
        newP.add(startBtn);
        newP.add(resumeBtn);
        newP.setLayout(new GridLayout(3,3));
        bgPanel.add(newP);
        bgPanel.setLayout(new GridBagLayout());
    }

        public void initFrame () {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    new CloseWindow("Thanks for Playing Rise of IronMan");
                }
            });

        }

        public void refreshWindow () {
            bgPanel.removeAll();
            bgPanel.repaint();
        }

    public static void main(String[] args) {
        new MainWindow();
    }
}
