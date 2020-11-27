/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.*;
import tactical_chaos.champion.ChampionsEnum.ChampionItems;
import tactical_chaos.player.Player;

public class SellItem extends Move {

    public SellItem(List<ChampionItems> store, Player player) {
        this.CurrentPlayer = player;
        this.ItemStore = store;
    }

    @Override
    public void PerformMove() {
        int c1, c2;
        Scanner sc = new Scanner(System.in);
        this.CurrentPlayer.PrintChampions();
        System.out.println("-Select Champion to Sell Item : ");
        c1 = sc.nextInt();
        while (SearchInList(c1, this.CurrentPlayer.Bench) == null && SearchInList(c1, this.CurrentPlayer.currentChampions) == null) {
            System.out.println("-Champion Not Found !,Please Insert A Valid ID \n --->>");
            c1 = sc.nextInt();
            this.CurrentChampion = SearchInList(c1, this.CurrentPlayer.Bench);
            if (this.CurrentChampion == null) {
                this.CurrentChampion = SearchInList(c1, this.CurrentPlayer.currentChampions);
            }

        }
        if (this.CurrentChampion.Items.isEmpty()) {
            System.out.println("-Champion Doesn't Have Any Item ! ");
            return;
        }
        for (int i = 0; i < this.CurrentChampion.Items.size(); i++) {
            System.out.println(i + 1 + "-" + this.CurrentChampion.Items.get(i).toString());
        }
        System.out.println("-Select Item :\n--->>");
        c2 = sc.nextInt();
        while (c2 < 1 || c2 > this.CurrentChampion.Items.size()) {
            System.out.println("-Wrong Input !,Please Enter A Valid Number or Press(0) to skip :\n--->>");
            c2 = sc.nextInt();
            if (c2 == 0) {
                return;
            }
        }
        this.ItemStore.add(this.CurrentChampion.Items.get(c2-1));
        this.CurrentPlayer.coins+=2;
        System.out.println("Item Sold Successfully !");
    }

}
