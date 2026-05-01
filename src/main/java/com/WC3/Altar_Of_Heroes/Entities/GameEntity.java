package com.WC3.Altar_Of_Heroes.Entities;

import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@MappedSuperclass
public abstract class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKey;

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @Min(value = 0, message = "Health cannot be negative!")
    private int health;

    protected GameEntity() {}
    public GameEntity(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public Long getIdKey() { return idKey; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public boolean isAlive() { return health > 0; }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative!");
        }
        this.health = health;
    }

    public void takeDamage(int damage) {
        if(damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative!");
        }

        if(!isAlive()) {
            return;
        }

        int previousHealth = health;
        health = Math.max(0, health - damage);

        if(getHealth() == 0) {
            if(damage >= previousHealth) {
                System.out.printf("%s was obliterated by massive damage!\n", getName());
            }
            else {
                System.out.printf("%s has died!\n", getName());
            }
        }
    }
}
