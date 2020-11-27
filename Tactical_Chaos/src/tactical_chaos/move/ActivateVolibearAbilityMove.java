/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.*;
import tactical_chaos.champion.Champion;
import tactical_chaos.damage.BasicAttackDamageCalculator;
import tactical_chaos.damage.MagicDamage;
import tactical_chaos.damage.TrueDamage;
import tactical_chaos.player.*;

/**
 *
 * @author Yassar
 */
public class ActivateVolibearAbilityMove extends Move {

    public ActivateVolibearAbilityMove(Arena map, Player player, Champion Attacker, Champion Deffender) {
        this.CurrentPlayer = player;
        this.Deffender = Deffender;
        this.CurrentChampion = Attacker;
        this.Map = map;
        this.MoveID = 29;
    }

    @Override
    public void PerformMove() {
        this.Deffender.AcceptDamage(new BasicAttackDamageCalculator(this.CurrentChampion.CA.AttackDamage));
        List<Champion> Enemies = new ArrayList<>();
        float damage = this.CurrentChampion.CA.AttackDamage - this.CurrentChampion.CA.AttackDamage * 50 / 100;
        
        if (this.CurrentChampion.CA.SorcererBuff != 0) {
            damage += damage * this.CurrentChampion.CA.SorcererBuff / 100;
        }
        
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

        Random rand = new Random();
        int idx = rand.nextInt(Enemies.size());
        int idx2 = rand.nextInt(Enemies.size());
        Enemies.get(idx).AcceptDamage(new MagicDamage(damage));
        Enemies.get(idx2).AcceptDamage(new MagicDamage(damage));

    }
}
