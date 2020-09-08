package com.starkIndustries.gameFrame;

import javax.swing.*;
import java.awt.*;

public class PicPanel extends JPanel {
    private String path;

    //ctor
    public PicPanel(String path) {
        this.path = path;
    }

    //Business Methods
    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        ImageIcon bgImg = new ImageIcon(path);
        graph.drawImage(bgImg.getImage(),0,0, this.getWidth(),this.getHeight(),bgImg.getImageObserver());
    }

    //getter&setter
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
