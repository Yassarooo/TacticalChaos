package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.*;
import tactical_chaos.player.Player;

public class SwapMove extends Move {

    int c1, c2;

    public SwapMove(Player Player, Arena Map) {
        this.CurrentPlayer = Player;
        this.Map = Map;
        this.MoveID = 36;
    }

    @Override
    public void PerformMove() {

        if (this.CurrentPlayer.PlayerID == 2) {
            if (this.CurrentPlayer.Bench.isEmpty()) {
                return;
            }
            Champion temp = this.CurrentPlayer.currentChampions.get(0);
            for (Champion c : this.CurrentPlayer.currentChampions) {
                if (c.CA.newHealth < temp.CA.newHealth) {
                    temp = c;
                }
            }
            Champion temp2 = this.CurrentPlayer.Bench.get(0);
            for (Champion c : this.CurrentPlayer.Bench) {
                if (c.CA.newHealth > temp.CA.newHealth) {
                    temp2 = c;
                }
            }
            if (temp.CA.newHealth > temp2.CA.newHealth) {
                return;
            }
            c1 = this.CurrentPlayer.Bench.indexOf(temp2);
            c1++;
            c2 = this.CurrentPlayer.currentChampions.indexOf(temp);
            c2++;

        } else {
            if (this.CurrentPlayer.currentChampions.isEmpty()) {
                System.out.println("-You Don't Have Champions In Arena, Please Add Champions First And Try Again . ");
                return;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("-Your Bench Champions : ");
            for (int i = 0; i < this.CurrentPlayer.Bench.size(); i++) {
                System.out.println(i + 1 + "- " + this.CurrentPlayer.Bench.get(i).PrintChampion());
            }

            System.out.println("-Select Champion Number ,Press (0) To (Skip) : \n--------->>>");
            this.c1 = sc.nextInt();
            if (c1 == 0) {
                return;
            }
            while (c1 > this.CurrentPlayer.Bench.size() && !this.CurrentPlayer.Bench.isEmpty()) {
                System.out.println("-Champion Not Found !,Please Enter a Valid Number !\n------->>>");
                this.c1 = sc.nextInt();
                if (c1 == 0) {
                    return;
                }
            }

            System.out.println("-Your Champions in Arena : ");
            for (int i = 0; i < this.CurrentPlayer.currentChampions.size(); i++) {
                System.out.println(i + 1 + "- " + this.CurrentPlayer.currentChampions.get(i).PrintChampion());
            }

            System.out.println("-Select Champion To Swap (Order Number) : \n--------->>>");
            this.c2 = sc.nextInt();

            while (c2 > this.CurrentPlayer.currentChampions.size()) {
                System.out.println("-Champion Not Found !,Please Enter a Valid Number !\n------->>>");
                this.c2 = sc.nextInt();
            }
        }
        int pos = this.Map.SearchForSquare(this.CurrentPlayer.currentChampions.get(c2 - 1));
        if(pos == -1) {
            return;
        }
        this.Map.Squares.get(pos).square.remove(this.CurrentPlayer.currentChampions.get(c2 - 1));
        this.Map.Squares.get(pos).square.add(this.CurrentPlayer.Bench.get(c1 - 1));
        this.CurrentPlayer.currentChampions.add(this.CurrentPlayer.Bench.get(c1 - 1));
        this.CurrentPlayer.Bench.remove(c1 - 1);
        this.CurrentPlayer.Bench.add(this.CurrentPlayer.currentChampions.get(c2 - 1));
        this.CurrentPlayer.currentChampions.remove(c2 - 1);

    }

}
