package tactical_chaos.move;

import tactical_chaos.arena.*;
import tactical_chaos.champion.*;

public class ActivateVeigerAbilityMove extends Move {

    public ActivateVeigerAbilityMove(Champion Deffender) {
        this.Deffender = Deffender;
        this.MoveID = 28;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        this.Deffender.CA.IsDead = true;
    }

}
