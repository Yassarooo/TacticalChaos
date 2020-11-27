/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;
import static tactical_chaos.move.MoveFactory.SearchInRange;

public class ActivateLissandraAbilityMove extends Move {

    public ActivateLissandraAbilityMove(Champion Attacker, Champion Deffender, Arena Map) {
        this.CurrentChampion = Attacker;
        this.Deffender = Deffender;
        this.Map = Map;
        this.MoveID = 18;
    }

    @Override
    public void PerformMove() {
        float damage = 550;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        if (this.CurrentChampion.CA.MagicHat) {
            damage += damage * 20 / 100;
        }
        Deffender.AcceptDamage(new MagicDamage(damage));
        List<Champion> InRange = SearchInRange(this.CurrentChampion, this.Map, this.CurrentChampion.CA.AttackRange);
        if (InRange.size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(InRange.size());
            if (InRange.get(idx).CA.DragonBuff) {
                return;
            }
            InRange.get(idx).AcceptDamage(new MagicDamage(damage));
        }
    }
}
