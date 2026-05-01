package com.WC3.Altar_Of_Heroes.Services;

import com.WC3.Altar_Of_Heroes.Entities.Building;
import com.WC3.Altar_Of_Heroes.Repositories.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingCRUDService {
    private final BuildingRepository inputBuildRepo;

    public BuildingCRUDService(BuildingRepository inputBuildRepo) {
        this.inputBuildRepo = inputBuildRepo;
    }

    public Building createBuilding(String name, int health) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank!");
        }

        Building inputBuild = new Building(name, health);
        return inputBuildRepo.save(inputBuild);
    }

    public void deleteBuildingByID(Long inputIDKey) {
        if(!inputBuildRepo.existsById(inputIDKey)) {
            throw new IllegalArgumentException("Building not found!");
        }

        inputBuildRepo.deleteById(inputIDKey);
    }

    public Building getBuildingByID(Long inputIDKey) {
        return inputBuildRepo.findById(inputIDKey)
                .orElseThrow(() -> new IllegalArgumentException("Building not found!"));
    }

    public List<Building> getAllBuildings() {
        return inputBuildRepo.findAll();
    }

    public Building updateBuilding(Long ID, String name, int health) {
        Building building = getBuildingByID(ID);

        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative!");
        }

        building.setHealth(health);

        return inputBuildRepo.save(building);
    }
}
