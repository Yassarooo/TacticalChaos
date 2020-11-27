package tactical_chaos.move;

import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;

public class ActivateAkaliAbilityMove extends Move {

    public ActivateAkaliAbilityMove(Champion Attacker,Champion Deffender) {
        this.CurrentChampion=Attacker;
        this.Deffender = Deffender;
        this.MoveID = 2;
    }

    @Override
    public void PerformMove() {
        float damage = this.Deffender.CA.Health * 10 / 100;

        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        if (this.Deffender.CA.DragonBuff || this.Deffender.CA.FioraAbility) {
            return;
        } else {
            this.Deffender.AcceptDamage(new MagicDamage(damage));
        }

    }

}
