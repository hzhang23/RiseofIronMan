package com.starkIndustries.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

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
                setVisible(false);
                GameFileWindow window = new GameFileWindow();
                window.generateWindow();
          }
      });
      exitBtn = new JButton("Exit");
      exitBtn.setBounds(100,400,100,100);
      exitBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new CloseWindow("Thanks for Playing Rise of IronMan");
          }
      });

      layeredPane.add(bgPanel,JLayeredPane.DEFAULT_LAYER);
      layeredPane.add(startBtn,JLayeredPane.MODAL_LAYER);
      layeredPane.add(resumeBtn,JLayeredPane.MODAL_LAYER);
      layeredPane.add(exitBtn,JLayeredPane.MODAL_LAYER);

      this.setLayeredPane(layeredPane);
      this.setSize(bgImg.getIconWidth(), bgImg.getIconHeight());
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setVisible(true);
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
              new CloseWindow("Thanks for Playing Rise of IronMan");
          }
      });
  }

    public static void main(String[] args) {
        new StartWindow();
    }

}
