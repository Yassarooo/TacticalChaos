/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tactical_chaos.move;

import java.util.List;
import java.util.Scanner;
import tactical_chaos.champion.Champion;
import static tactical_chaos.champion.ChampionsEnum.ChampionItems.*;
import static tactical_chaos.move.Move.SearchInList;
import tactical_chaos.player.Player;
import tactical_chaos.store.InGameStore;

/**
 *
 * @author Yassar
 */
public class ItemMove extends Move{
    public ItemMove (Player player) {
        this.CurrentPlayer = player;
        this.MoveID = 38;
    }
    @Override
    public void PerformMove() {
        Scanner s = new Scanner(System.in);
        int c1,c2;
        System.out.println("\n-Your Champions On The Map : ");
        for (Champion c : this.CurrentPlayer.currentChampions) {
            System.out.println(c.PrintChampion());
        }
        System.out.println("Select Champion To Print its Items :");
        c1 = s.nextInt();
        while (SearchInList(c1, this.CurrentPlayer.currentChampions) == null && !this.CurrentPlayer.currentChampions.isEmpty()) {
            System.out.println("-Champion Not Found !\nPlease Enter a Valid Number !\n------->>>");
            c1 = s.nextInt();
        }
        Champion c = SearchInList(c1, this.CurrentPlayer.currentChampions);
        for(int i=0;i<c.Items.size();i++){
            System.out.println(i++ +"-"+c.Items.get(i));
        }
        System.out.println("-Select An Item :\n--->>");
        c2=s.nextInt();
        if(c.Items.get(c2-1).equals(Magic_Hat));
            c.CA.MagicHat=true;
        if(c.Items.get(c2-1).equals(Warrior_Gloves));
            c.CA.AttackDamage+=c.CA.AttackDamage*10/100;
        if(c.Items.get(c2-1).equals(Knight_Armor));
            c.CA.Armor+=c.CA.Armor*15/100;
        if(c.Items.get(c2-1).equals(Angry_Cloak));
            c.CA.CriticalStrikeChance+=c.CA.CriticalStrikeChance*10/100;
        if(c.Items.get(c2-1).equals(Night_Shift));
            c.CA.AttackDamage+=c.CA.AttackDamage*20/100;
        if(c.Items.get(c2-1).equals(Void_Hit));
            c.CA.Health+=c.CA.Health*5/100;
        if(c.Items.get(c2-1).equals(Universe_Core));
            c.CA.MagicResist+=c.CA.MagicResist*20/100;
    }
    
}
