package tactical_chaos.damage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tactical_chaos.champion.Champion;

public class BasicAttackDamageCalculator extends DamageCalculator {

    public BasicAttackDamageCalculator(float damage) {
        this.IntendedDamage = damage;
    }

    @Override
    public void Calculate(Champion deffender) {

        deffender.CA.newHealth -= (float) this.IntendedDamage - (float) (this.IntendedDamage * deffender.CA.Armor / 100);

        if (deffender.CA.newHealth <= 0) {
            deffender.CA.IsDead = true;
        }

    }

}
