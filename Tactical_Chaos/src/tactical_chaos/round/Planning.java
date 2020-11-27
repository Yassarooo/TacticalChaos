package tactical_chaos.round;

import tactical_chaos.store.*;
import java.io.*;
import java.util.*;
import tactical_chaos.arena.Arena;
import tactical_chaos.champion.*;
import tactical_chaos.champion.ChampionsEnum.ChampionItems;

public class Planning extends Round {

    public Arena Map;
    public InGameStore newStore;
    public List<ChampionItems> ItemsStore;

    public Planning(int num, Arena m, InGameStore s,List<ChampionItems> st) throws FileNotFoundException {
        this.NumberOfRound = num;
        this.Map = m;
        this.newStore = s;
        this.ItemsStore= st;
    }

    public static List<Champion> GetTemporalList(InGameStore newStore) {

        InGameStore tempStore = new InGameStore();

        if (!newStore.HasBeenFiltered) {
            newStore.ChampionsList = newStore.AcceptFilter(new ChampionClassFilter());
            newStore.HasBeenFiltered = true;
        }
        tempStore.CopyStore(newStore);
        tempStore.ChampionsList = tempStore.AcceptFilter(new TemporalStoreFilter());
        return tempStore.ChampionsList;
    }

}