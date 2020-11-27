package tactical_chaos.damage;

import tactical_chaos.champion.Champion;

public class ManaBurnerDamageCalculator extends DamageCalculator {
           public ManaBurnerDamageCalculator(float damage) {
                     this.IntendedDamage = damage;
           }
    @Override
    public void Calculate(Champion deffender) {

           deffender.CA.ManaStart -= this.IntendedDamage;
           if (deffender.CA.ManaStart <= 0) {
                     deffender.CA.ManaStart = 0;
        }
    }

}
