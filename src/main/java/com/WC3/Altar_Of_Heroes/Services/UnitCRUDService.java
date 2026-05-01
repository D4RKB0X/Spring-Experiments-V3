package com.WC3.Altar_Of_Heroes.Services;

import com.WC3.Altar_Of_Heroes.Entities.Unit;
import com.WC3.Altar_Of_Heroes.Repositories.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitCRUDService {
    private final UnitRepository inputUnitRepo;

    public UnitCRUDService(UnitRepository inputUnitRepo) {
        this.inputUnitRepo = inputUnitRepo;
    }

    public Unit createUnit(String name, int health, int damage) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank!");
        }

        Unit inputUnit = new Unit(name, health, damage);
        return inputUnitRepo.save(inputUnit);
    }

    public void deleteUnitByID(Long inputIDKey) {
        if(!inputUnitRepo.existsById(inputIDKey)) {
            throw new IllegalArgumentException("Unit not found!");
        }

        inputUnitRepo.deleteById(inputIDKey);
    }

    public Unit getUnitByID(Long inputIDKey) {
        return inputUnitRepo.findById(inputIDKey)
                .orElseThrow(() -> new IllegalArgumentException("Unit not found!"));
    }

    public List<Unit> getAllUnits() {
        return inputUnitRepo.findAll();
    }

    public Unit updateUnit(Long ID, String name, int health, int damage) {
            Unit inputUnit = getUnitByID(ID);

            if (health < 0 || damage < 0) {
                throw new IllegalArgumentException("Health and damage must be non-negative!");
            }

            inputUnit.setHealth(health);
            inputUnit.setDamage(damage);

            return inputUnitRepo.save(inputUnit);
    }
}