package com.WC3.Altar_Of_Heroes.Services;

import com.WC3.Altar_Of_Heroes.Entities.Building;
import com.WC3.Altar_Of_Heroes.Entities.Unit;
import com.WC3.Altar_Of_Heroes.Repositories.BuildingRepository;
import com.WC3.Altar_Of_Heroes.Repositories.UnitRepository;
import org.springframework.stereotype.Service;

@Service
public class CombatService {
    private final UnitRepository inputUnitRepo;
    private final BuildingRepository inputBuildRepo;

    public CombatService(UnitRepository inputUnitRepo, BuildingRepository inputBuildRepo) {
        this.inputUnitRepo = inputUnitRepo;
        this.inputBuildRepo = inputBuildRepo;
    }

    public void attackUnit(Long attackerID, Long targetID) {
        Unit inputAttacker = inputUnitRepo.findById(attackerID)
                .orElseThrow(() -> new IllegalArgumentException("Attacker not found!"));

        Unit inputTarget = inputUnitRepo.findById(targetID)
                .orElseThrow(() -> new IllegalArgumentException("Target not found!"));

        if(!inputAttacker.isAlive() || !inputTarget.isAlive()) {
            throw new IllegalStateException("Combat Invalid!");
        }

        inputAttacker.dealDamage(inputTarget);

        inputUnitRepo.save(inputAttacker);
        inputUnitRepo.save(inputTarget);
    }

    public void attackBuilding(Long attackerID, Long targetID) {
        Unit inputAttacker = inputUnitRepo.findById(attackerID)
                .orElseThrow(() -> new IllegalArgumentException("Attacker not found!"));

        Building inputTarget = inputBuildRepo.findById(targetID)
                .orElseThrow(() -> new IllegalArgumentException("Target not found!"));

        if(!inputAttacker.isAlive() || !inputTarget.isAlive()) {
            throw new IllegalStateException("Combat Invalid!");
        }

        inputAttacker.dealDamage(inputTarget);

        inputUnitRepo.save(inputAttacker);
        inputBuildRepo.save(inputTarget);
    }
}
