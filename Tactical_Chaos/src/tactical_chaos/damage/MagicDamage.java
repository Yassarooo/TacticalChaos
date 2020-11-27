package tactical_chaos.damage;

import tactical_chaos.champion.Champion;

public class MagicDamage extends DamageCalculator {

    public MagicDamage(float damage) {

        this.IntendedDamage = damage;
    }

    @Override
    public void Calculate(Champion deffender) {

        deffender.CA.newHealth -= (float) this.IntendedDamage - (float) (this.IntendedDamage * deffender.CA.MagicResist / 100);

        if (deffender.CA.newHealth == 0) {
            deffender.CA.IsDead = true;
        }

    }
}
