package tactical_chaos.move;

import tactical_chaos.champion.Champion;

public class ActivateKatarinaAbilityMove extends Move {

    public ActivateKatarinaAbilityMove(Champion Deffender) {
        this.Deffender = Deffender;
        this.MoveID = 14;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }

        this.Deffender.CA.ManaStart = 0;
        this.Deffender.CA.GetMana = false;

    }

}
