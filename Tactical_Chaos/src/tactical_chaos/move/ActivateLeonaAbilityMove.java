package tactical_chaos.move;

import tactical_chaos.champion.Champion;

public class ActivateLeonaAbilityMove extends Move {

    public ActivateLeonaAbilityMove(Champion Deffender) {
        this.Deffender = Deffender;
        this.MoveID = 17;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        this.Deffender.CA.IsStunned = true;
    }

}
