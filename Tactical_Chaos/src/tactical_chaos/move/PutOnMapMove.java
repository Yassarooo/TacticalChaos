/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.Random;
import java.util.Scanner;
import tactical_chaos.arena.Arena;
import tactical_chaos.player.Player;

public class PutOnMapMove extends Move {

    public PutOnMapMove(Player player, Arena Map) {
        this.CurrentPlayer = player;
        this.Map = Map;
        this.MoveID = 34;
    }

    @Override
    public void PerformMove() {
        int c1 = 0;
        if (this.CurrentPlayer.PlayerID == 2) {
            if (this.CurrentPlayer.Bench.isEmpty()) {
                return;
            }
            Random rand = new Random();
            int c = rand.nextInt(this.CurrentPlayer.Bench.size());
            this.CurrentChampion = this.CurrentPlayer.Bench.get(c);
            c1 = rand.nextInt(9999);
        } else if (this.CurrentPlayer.PlayerID == 1) {
            this.CurrentChampion = this.CurrentPlayer.ChooseChampionFromBench();
            System.out.println("-Choose Square Number To Put On");
            System.out.print("(1 -> 10000) --->> : ");
            Scanner sc = new Scanner(System.in);

            c1 = sc.nextInt();
            while (c1 < 1 || c1 > 10000) {
                System.out.println("-Wrong Input !!");
                System.out.println("-Choose Square Number To Put On");
                System.out.print("(1 -> 10000) --->> : ");

                c1 = sc.nextInt();
            }
        }
        this.Map.PutChampionOnMap(c1, this.CurrentChampion);
        this.CurrentPlayer.Bench.remove(this.CurrentChampion);
        this.CurrentPlayer.currentChampions.add(this.CurrentChampion);
    }
}
