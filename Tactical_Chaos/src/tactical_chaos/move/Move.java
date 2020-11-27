package tactical_chaos.move;

import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.*;
import tactical_chaos.champion.ChampionsEnum.ChampionItems;
import tactical_chaos.player.*;
import tactical_chaos.store.*;

abstract public class Move {

    public InGameStore Store;
    public List<Champion> TempStore;
    public List<ChampionItems> ItemStore;
    public Player CurrentPlayer;
    public Champion CurrentChampion, Deffender;
    public Arena Map;
    public int ID;
    public int MoveID;

    abstract public void PerformMove();

    public static Champion SearchStore(int num, List<Champion> store) {
        for (Champion c : store) {
            if (c.CA.Number == num) {
                return c;
            }
        }
        return null;
    }

    public static Champion SearchInList(int id, List <Champion> list) {
        for (Champion c : list) {
            if (c.CA.ID == id) {
                return c;
            }
        }
        return null;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Move m = (Move) obj;
        return (this.MoveID == m.MoveID && this.CurrentChampion.equals(m.CurrentChampion));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.CurrentChampion);
        hash = 29 * hash + Objects.hashCode(this.Deffender);
        hash = 29 * hash + this.MoveID;
        return hash;
    }
    
}