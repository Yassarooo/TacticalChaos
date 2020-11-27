/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.*;
import tactical_chaos.champion.Champion;
import tactical_chaos.champion.ChampionsEnum.*;
import static tactical_chaos.champion.ChampionsEnum.ChampionClass.*;
import static tactical_chaos.champion.ChampionsEnum.ChampionItems.*;
import tactical_chaos.player.Player;

public class BuyItem extends Move {

    public BuyItem(List<ChampionItems> store, Player player) {
        this.ItemStore = store;
        this.CurrentPlayer = player;
    }

    @Override
    public void PerformMove() {
        if (this.ItemStore.isEmpty()) {
            System.out.println("-Items Store Is Empty!!");
            return;
        }
        int c1,c2;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < this.ItemStore.size(); i++) {
            System.out.println(i + 1 + "-" + this.ItemStore.get(i).toString() + "| Price: 2");
        }
        System.out.println("-Please Select Item :\n--->>");
        c1 = sc.nextInt();
        while (c1 < 1 || c1 > this.ItemStore.size()) {
            System.out.println("-Wrong Input !,Please Enter A Valid Number or Press(0) to skip :\n--->>");
            c1 = sc.nextInt();
            if (c1 == 0) {
                return;
            }
        }
        List<Champion> CanBuy = new ArrayList<>();
        if (this.ItemStore.get(c1 - 1) == Magic_Hat) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Sorcerer)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Sorcerer)
                        CanBuy.add(c);
                }
            }
        }
        if (this.ItemStore.get(c1 - 1) == Warrior_Gloves) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==BladeMaster)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==BladeMaster)
                        CanBuy.add(c);
                }
            }
        }
        if (this.ItemStore.get(c1 - 1) == Knight_Armor) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Knight)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Knight)
                        CanBuy.add(c);
                }
            }
        }
        if (this.ItemStore.get(c1 - 1) == Angry_Cloak) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Yordle)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Yordle)
                        CanBuy.add(c);
                }
            }
        }
        if (this.ItemStore.get(c1 - 1) == Night_Shift) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Assassin)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Assassin)
                        CanBuy.add(c);
                }
            }
        }
        if (this.ItemStore.get(c1 - 1) == Void_Hit) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Void)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Void)
                        CanBuy.add(c);
                }
            }
        }
        if (this.ItemStore.get(c1 - 1) == Universe_Core) {
            for (Champion c : this.CurrentPlayer.currentChampions) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Elementalist)
                        CanBuy.add(c);
                }
            }
            for (Champion c : this.CurrentPlayer.Bench) {
                for(ChampionClass a: c.ActiveClasses){
                    if(a==Elementalist)
                        CanBuy.add(c);
                }
            }
        }
        if(this.CurrentPlayer.coins<2){
            System.out.println("You Don't Have Coins !");
            return;
        }
        if(CanBuy.isEmpty()){
            System.out.println("You Don't Have Any Champion Can Carry The Item !");
            return;
        }
        CanBuy.toString();
        System.out.println("-Select Champion To Add The Item to \n--->>");
        c2=sc.nextInt();
        while(!CanBuy.isEmpty()&&SearchInList(c2,CanBuy)==null){
            System.out.println("Champion Not Found !,Please Enter A Valid Champion ID \n--->>");
            c2=sc.nextInt();
        }
        this.CurrentChampion=SearchInList(c2,CanBuy);
        if(this.CurrentChampion.Items.size()==3)
        {
            System.out.println("Champion Items Limit Exceeded ! ,Please Choose Another Champion.");
        }
        this.CurrentChampion.Items.add(this.ItemStore.get(c1-1));
        System.out.println("Item Buyed Successfully !");
    }
}
