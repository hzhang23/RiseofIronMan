package com.starkIndustries.puzzleGame;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private static int IMAGEWIDTH;
    private static int IMAGEHEIGHT;
    private int ID = 0;

    public Cell(Icon icon, int id, int imagewidth, int height)
    {
        this.setIcon(icon);
        this.ID = id;
        this.IMAGEWIDTH = imagewidth;
        this.IMAGEHEIGHT = height;
        this.setSize(IMAGEWIDTH, IMAGEHEIGHT);
    }

    public void move(Direction dir) {
        Rectangle rec = this.getBounds();
        switch(dir)
        {
            case UP:
                this.setLocation(rec.x, rec.y + IMAGEHEIGHT);
                break;
            case DOWN:
                this.setLocation(rec.x, rec.y - IMAGEHEIGHT);
                break;
            case LEFT:
                this.setLocation(rec.x - IMAGEWIDTH, rec.y);
                break;
            case RIGHT:
                this.setLocation(rec.x + IMAGEWIDTH, rec.y);
                break;
        }
    }

    public int getID() {
        return ID;
    }

    public int getX()
    {
        return this.getBounds().x;
    }

    public int getY()
    {
        return this.getBounds().y;
    }
}

