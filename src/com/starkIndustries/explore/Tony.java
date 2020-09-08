package com.starkIndustries.explore;

/*
this class mainly for Tony Stark
 */

import java.util.ArrayList;

public class Tony {
    private String name;
    private boolean isLive;
    private ArrayList<Equipment> equipments;


    //ctor
    public Tony(String name) {
        this.name = name;
        isLive = true;
    }

    //business methods
    public StringBuilder getStatus(){
        StringBuilder mystatus = new StringBuilder();
        mystatus.append("the battery is drying now...\n");
        if (equipments.isEmpty()){
            mystatus.append("you current materials are: none \n");
        }else{
            mystatus.append("your current materials are:" + equipments);
        }
        return mystatus;
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

    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Equipment equipment) {
        this.equipments = equipments;
    }
}
