package com.WC3.Altar_Of_Heroes.Controllers;

import com.WC3.Altar_Of_Heroes.Entities.Unit;
import com.WC3.Altar_Of_Heroes.Services.UnitCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
public class UnitController {
    private final UnitCRUDService inputUnitService;

    public UnitController(UnitCRUDService inputUnitService) {
        this.inputUnitService = inputUnitService;
    }

    @PostMapping("/create")
    public Unit createUnit(@RequestParam String name, @RequestParam int health, @RequestParam int damage) {
        return inputUnitService.createUnit(name, health, damage);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUnit(@PathVariable Long id) {
        inputUnitService.deleteUnitByID(id);
        return "Unit deleted: " + id;
    }

    @GetMapping("/find/{id}")
    public Unit findUnitByID(@PathVariable Long id) {
        return inputUnitService.getUnitByID(id);
    }

    @GetMapping("/all")
    public List<Unit> getAllUnits() {
        return inputUnitService.getAllUnits();
    }
}
