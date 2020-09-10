package com.starkIndustries.fight;

public class TonyStark extends Player {
    // instance variables
    private boolean isSuitComplete = false;

    // ctors
    public TonyStark(String name, double hp, double attackUpper, double attackLower, double armor, boolean isSuitComplete) {
        super(name, hp, attackUpper, attackLower, armor);
        this.isSuitComplete = isSuitComplete;
    }

    // instance methods
    public void defend() {
        System.out.println("Tony defends");
    }

    public void runAway() {
        System.out.println("Tony runs away");
    }

    // getters and setters
    public boolean isSuitComplete() {
        return isSuitComplete;
    }

    public void setSuitComplete(boolean suitComplete) {
        isSuitComplete = suitComplete;
    }
}
