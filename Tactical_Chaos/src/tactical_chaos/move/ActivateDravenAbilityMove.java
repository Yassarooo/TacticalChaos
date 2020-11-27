package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.TrueDamage;

public class ActivateDravenAbilityMove extends Move {

    public ActivateDravenAbilityMove(Champion Attacker,Champion Deffender) {
        this.CurrentChampion=Attacker;
        this.Deffender = Deffender;
        this.MoveID = 7;
    }

    @Override
    public void PerformMove() {
        float damage = this.Deffender.CA.Health * 30 / 100;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }

        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        }
        this.Deffender.AcceptDamage(new TrueDamage(damage));

    }

}
