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
public class ActivateLuluAbilityMove extends Move {

    public ActivateLuluAbilityMove(Arena map, Player player, Champion Deffender) {
        this.CurrentPlayer = player;
        this.CurrentChampion = Deffender; //itself
        this.Map = map;
        this.MoveID = 20;
    }

    @Override
    public void PerformMove() {
        float inc = 150;
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            inc += inc * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        List<Champion> Alies = new ArrayList<>();
        this.CurrentChampion.CA.newHealth += inc;
        if (this.CurrentChampion.CA.newHealth > this.CurrentChampion.CA.Health) {
            this.CurrentChampion.CA.newHealth = this.CurrentChampion.CA.Health;
        }
        for (Square s : this.Map.Squares) {
            if (!s.square.isEmpty()) {
                for (Champion c : s.square) {
                    if (this.CurrentPlayer.ChampionsID.contains(c.CA.ID) && this.CurrentChampion.CA.ID != c.CA.ID) {
                        Alies.add(c);
                    }
                }
            }
        }
        if (Alies.size() > 0) {
            Random rand = new Random();
            int idx = rand.nextInt(Alies.size());
            int idx2 = rand.nextInt(Alies.size());
            Alies.get(idx).CA.newHealth += inc;
            if (Alies.get(idx).CA.newHealth > Alies.get(idx).CA.Health) {
                Alies.get(idx).CA.newHealth = Alies.get(idx).CA.Health;
            }
            Alies.get(idx2).CA.newHealth += inc;
            if (Alies.get(idx2).CA.newHealth > Alies.get(idx2).CA.Health) {
                Alies.get(idx2).CA.newHealth = Alies.get(idx2).CA.Health;
            }
        }
    }
}
