package com.starkIndustries.explore;

/*
this class mainly for Tony Stark
 */

import java.util.ArrayList;

public class Tony {
    private String name;
    private boolean isLive;


    //ctor
    public Tony(String name) {
        this.name = name;
        isLive = true;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
