package com.WC3.Altar_Of_Heroes.Controllers;

import com.WC3.Altar_Of_Heroes.Entities.Building;
import com.WC3.Altar_Of_Heroes.Services.BuildingCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {
    private final BuildingCRUDService inputBuildService;

    public BuildingController(BuildingCRUDService inputBuildService) {
        this.inputBuildService = inputBuildService;
    }

    @PostMapping("/create")
    public Building createBuilding(@RequestParam String name, @RequestParam int health) {
        return inputBuildService.createBuilding(name, health);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBuilding(@PathVariable Long id) {
        inputBuildService.deleteBuildingByID(id);
        return "Building deleted: " + id;
    }

    @GetMapping("/find/{id}")
    public Building findBuildingByID(@PathVariable Long id) {
        return inputBuildService.getBuildingByID(id);
    }

    @GetMapping("/all")
    public List<Building> getAllBuildings() {
        return inputBuildService.getAllBuildings();
    }
}