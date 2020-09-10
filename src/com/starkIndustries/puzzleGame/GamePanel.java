package com.starkIndustries.puzzleGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener {

    private Cell BlankCell = null;
    private int row = 2;
    private int col = 2;
    private Cell cells[] = new Cell[row*col];
    int ImageWidth;
    int ImageHeight;

    public GamePanel()
    {
        this.setLayout(null);
        init();
    }

    public void init() {
        int num = 0;
        BufferedImage buf = null;
        BufferedImage bufnew = null;
        ImageIcon icon = null;
        int width = 0;
        int height = 0;
        try
        {
            buf = ImageIO.read(new File("resources/puzzle/iron_man_mk1.png"));
            ImageWidth = buf.getWidth();
            ImageHeight = buf.getHeight();
            width = ImageWidth/col;
            height = ImageHeight/row;
        }catch(Exception e)
        {
            System.out.println(e);
        }
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                num = i*col+j;
                if(num < row*col-1)
                {
                    bufnew = buf.getSubimage(width*j, height*i, width, height);
                    icon = new ImageIcon(bufnew);
                }
                else
                {
                    icon = new ImageIcon("F:/Image/Puzzle_game/background2.jpg");
                }
                cells[num] = new Cell(icon, num, width, height);
                cells[num].setLocation(width*j, height*i);
            }
        }
        BlankCell = cells[cells.length-1];
        for(int i = 0; i < cells.length; i++)
        {
            this.add(cells[i]);
            if(i < cells.length-1)
                cells[i].addMouseListener(this);
        }
    }

    public void OutOfOrder() {
        Random random = new Random();
        for(int i = 0 ; i < cells.length ; i++)
        {
            int index1 = random.nextInt(cells.length);
            int index2 = random.nextInt(cells.length);
            int x = cells[index1].getX();
            int y = cells[index1].getY();
            cells[index1].setLocation(cells[index2].getX(), cells[index2].getY());
            cells[index2].setLocation(x, y);
        }
    }

    public boolean IsWin() {
        for(int i = 0; i < cells.length; i++)
        {
            int x = cells[i].getX();
            int y = cells[i].getY();
            if(x/(ImageWidth/col) + y/(ImageHeight/row) != i)
            {
                return false;
            }else{
                return true;
            }
        }
        return true;
    }



    @Override
    public void mouseClicked(MouseEvent e)
    {
        Cell t = (Cell) e.getSource();
        int x = BlankCell.getX();
        int y = BlankCell.getY();
        if(t.getY() == y && t.getX() + ImageWidth/col == x)
        {
            t.move(Direction.RIGHT);
            BlankCell.move(Direction.LEFT);
        }
        else if(t.getY() == y && t.getX() - ImageWidth/col == x)
        {
            t.move(Direction.LEFT);
            BlankCell.move(Direction.RIGHT);
        }
        else if(t.getX() == x && t.getY() + ImageHeight/row == y)
        {
            t.move(Direction.UP);
            BlankCell.move(Direction.DOWN);
        }
        else if(t.getX() == x && t.getY() - ImageHeight/row == y)
        {
            t.move(Direction.DOWN);
            BlankCell.move(Direction.UP);
        }
    }




    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
