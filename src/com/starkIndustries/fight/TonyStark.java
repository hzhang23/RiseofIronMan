package com.starkIndustries.fight;

public class TonyStark {
    // instance variables
    private int healthPoints = 10;
    private boolean isSuitComplete = false;

    // ctors
    public TonyStark(int healthPoints, boolean isSuitComplete) {
        this.healthPoints = healthPoints;
        this.isSuitComplete = isSuitComplete;
    }

    // instance methods
    public void fight() {
        System.out.println("Tony fights");
    }

    public void defend() {
        System.out.println("Tony defends");
    }

    public void runAway() {
        System.out.println("Tony runs away");
    }

    // getters and setters
    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public boolean isSuitComplete() {
        return isSuitComplete;
    }

    public void setSuitComplete(boolean suitComplete) {
        isSuitComplete = suitComplete;
    }
}
