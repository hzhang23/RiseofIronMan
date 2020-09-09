package com.starkIndustries.fight;

public abstract class NPC extends Player{
    // variables
    private int healthPoints;

    public NPC(String name, double hp, double attackUpper, double attackLower, double armor) {
        super(name, hp, attackUpper, attackLower, armor);
    }

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
