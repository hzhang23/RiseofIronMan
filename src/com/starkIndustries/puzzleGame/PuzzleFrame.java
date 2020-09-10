package com.starkIndustries.puzzleGame;


import com.starkIndustries.game.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleFrame extends JFrame {
    public JPanel pane1 = new JPanel();
    public JButton button1 = new JButton("Start/Restart");
    public JButton completeBtn = new JButton("Complete");
    public JLabel timesheet = new JLabel();
    final Time timer = new Time();
    public PuzzleFrame()
    {
        this.setTitle("Assemble the suit");
        pane1.setLayout(new FlowLayout());
        pane1.add(button1);
        pane1.add(completeBtn);
        pane1.add(timesheet);
        Container con = this.getContentPane();
        con.add(pane1,BorderLayout.NORTH);
        GamePanel gamepane = new GamePanel();
        con.add(gamepane,BorderLayout.CENTER);
        this.setBounds(300, 300, 600, 1000);
        this.setVisible(true);
        button1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gamepane.OutOfOrder();
                timer.setT(15);
                timer.start();
            }
        });
        completeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gamepane.IsWin()){
                    return;
                }else{
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Iron Man Suit Equiped, ready to fight!","completion the " +
                            "suit!", JOptionPane.PLAIN_MESSAGE);
                    new GameWindow("23");
                    dispose();
                }
            }
        });
    }

    class Time extends Thread {
        int t = 100;

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public void run(){
            while(t>0){
                try {
                    Thread.sleep(1000);
                    t--;
                    timesheet.setText(t + " seconds left to assemble the Iron Man Suit");
                    System.out.println(t);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (t <1) {
                    JOptionPane.showMessageDialog(null, "Raza break the door and shoot at you", "YOU DIE!",
                            JOptionPane.PLAIN_MESSAGE);
                    new GameWindow("fail");
                    dispose();
                }
            }
        }
    }


    public static void main(String[] args) {
        new PuzzleFrame();
    }
    }
