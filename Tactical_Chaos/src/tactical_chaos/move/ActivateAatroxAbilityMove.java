package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;
import static tactical_chaos.move.MoveFactory.SearchInRange;

public class ActivateAatroxAbilityMove extends Move {

    public ActivateAatroxAbilityMove(Arena Map, Champion Deffender) {
        this.CurrentChampion = Deffender; //itself
        this.Map = Map;
        this.MoveID = 1;
    }

    @Override
    public void PerformMove() {
        float damage = 250;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        List<Champion> toDamage = SearchInRange(this.CurrentChampion, this.Map, 25);
        for (Champion c : toDamage) {
            if (c.CA.DragonBuff || c.CA.FioraAbility) {
                continue;
            }
            c.AcceptDamage(new MagicDamage(damage));
        }

    }
}
