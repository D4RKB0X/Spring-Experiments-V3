package com.WC3.Altar_Of_Heroes.Controllers;

import com.WC3.Altar_Of_Heroes.Services.CombatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combat")
public class CombatController {
    private final CombatService inputCombatService;

    public CombatController(CombatService inputCombatService) {
        this.inputCombatService = inputCombatService;
    }

    @PostMapping("/attack-unit")
    public String inputAttackUnit(@RequestParam Long attackerID, @RequestParam Long targetID) {
        inputCombatService.attackUnit(attackerID, targetID);
        return "Attack Executed!";
    }

    @PostMapping("/attack-building")
    public String inputAttackBuilding(@RequestParam Long attackerID, @RequestParam Long targetID) {
        inputCombatService.attackBuilding(attackerID, targetID);
        return "Building Attacked!";
    }

    @PostMapping("/fight")
    public String fight(@RequestParam Long unitA, @RequestParam Long unitB) {
        return inputCombatService.fightUnits(unitA, unitB);
    }
}
