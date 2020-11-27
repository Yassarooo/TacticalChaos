package tactical_chaos.move;

import tactical_chaos.champion.Champion;

public class ActivateFioraAbilityMove extends Move {

    public ActivateFioraAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 8;
    }

    @Override
    public void PerformMove() {

        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        this.CurrentChampion.CA.FioraAbility = true;
        this.Deffender.CA.IsStunned = true;
        this.Deffender.CA.NextRound = 1;
    }
}
