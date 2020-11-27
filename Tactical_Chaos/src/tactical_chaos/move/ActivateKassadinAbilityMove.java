package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;
import tactical_chaos.damage.TrueDamage;

public class ActivateKassadinAbilityMove extends Move {

    public ActivateKassadinAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 13;
    }

    @Override
    public void PerformMove() {
        float damage = this.CurrentChampion.CA.AttackDamage + 20;

        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        } else {

            this.Deffender.CA.ManaStart -= 2;

            if (this.CurrentChampion.CA.SorcererBuff != 0) {
                damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
            }
            if (this.CurrentChampion.CA.MagicHat) {
                damage += damage * 20 / 100;
            }

            if (this.CurrentChampion.CA.VoidBuff = true) {
                this.Deffender.AcceptDamage(new TrueDamage(damage));
            } else {
                this.Deffender.AcceptDamage(new MagicDamage(damage));
            }

        }

    }

}
