package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;

public class ActivateGravesAbilityMove extends Move {

    public ActivateGravesAbilityMove(Champion Attacker,Champion Deffender) {
        this.CurrentChampion=Attacker;
        this.Deffender = Deffender;
        this.MoveID = 11;
    }

    @Override
    public void PerformMove() {
        float damage=300;
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }

        this.Deffender.CA.CriticalStrikeChance = 0;
        this.Deffender.AcceptDamage(new MagicDamage(damage));
    }

}
