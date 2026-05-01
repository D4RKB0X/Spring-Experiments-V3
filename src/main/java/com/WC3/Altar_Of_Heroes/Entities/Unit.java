package com.WC3.Altar_Of_Heroes.Entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class Unit extends GameEntity{

    @Min(value = 0, message = "Damage cannot be negative!")
    private int damage;

    protected Unit() {}

    public Unit(String name, int health, int damage) {
        super(name, health);
        this.damage = damage;
    }

    public int getDamage() { return damage; }

    public void setDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative!");
        }
        this.damage = damage;
    }

    public void dealDamage(GameEntity inputTarget) {
        if(inputTarget == null) {
            throw new IllegalArgumentException("Target cannot be null!");
        }

        if(!isAlive()) {
            throw new IllegalStateException("Dead unit cannot attack!");
        }

        inputTarget.takeDamage(damage);
    }
}
