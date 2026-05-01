package com.WC3.Altar_Of_Heroes.Entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class Hero extends Unit{

    @Min(value = 0, message = "Strength cannot be negative!")
    private int strength;

    @Min(value = 0, message = "Agility cannot be negative!")
    private int agility;

    @Min(value = 0, message = "Intelligence cannot be negative!")
    private int intelligence;

    protected Hero() {}

    public Hero(String name, int health, int damage, int strength, int agility, int intelligence) {
        super(name, health, damage);
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public int getStrength() { return strength; }
    public int getAgility() { return agility; }
    public int getIntelligence() { return intelligence; }

    public void setStrength(int strength) {
        if(strength < 0) {
            throw new IllegalArgumentException("Strength cannot be negative!");
        }

        this.strength = strength;
    }

    public void setAgility(int agility) {
        if(agility < 0) {
            throw new IllegalArgumentException("Agility cannot be negative!");
        }

        this.agility = agility;
    }

    public void setIntelligence(int intelligence) {
        if(intelligence < 0) {
            throw new IllegalArgumentException("Intelligence cannot be negative!");
        }

        this.intelligence = intelligence;
    }
}
