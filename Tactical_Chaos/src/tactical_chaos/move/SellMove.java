package tactical_chaos.move;

import tactical_chaos.store.*;
import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.*;
import tactical_chaos.player.*;

public class SellMove extends Move {

    int ChampIndx, c1, c2, NewPrice;
    boolean CanSell = false;

    public SellMove(Player Player, InGameStore store,Arena Map) {
        this.Store = store;
        this.Store.ChampionsList = store.ChampionsList;
        this.CurrentPlayer = Player;
        this.Map=Map;
        this.MoveID = 35;
    }

    @Override
    public void PerformMove() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-Your Coins : || " + this.CurrentPlayer.coins + " ||\n-Your Bench Champions : ");
        System.out.println(CurrentPlayer.Bench.toString());
        System.out.println("-Your Champions In Map :");
        System.out.println(CurrentPlayer.currentChampions.toString());

        System.out.println("-Enter Champion ID To Sell Or Press (0) To (Skip) : \n--------->>>");
        this.c1 = sc.nextInt();
        if (c1 == 0) {
            return;
        }

        while (SearchInList(c1, this.CurrentPlayer.Bench) == null && SearchInList(c1, this.CurrentPlayer.currentChampions) == null && (!this.CurrentPlayer.Bench.isEmpty() || !this.CurrentPlayer.currentChampions.isEmpty() || (this.CurrentPlayer.Bench.isEmpty() && this.CurrentPlayer.currentChampions.isEmpty()))) {
            System.out.println("-Champion Not Found !\nPlease Enter a Valid Number ! Or Press (0) To (Skip) :\n------->>>");
            this.c1 = sc.nextInt();
            if (c1 == 0) {
                return;
            }
        }

        this.CurrentChampion = SearchInList(c1, this.CurrentPlayer.Bench);
        if (this.CurrentChampion == null) {
            this.CurrentChampion = SearchInList(c1, this.CurrentPlayer.currentChampions);
        }
        //to set new price according to health percent
        CheckHealth(this.CurrentChampion);

        //add price to player coins
        CurrentPlayer.coins += this.CurrentChampion.CA.Cost;

        //remove champion from player Bench and Current
        if (SearchInList(c1, this.CurrentPlayer.Bench) != null) {
            this.CurrentPlayer.Bench.remove(this.CurrentChampion);
        } else {
            this.CurrentPlayer.currentChampions.remove(this.CurrentChampion);
            int idx=this.Map.SearchForSquare(this.CurrentChampion);
            this.Map.Squares.get(idx).square.remove(this.CurrentChampion);
        }

        System.out.println("-Champion Sold Successfully !!!");

        //Edit copies in the store
        EditChampionCopy();

    }

    void CheckHealth(Champion c) {
        if (c.CA.Health < 100) {
            c.CA.Cost -= c.CA.Cost * 50 / 100;
        }
    }

    public void EditChampionCopy() {
        Champion c = this.SearchStore(c1, this.Store.ChampionsList);
        if (this.CurrentChampion.CA.Number == c.CA.Number) {
            //if the champion exactly equals store champ just increase copies
            if (this.CurrentChampion.CA.Level == 1 && this.CurrentChampion.CA.Health == c.CA.Health && this.CurrentChampion.CA.Armor == c.CA.Armor) {
                c.CA.Copies++;
            } else {
                this.CurrentChampion.CA.Copies = 1;
                this.Store.ChampionsList.add(this.CurrentChampion);
            }
        }
    }
}
