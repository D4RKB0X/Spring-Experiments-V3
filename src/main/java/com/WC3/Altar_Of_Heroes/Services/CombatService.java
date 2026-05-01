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

    public String fightUnits(Long unitAId, Long unitBId) {

        Unit unitA = inputUnitRepo.findById(unitAId)
                .orElseThrow(() -> new IllegalArgumentException("Unit A not found!"));

        Unit unitB = inputUnitRepo.findById(unitBId)
                .orElseThrow(() -> new IllegalArgumentException("Unit B not found!"));

        if (!unitA.isAlive() || !unitB.isAlive()) {
            throw new IllegalStateException("Both units must be alive to fight!");
        }

        StringBuilder battleLog = new StringBuilder();
        battleLog.append("⚔️ Battle Start: ")
                .append(unitA.getName())
                .append(" vs ")
                .append(unitB.getName())
                .append("\n");

        int round = 1;

        while (unitA.isAlive() && unitB.isAlive()) {

            battleLog.append("\n\n--- Round ").append(round++).append(" ---\n");

            unitA.dealDamage(unitB);
            battleLog.append(unitA.getName())
                    .append(" hits ")
                    .append(unitB.getName())
                    .append(" | HP left: ")
                    .append(unitB.getHealth())
                    .append("\n");

            if (!unitB.isAlive()) {
                break;
            }

            unitB.dealDamage(unitA);
            battleLog.append(unitB.getName())
                    .append(" hits ")
                    .append(unitA.getName())
                    .append(" | HP left: ")
                    .append(unitA.getHealth())
                    .append("\n");
        }

        Unit winner = unitA.isAlive() ? unitA : unitB;

        battleLog.append("\n🏆 Winner: ").append(winner.getName());

        inputUnitRepo.save(unitA);
        inputUnitRepo.save(unitB);

        return battleLog.toString();
    }
}
