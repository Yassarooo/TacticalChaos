package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.TrueDamage;

public class ActivateVayneAbilityMove extends Move {

    public ActivateVayneAbilityMove(Champion Attacker,Champion Deffender) {
        this.CurrentChampion=Attacker;
        this.Deffender = Deffender;
        this.MoveID = 27;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        float inc=this.Deffender.CA.Health * 15 / 100;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.Deffender.AcceptDamage(new TrueDamage(inc));

    }

}
