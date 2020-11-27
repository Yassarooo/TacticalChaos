package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.TrueDamage;
import tactical_chaos.damage.MagicDamage;

public class ActivateMordekaiserAbilityMove extends Move {

    public ActivateMordekaiserAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 22;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        float damage = 150,inc = this.Deffender.CA.MagicResist * 10 / 100;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
        }

        if (this.CurrentChampion.CA.VoidBuff) {
            this.Deffender.AcceptDamage(new TrueDamage(damage));
        } else {
            this.Deffender.AcceptDamage(new MagicDamage(damage));
        }

        this.Deffender.CA.MagicResist -= inc;

    }

}
