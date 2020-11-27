package tactical_chaos.arena;

import java.util.*;
import tactical_chaos.arena.Square.LandType;
import static tactical_chaos.arena.Square.LandType.*;
import tactical_chaos.champion.Champion;
import static tactical_chaos.champion.Champion.GetChance;
import tactical_chaos.champion.ChampionsEnum;
import tactical_chaos.champion.ChampionsEnum.ChampionItems;
import tactical_chaos.player.*;

public class Arena {

    public List<Square> Squares;

    public Arena() {
        Squares = new ArrayList<>();
        int counter = 1;
        //boolean chance = ;
        int rand= new Random().nextInt(ChampionItems.values().length);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Square tempSquare = new Square();
                tempSquare.SquareNumber = counter;
                tempSquare.SquareX = i;
                tempSquare.SquareY = j;
                if(GetChance(30))
                    tempSquare.Items.add(ChampionItems.values()[rand]);
                if(GetChance(15))
                    tempSquare.Type=Terrain;
                if(tempSquare.Type==None)
                    if(GetChance(15))
                        tempSquare.Type=Water;
                if(tempSquare.Type==None)
                    if(GetChance(25))
                        tempSquare.Type=Grass;
                if(tempSquare.Type==None)
                        tempSquare.Type=Standard;
                Squares.add(tempSquare);
                counter++;
            }

        }
    }

    public void PutChampionOnMap(int SquareNumber, Champion champion) {
        Squares.get(SquareNumber - 1).square.add(champion);
    }

    public void PrintMap() {
        for (Square s : Squares) {
            if (!s.square.isEmpty()) {
                System.out.print("Square (X/Y) : (" + s.SquareX + "," + s.SquareY + ") --> ");
                System.out.print(s.square);
                System.out.println("\n");
            }
        }
    }

    public void PlayerMap(Player p) {
        for (Square s : Squares) {
            if (!s.square.isEmpty()) {
                for (Champion c : p.currentChampions) {
                    if (s.square.contains(c)) {
                        System.out.print("Square (X/Y) : (" + s.SquareX + "," + s.SquareY + ") --> ");
                        System.out.print(c);
                        System.out.println("\n");
                    }
                }
            }
        }
    }

    public List<Champion> AttackMap(Player p) {
        List<Champion> Enemies = new ArrayList<>();
        for (Square s : Squares) {
            if (!s.square.isEmpty()) {
                for (Champion c : s.square) {
                    if (!p.ChampionsID.contains(c.CA.ID)) {
                        Enemies.add(c);
                        System.out.print("Square (X/Y) : (" + s.SquareX + "," + s.SquareY + ") --> ");
                        System.out.print(c);
                        System.out.println("\n");
                    }
                }
            }
        }
        return Enemies;
    }

    public int SearchForSquare(Champion target) {
        for (int i = 0; i < Squares.size(); i++) {
            if (!Squares.get(i).square.isEmpty()) {
                for(Champion c : Squares.get(i).square) {
                    if(c.CA.ID == target.CA.ID) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
