package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;

public class ActivateAsheAbilityMove extends Move {
    
    public ActivateAsheAbilityMove(Champion Attacker, Champion Deffender) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.MoveID = 4;
    }

    @Override
    public void PerformMove() {
        float damage = 200;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        this.CurrentChampion.CA.Speed += 10 / 100;
        this.Deffender.AcceptDamage(new MagicDamage(damage));
        this.CurrentChampion.CA.NextRound = 2;
        this.Deffender.CA.IsStunned = true;
    }
}
