package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;

public class ActivateMissFortuneAbilityMove extends Move {

    public ActivateMissFortuneAbilityMove(Champion Attacker,Champion Deffender) {
        this.CurrentChampion=Attacker;
        this.Deffender = Deffender;
        this.MoveID = 21;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        float damage = 100, inc = this.Deffender.CA.AttackDamage * 10 / 100;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
        }

        this.Deffender.AcceptDamage(new MagicDamage(damage));
        this.Deffender.CA.AttackDamage += inc;

    }

}
