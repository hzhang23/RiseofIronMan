package com.starkIndustries.fight;

public class Player {
    private String name;
    private double hp;
    private double attackUpper;
    private double attackLower;
    private double armor;

    //ctor
    public Player(String name, double hp, double attackUpper, double attackLower, double armor) {
        this.name = name;
        this.hp = hp;
        this.attackUpper = attackUpper;
        this.attackLower = attackLower;
        this.armor = armor;
    }

    public void attack(Player npc){
        double hurt = Math.random()*(attackUpper - attackLower) + attackLower;
        if (hurt <= npc.armor){
            hurt = 0;
        }else {
            hurt -= npc.armor;
        }
        npc.hp -= hurt;
        System.out.println(name + " cause " + npc.name + " lose " + hurt + " hp");
        System.out.println(name + " current hp is " + hp);
        System.out.println(npc.name + " current hp is " + hp);
    }

    public boolean isLiving(Player npc){
        if (hp <= 0 ){
            System.out.println(name + " is dead" + npc.name + " wins");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Player tonyStark = new Player("Tony Stark", 50, 20, 10, 5);
        Player raza = new Player ("Raza", 100, 10, 5, 10);
        while (true){
            tonyStark.attack(raza);
            if(!raza.isLiving(tonyStark)){
                break;
            }
            raza.attack(tonyStark);
            if(!tonyStark.isLiving(raza)){
                break;
            }
        }
    }

    //getter&setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public Double getAttackUpper() {
        return attackUpper;
    }

    public void setAttackUpper(Double attackUpper) {
        this.attackUpper = attackUpper;
    }

    public Double getAttachLower() {
        return attackLower;
    }

    public void setAttachLower(Double attackLower) {
        this.attackLower = attackLower;
    }

    public Double getArmor() {
        return armor;
    }

    public void setArmor(Double armor) {
        this.armor = armor;
    }
}
