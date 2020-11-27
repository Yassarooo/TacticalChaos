package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;

public class ActivateGarenAbilityMove extends Move {

    public ActivateGarenAbilityMove(Champion Attacker,Champion Deffender) {
        this.CurrentChampion=Attacker;
        this.Deffender = Deffender;
        this.MoveID = 10;
    }

    @Override
    public void PerformMove() {

        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }

        float MissingHealth = this.Deffender.CA.Health - this.Deffender.CA.newHealth;

        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            MissingHealth += MissingHealth * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.Deffender.AcceptDamage(new MagicDamage(MissingHealth));
    }

}
