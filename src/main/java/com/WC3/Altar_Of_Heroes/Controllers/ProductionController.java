package com.WC3.Altar_Of_Heroes.Controllers;

import com.WC3.Altar_Of_Heroes.Entities.Hero;
import com.WC3.Altar_Of_Heroes.Services.ProductionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/production")
public class ProductionController {
    private final ProductionService inputProductService;

    public ProductionController(ProductionService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @PostMapping("/train")
    public String inputTrainUnit(@RequestParam Long buildingID,
                                 @RequestParam String unitName,
                                 @RequestParam int health,
                                 @RequestParam int damage) {
        return inputProductService.trainUnit(buildingID, unitName, health, damage);
    }

    @PostMapping("/summon")
    public Hero inputSummonHero(@RequestParam Long buildingID,
                                @RequestParam String heroName,
                                @RequestParam int health,
                                @RequestParam int damage,
                                @RequestParam int strength,
                                @RequestParam int agility,
                                @RequestParam int intelligence) {
        return inputProductService.summonHero(buildingID, heroName, health, damage, strength, agility, intelligence);
    }
}
