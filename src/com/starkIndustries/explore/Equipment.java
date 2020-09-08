package com.starkIndustries.explore;

public class Equipment {
    private String name;
    private Tony owner;

    //ctor
    public Equipment(String name) {
        this.name = name;
    }

    //getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tony getOwner() {
        return owner;
    }

    public void setOwner(Tony owner) {
        this.owner = owner;
    }
}
