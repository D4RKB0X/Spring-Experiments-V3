package com.WC3.Altar_Of_Heroes.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Building extends GameEntity {

    @OneToMany
    @JoinColumn(name = "Building_ID")
    private Set<Unit> unitRoster = new HashSet<>();

    private static final int MAX_CAPACITY = 4;

    protected Building() {}

    public Building(String name, int health) { super(name, health); }
    public Set<Unit> getUnitRoster() { return Set.copyOf(unitRoster); }

    public void addUnit(Unit inputUnit) {
        if(inputUnit == null) {
            throw new IllegalArgumentException("Unit cannot be null!");
        }

        if(unitRoster.size() >= MAX_CAPACITY) {
            throw new IllegalStateException("Building roster is already full!");
        }

        if(!unitRoster.add(inputUnit)) {
            throw new IllegalStateException("Unit already exists in building!");
        }
    }

    public void removeUnit(Unit inputUnit) {
        if(inputUnit == null) {
            throw new IllegalArgumentException("Unit cannot be null!");
        }

        if(!unitRoster.remove(inputUnit)) {
            throw new IllegalStateException("Unit is not part of the building!");
        }
    }
}
