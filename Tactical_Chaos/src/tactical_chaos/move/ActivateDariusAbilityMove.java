package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;
import tactical_chaos.damage.TrueDamage;
import static tactical_chaos.move.MoveFactory.SearchInRange;

public class ActivateDariusAbilityMove extends Move {

    public ActivateDariusAbilityMove(Arena Map, Champion Deffender) {
        this.CurrentChampion = Deffender; //itself
        this.Map = Map;
        this.MoveID = 6;
    }

    @Override
    public void PerformMove() {
        List<Champion> toDamage = SearchInRange(this.CurrentChampion, this.Map, 20);
        float inc=75;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        this.CurrentChampion.CA.newHealth+=inc;
        for (Champion c : toDamage) {
            if (c.CA.DragonBuff) {
                continue;
            }
            c.AcceptDamage(new TrueDamage(inc));
        }
    }
}
