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
        double hurtPoints = Math.random()*(attackUpper - attackLower) + attackLower;
        if (hurtPoints <= npc.armor){
            hurtPoints = 0;
        }else {
            hurtPoints -= npc.armor;
        }
        npc.hp -= hurtPoints;
        System.out.println(name + " cause " + npc.name + " lose " + hurtPoints + " hp");
        System.out.println(name + " current hp is " + this.getHp());
        System.out.println(npc.name + " current hp is " + npc.getHp());
    }

    public void defend(Player npc) {
        double defensePoints = this.getArmor()/2;
        System.out.println("defensePoints is " + defensePoints);
        System.out.println("HP before points added: " + this.getHp());
        this.hp += defensePoints;
        System.out.println("HP after points added: " + this.getHp());
    }

    public boolean isLiving(Player npc){
        if (hp <= 0 ){
            System.out.println(name + " is dead" + npc.name + " wins");
            return false;
        }
        return true;
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
