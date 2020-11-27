/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.*;
import tactical_chaos.champion.Champion;
import tactical_chaos.damage.MagicDamage;
import tactical_chaos.damage.TrueDamage;
import tactical_chaos.player.*;

/**
 *
 * @author Yassar
 */
public class ActivateKarthusAbilityMove extends Move {

    public ActivateKarthusAbilityMove(Arena map, Player player, Champion Deffender) {
        this.CurrentPlayer = player;
        this.CurrentChampion = Deffender; //Himself
        this.Map = map;
        this.MoveID = 12;
    }

    @Override
    public void PerformMove() {
        List<Champion> Enemies = new ArrayList<>();
        float damage = 250;
        for (Square s : this.Map.Squares) {
            if (!s.square.isEmpty()) {
                for (Champion c : s.square) {
                    if (!this.CurrentPlayer.ChampionsID.contains(c.CA.ID)) {
                        if (c.CA.DragonBuff) {
                            continue;
                        }
                        Enemies.add(c);
                    }
                }
            }
        }
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        if(this.CurrentChampion.CA.MagicHat){
            damage += damage*20/100;
        }
        if (this.CurrentChampion.CA.VoidBuff) {
            for (Champion c : Enemies) {
                c.AcceptDamage(new TrueDamage(damage));
            }

        } else {
            for (Champion c : Enemies) {
                c.AcceptDamage(new MagicDamage(damage));
            }

        }

    }
}
