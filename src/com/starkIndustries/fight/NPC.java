package com.starkIndustries.fight;

public abstract class NPC {
    // variables
    private int healthPoints;

    // abstract methods
    public abstract void fight(); // this method will cause damage to Tony's health

    // accessors
    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
