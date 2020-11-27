package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;
import static tactical_chaos.move.MoveFactory.SearchInRange;

public class ActivateAniviaAbilityMove extends Move {

    public ActivateAniviaAbilityMove(Champion Attacker, Champion Deffender, Arena Map) {
        this.Deffender = Deffender;
        this.CurrentChampion = Attacker;
        this.Map = Map;
        this.MoveID = 3;
    }

    @Override
    public void PerformMove() {
        List<Champion> toDamage = SearchInRange(this.CurrentChampion, this.Map, 30);
        float damage = 250;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        if(this.CurrentChampion.CA.MagicHat){
            damage += damage*20/100;
        }
        for (Champion c : toDamage) {
            if (c.CA.DragonBuff) {
                continue;
            }
            c.AcceptDamage(new MagicDamage(damage));
        }

        if (!this.Deffender.CA.DragonBuff) {
            this.Deffender.CA.IsStunned = true;
        }

    }
}
