package tactical_chaos.damage;

import tactical_chaos.champion.Champion;

public class TrueDamage extends DamageCalculator {

    public TrueDamage(float damage) {

        this.IntendedDamage = damage;
    }

    @Override
    public void Calculate(Champion deffender) {

        deffender.CA.newHealth = deffender.CA.newHealth - this.IntendedDamage;
        if (deffender.CA.newHealth == 0) {
            deffender.CA.IsDead = true;
        }

    }

}
