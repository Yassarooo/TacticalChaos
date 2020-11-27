package tactical_chaos.move;

import java.util.Random;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.Champion;
import static tactical_chaos.champion.Champion.GetChance;
import tactical_chaos.damage.BasicAttackDamageCalculator;
import static tactical_chaos.move.MoveFactory.SearchInRange;
import tactical_chaos.damage.TrueDamage;

public class BasicAttackMove extends Move {

    public BasicAttackMove(Champion Attacker, Champion Deffender, Arena Map) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.Map = Map;
        this.MoveID = 32;
    }

    @Override
    public void PerformMove() {

        if (this.CurrentChampion.CA.ManaStart < this.CurrentChampion.CA.ManaCost && this.CurrentChampion.CA.GetMana) {
            this.CurrentChampion.CA.ManaStart++;
        }
        if (this.Deffender.CA.ManaStart < this.Deffender.CA.ManaCost && this.Deffender.CA.GetMana) {
            this.Deffender.CA.ManaStart++;
        }

        if (this.Deffender.CA.YordleBuff) {
            return;
        }

        float damage;

        if (GetChance(CurrentChampion.CA.CriticalStrikeChance)) {
            damage = (float) this.CurrentChampion.CA.AttackDamage
                    + (float) (this.CurrentChampion.CA.AttackDamage * this.CurrentChampion.CA.CriticalStrikeDamage / 100);
        } else {
            damage = this.CurrentChampion.CA.AttackDamage;
        }

        if (this.CurrentChampion.CA.DemonBuff == true) {
            Deffender.CA.ManaStart -= this.CurrentChampion.CA.DemonManaBurn;
            damage += this.CurrentChampion.CA.DemonManaBurn;
        }

        if (this.CurrentChampion.CA.GunslingerBuff == true) {
            for (int i = 0; i < this.CurrentChampion.CA.GunslingerBuff2; i++) {
                Random rand = new Random();
                if(SearchInRange(this.CurrentChampion, this.Map, this.CurrentChampion.CA.VisionRange).isEmpty())
                {
                    return;
                }
                int idx = rand.nextInt(SearchInRange(this.CurrentChampion, this.Map, this.CurrentChampion.CA.VisionRange).size());
                if (idx < 0) {
                    return;
                }
                SearchInRange(this.CurrentChampion, this.Map, this.CurrentChampion.CA.VisionRange).get(idx).AcceptDamage(new BasicAttackDamageCalculator(damage));
            }
        }
        if (this.CurrentChampion.CA.VoidBuff) {
            this.Deffender.AcceptDamage(new TrueDamage(damage));
        }
        if (this.CurrentChampion.CA.BladeMasterBuff) {
            for (int i = 0; i < this.CurrentChampion.CA.BladeMasterBuff2; i++) {
                Deffender.AcceptDamage(new BasicAttackDamageCalculator(damage));
            }
        } else {
            Deffender.AcceptDamage(new BasicAttackDamageCalculator(damage));
        }

        if (this.CurrentChampion.CA.GlacialBuff == true) {
            this.Deffender.CA.IsStunned = true;
        }

        if (this.CurrentChampion.CA.NobleBuff == true) {
            this.CurrentChampion.CA.newHealth += 40;
            this.CurrentChampion.CA.Health += 40;
        }

    }
}
