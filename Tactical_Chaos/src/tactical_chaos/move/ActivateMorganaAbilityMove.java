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

public class ActivateMorganaAbilityMove extends Move {

    public ActivateMorganaAbilityMove( Arena Map,Champion Deffender) {
        this.CurrentChampion = Deffender; //itself
        this.Map = Map;
        this.MoveID = 23;
    }

    @Override
    public void PerformMove() {

        List<Champion> InRange = SearchInRange(this.CurrentChampion, this.Map, 25);
        float damage = 100;
        int steal = 2;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
            steal = 4;
        }
        if(this.CurrentChampion.CA.MagicHat){
            damage += damage*20/100;
        }
        for (Champion c : InRange) {
            if (c.CA.VoidBuff) {
                return;
            } else {
                c.AcceptDamage(new MagicDamage(damage));
                if (c.CA.ManaStart < steal) {
                    this.CurrentChampion.CA.ManaStart += c.CA.ManaStart;
                    c.CA.ManaStart = 0;
                } else {
                    this.CurrentChampion.CA.ManaStart += steal;
                    c.CA.ManaStart -= steal;
                }
            }
        }
        if (this.CurrentChampion.CA.ManaStart > this.CurrentChampion.CA.ManaCost) {
            this.CurrentChampion.CA.ManaStart = this.CurrentChampion.CA.ManaCost;
        }
    }

}
