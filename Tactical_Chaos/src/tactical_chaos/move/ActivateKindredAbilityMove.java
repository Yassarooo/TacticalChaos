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
public class ActivateKindredAbilityMove extends Move {

    public ActivateKindredAbilityMove(Arena map, Player player, Champion Deffender) {
        this.CurrentPlayer = player;
        this.CurrentChampion = Deffender; //itself
        this.Map = map;
        this.MoveID = 16;
    }

    @Override
    public void PerformMove() {
        float inc;
        for (Square s : this.Map.Squares) {
            if (!s.square.isEmpty()) {
                for (Champion c : s.square) {
                    if (this.CurrentPlayer.ChampionsID.contains(c.CA.ID) && this.CurrentChampion.CA.ID != c.CA.ID) {
                        inc=c.CA.AttackDamage * 10 / 100;
                        if (this.CurrentChampion.CA.SorcererBuff != 0) {
                            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
                        }
                        c.CA.AttackDamage +=inc;
                    }
                }
            }
        }

    }
}
