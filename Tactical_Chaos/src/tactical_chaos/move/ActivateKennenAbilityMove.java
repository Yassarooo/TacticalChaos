package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.BasicAttackDamageCalculator;

public class ActivateKennenAbilityMove extends Move {

    public ActivateKennenAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 15;
    }

    @Override
    public void PerformMove() {
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }

        float damage = this.CurrentChampion.CA.AttackDamage * 350 / 100;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.Deffender.AcceptDamage(new BasicAttackDamageCalculator(damage));

    }

}
