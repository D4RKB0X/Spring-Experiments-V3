package com.WC3.Altar_Of_Heroes.Services;

import com.WC3.Altar_Of_Heroes.Entities.Building;
import com.WC3.Altar_Of_Heroes.Entities.Hero;
import com.WC3.Altar_Of_Heroes.Entities.Unit;
import com.WC3.Altar_Of_Heroes.Repositories.BuildingRepository;
import com.WC3.Altar_Of_Heroes.Repositories.UnitRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductionService {
    private final UnitRepository inputUnitRepo;
    private final BuildingRepository inputBuildRepo;

    public ProductionService(UnitRepository inputUnitRepo, BuildingRepository inputBuildRepo) {
        this.inputUnitRepo = inputUnitRepo;
        this.inputBuildRepo = inputBuildRepo;
    }

    public String trainUnit(Long buildingID, String unitName, int health, int damage) {
        Building inputBuilding = inputBuildRepo.findById(buildingID)
                .orElseThrow(() -> new IllegalArgumentException("Building not found!"));

        Unit inputUnit = new Unit(unitName, health, damage);

        inputBuilding.addUnit(inputUnit);
        inputUnitRepo.save(inputUnit);
        inputBuildRepo.save(inputBuilding);

        return "Unit: " + inputUnit.getName() + " trained at " + inputBuilding.getName();
    }

    public Hero summonHero(Long buildingID, String heroName, int health, int damage, int strength, int agility, int intelligence) {
        Building inputBuilding = inputBuildRepo.findById(buildingID)
                .orElseThrow(() -> new IllegalArgumentException("Building not found!"));

        Hero inputHero = new Hero(heroName, health, damage, strength, agility, intelligence);

        inputBuilding.addUnit(inputHero);
        inputUnitRepo.save(inputHero);
        inputBuildRepo.save(inputBuilding);

        return inputHero;
    }
}
