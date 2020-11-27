package tactical_chaos.store;

import tactical_chaos.champion.Champion;
import java.util.*;
import static tactical_chaos.champion.Champion.CopyChampion;
import tactical_chaos.champion.ChampionsEnum.ChampionClass;

public class TemporalStoreFilter extends StoreFilter {

    @Override
    public List<Champion> GetOnly(List<Champion> store, int ChampionsNumber, List<ChampionClass> classes) {
        // create a temporary list for storing 
        // selected element 
        if(store.isEmpty()){
            return null;
        }
        Champion temp;
        List<Champion> newList = new ArrayList<>(), tempList = new ArrayList<>();
        for (Champion c : store) {
            temp = CopyChampion(c);
            tempList.add(temp);
        }
        Random rand = new Random();
        for (int i = 0; i < ChampionsNumber; i++) {
            // take a raundom index between 0 to size 
            // of given List 
            int randomIndex = rand.nextInt(tempList.size());
            // add element in temporary list 
            newList.add(store.get(randomIndex));
        }
        return newList;
    }
}
