package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.round.Planning;

public class ActivateTalonAbilityMove extends Move {

    public ActivateTalonAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 25;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }

        this.CurrentChampion.CA.TalonNextRound = 4;
        this.CurrentChampion.CA.CriticalStrikeChance = 100;
        this.CurrentChampion.CA.TalonAbility = true;

    }

}
