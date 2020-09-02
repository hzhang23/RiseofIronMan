package com.starkIndustries.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {
    private JLayeredPane layeredPane;
    private JPanel bgPanel;
    private JLabel bgLabel;
    private ImageIcon bgImg;
    private JButton startBtn;
    private JButton resumeBtn;
    private JButton exitBtn;



  public StartWindow(){
      layeredPane = new JLayeredPane();
      bgImg = new ImageIcon("resources/StartGameBackground.jpg");
      bgPanel = new JPanel();
      bgPanel.setBounds(0,0,bgImg.getIconWidth(),bgImg.getIconHeight());

      bgLabel = new JLabel(bgImg);
      bgPanel.add(bgLabel);

      startBtn = new JButton("Start Game");
      startBtn.setBounds(100,100,100,100);
      startBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
//              new gameWindow("0");
          }
      });
      resumeBtn = new JButton("Resume Game");
      resumeBtn.setBounds(100,250,100,100);
      resumeBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
//              new gameFileWindow();
          }
      });
      exitBtn = new JButton("Exit");
      exitBtn.setBounds(100,400,100,100);
      exitBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              System.exit(0);
          }
      });

      layeredPane.add(bgPanel,JLayeredPane.DEFAULT_LAYER);
      layeredPane.add(startBtn,JLayeredPane.MODAL_LAYER);
      layeredPane.add(resumeBtn,JLayeredPane.MODAL_LAYER);
      layeredPane.add(exitBtn,JLayeredPane.MODAL_LAYER);

      this.setLayeredPane(layeredPane);
      this.setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setLocationRelativeTo(null);
  }



    public static void main(String[] args) {
        new StartWindow();
    }

}
