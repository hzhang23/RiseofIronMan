package com.starkIndustries.puzzleGame;

public class Timer extends Thread {
    int t = 10;

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
                System.out.println(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
