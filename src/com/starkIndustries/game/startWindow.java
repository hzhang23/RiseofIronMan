package com.starkIndustries.game;

import javax.swing.*;

public class startWindow extends JFrame {
    private JLayeredPane layeredPane;
    private JPanel bgPanel;
    private JLabel bgLabel;
    private ImageIcon bgImg;
    private JButton startBtn;
    private JButton resumeBtn;
    private JButton exitBtn;



  public startWindow(){
      layeredPane = new JLayeredPane();
      bgImg = new ImageIcon("resources/StartGameBackground.jpg");
      bgPanel = new JPanel();
      bgPanel.setBounds(0,0,bgImg.getIconWidth(),bgImg.getIconHeight());

      bgLabel = new JLabel(bgImg);
      bgPanel.add(bgLabel);

      startBtn = new JButton("Start Game");
      startBtn.setBounds(100,100,100,100);
      resumeBtn = new JButton("Resume Game");
      resumeBtn.setBounds(100,250,100,100);
      exitBtn = new JButton("Exit");
      exitBtn.setBounds(100,400,100,100);

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
        new startWindow();
    }

}
