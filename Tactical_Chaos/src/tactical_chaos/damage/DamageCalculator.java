package tactical_chaos.damage;

import tactical_chaos.champion.Champion;

public abstract class DamageCalculator {

    public float IntendedDamage;

    public abstract void Calculate(Champion deffender);
}
