package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.TrueDamage;

public class ActivateChogathAbilityMove extends Move {

    public ActivateChogathAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 5;
    }

    @Override
    public void PerformMove() {
        float damage = 150;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        } else {
            if (this.CurrentChampion.CA.VoidBuff = true) {
                this.Deffender.AcceptDamage(new TrueDamage(damage));
            } else {
                this.Deffender.AcceptDamage(new TrueDamage(damage));
            }

        }

    }

}
